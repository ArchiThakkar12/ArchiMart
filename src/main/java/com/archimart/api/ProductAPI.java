package com.archimart.api;

import com.archimart.dto.ProductDTO;
import com.archimart.exception.ArchiMartException;
import com.archimart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/product-api")
@RestController
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @Autowired
    private Environment environment;

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() throws ArchiMartException {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/product/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer productId) throws ArchiMartException {
        ProductDTO product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{productId}")
    public ResponseEntity<String> reduceAvailableQuantity(@PathVariable Integer productId , @RequestBody Integer quantity) throws ArchiMartException {
        productService.reduceAvailableQuantity(productId,quantity);

        return new ResponseEntity<>(environment.getProperty("ProductAPI.REDUCE_QUANTITY_SUCCESSFULL"), HttpStatus.OK);
    }
}
