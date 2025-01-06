package com.archimart.api;

import com.archimart.dto.CartProductDTO;
import com.archimart.dto.CustomerCartDTO;
import com.archimart.dto.ProductDTO;
import com.archimart.exception.ArchiMartException;
import com.archimart.service.CustomerCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "/customercart-api")
public class CustomerCartAPI {

    @Autowired
    private CustomerCartService customerCartService;

    @Autowired
    private Environment environment;

    @Autowired
    private RestTemplate template;


    @PostMapping(value = "/products")
    public ResponseEntity<String> addProductToCart(@RequestBody CustomerCartDTO customerCartDTO)
            throws ArchiMartException {
        System.out.println("Received a request to add products for " + customerCartDTO.getCustomerEmailId());
        Integer cartId = customerCartService.addProductToCart(customerCartDTO);
        String message = environment.getProperty("CustomerCartAPI.PRODUCT_ADDED_TO_CART");
        return new ResponseEntity<>(message + "  " + cartId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/customer/{customerEmailId}/products")
    public ResponseEntity<Set<CartProductDTO>> getProductsFromCart( @PathVariable("customerEmailId") String customerEmailId)
            throws ArchiMartException {
        System.out.println("Received a request to get products details from the cart of "+customerEmailId);

        Set<CartProductDTO> cartProductDTOs = customerCartService.getProductsFromCart(customerEmailId);
        for (CartProductDTO cartProductDTO : cartProductDTOs) {
            ProductDTO productDTO = template.getForEntity("http://localhost:8080/product-api/product/" + cartProductDTO.getProduct().getProductId(),
                    ProductDTO.class).getBody();

            cartProductDTO.setProduct(productDTO);
        }
        return new ResponseEntity<>(cartProductDTOs, HttpStatus.OK);

    }

    @DeleteMapping(value = "/customer/{customerEmailId}/product/{productId}")
    public ResponseEntity<String> deleteProductFromCart( @PathVariable("customerEmailId") String customerEmailId, @PathVariable("productId") Integer productId)
            throws ArchiMartException {

        customerCartService.deleteProductFromCart(customerEmailId,productId);
        String returnMessage = environment.getProperty("CustomerCartAPI.PRODUCT_DELETED_FROM_CART_SUCCESS");
        return new ResponseEntity<>(returnMessage, HttpStatus.OK);
    }

    @PutMapping(value = "/customer/{customerEmailId}/product/{productId}")
    public ResponseEntity<String> modifyQuantityOfProductInCart( @PathVariable("customerEmailId") String customerEmailId, @PathVariable("productId") Integer productId,
            @RequestBody Integer quantity) throws ArchiMartException{

        customerCartService.modifyQuantityOfProductInCart(customerEmailId,productId,quantity);
        String returnMessage = environment.getProperty("CustomerCartAPI.PRODUCT_QUANTITY_UPDATE_FROM_CART_SUCCESS");
        return new ResponseEntity<>(returnMessage, HttpStatus.OK);
    }

    @DeleteMapping(value = "/customer/{customerEmailId}/products")
    public ResponseEntity<String> deleteAllProductsFromCart( @PathVariable("customerEmailId") String customerEmailId)
            throws ArchiMartException {
        System.out.println("Received a request to clear the cart of "+customerEmailId );

        customerCartService.deleteAllProductsFromCart(customerEmailId);
        String message = environment.getProperty("CustomerCartAPI.ALL_PRODUCTS_DELETED");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
