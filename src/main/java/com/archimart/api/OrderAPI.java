package com.archimart.api;

import com.archimart.dto.*;
import com.archimart.exception.ArchiMartException;
import com.archimart.service.CustomerCartService;
import com.archimart.service.OrderService;
import com.archimart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@CrossOrigin
@RequestMapping(value = "/customerorder-api")
@RestController
public class OrderAPI {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CustomerCartService customerCartService;

	@Autowired
	private Environment environment;

	@PostMapping(value = "/place-order")
	public ResponseEntity<String> placeOrder(@RequestBody OrderDTO order) throws ArchiMartException {
		System.out.println("Received a request to get products details from the cart of "+order.getCustomerEmailId());

		Set<CartProductDTO> cartProductDTOs = customerCartService.getProductsFromCart(order.getCustomerEmailId());
		for (CartProductDTO cartProductDTO : cartProductDTOs) {
			ProductDTO productDTO = productService.getProductById(cartProductDTO.getProduct().getProductId());
			cartProductDTO.setProduct(productDTO);
		}

		System.out.println("Received a request to clear the cart of "+order.getCustomerEmailId() );
		customerCartService.deleteAllProductsFromCart(order.getCustomerEmailId());

		List<OrderedProductDTO> orderedProductDTOs = new ArrayList<>();
		for (CartProductDTO cartProductDTO : cartProductDTOs) {
			OrderedProductDTO orderedProductDTO = new OrderedProductDTO();
			orderedProductDTO.setProduct(cartProductDTO.getProduct());
			orderedProductDTO.setQuantity(cartProductDTO.getQuantity());
			orderedProductDTOs.add(orderedProductDTO);
		}
		order.setOrderedProducts(orderedProductDTOs);

		Integer orderId = orderService.placeOrder(order);
		String modificationSuccessMsg = environment.getProperty("OrderAPI.ORDER_PLACED_SUCCESSFULLY");

		return new ResponseEntity<>(modificationSuccessMsg + orderId, HttpStatus.CREATED);
	}

	@GetMapping(value = "order/{orderId}")
	public ResponseEntity<OrderDTO> getOrderDetails( @PathVariable Integer orderId) throws ArchiMartException {
		OrderDTO orderDTO = orderService.getOrderDetails(orderId);
		for (OrderedProductDTO orderedProductDTO : orderDTO.getOrderedProducts()) {
			ProductDTO product = productService.getProductById(orderedProductDTO.getProduct().getProductId());
			orderedProductDTO.setProduct(product);
		}
		return new ResponseEntity<>(orderDTO, HttpStatus.OK);
	}


	@GetMapping(value = "customer/{customerEmailId}/orders")
	public ResponseEntity<List<OrderDTO>> getOrdersOfCustomer( @PathVariable String customerEmailId ) throws ArchiMartException {
		List<OrderDTO> orderDTOs = orderService.findOrdersByCustomerEmailId(customerEmailId);
		for (OrderDTO orderDTO : orderDTOs) {
			for (OrderedProductDTO orderedProductDTO : orderDTO.getOrderedProducts()) {
				ProductDTO product = productService.getProductById(orderedProductDTO.getProduct().getProductId());
				orderedProductDTO.setProduct(product);
			}
		}
		return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
	}


	@PutMapping(value = "order/{orderId}/update/order-status")
	public void updateOrderAfterPayment( @PathVariable Integer orderId,
			@RequestBody String transactionStatus) throws ArchiMartException {
		if (transactionStatus.equals("TRANSACTION_SUCCESS")) {
			orderService.updateOrderStatus(orderId, OrderStatus.CONFIRMED);
			OrderDTO orderDTO = orderService.getOrderDetails(orderId);
			for (OrderedProductDTO orderedProductDTO : orderDTO.getOrderedProducts()) {
				productService.reduceAvailableQuantity(orderedProductDTO.getProduct().getProductId(), orderedProductDTO.getQuantity());
			}
		} else {
			orderService.updateOrderStatus(orderId, OrderStatus.CANCELLED);
		}
	}

	@PutMapping(value = "order/{orderId}/update/payment-through")
	public void updatePaymentOption( @PathVariable Integer orderId,
			@RequestBody String paymentThrough) throws ArchiMartException {
		if (paymentThrough.equalsIgnoreCase("DEBIT_CARD")) {
			orderService.updatePaymentThrough(orderId, PaymentThrough.DEBIT_CARD);
		} else {
			orderService.updatePaymentThrough(orderId, PaymentThrough.CREDIT_CARD);
		}
	}

}
