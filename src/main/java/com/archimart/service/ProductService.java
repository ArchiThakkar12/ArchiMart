package com.archimart.service;

import com.archimart.dto.ProductDTO;
import com.archimart.entity.Product;
import com.archimart.exception.ArchiMartException;
import com.archimart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "productService")
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() throws ArchiMartException{
        List<ProductDTO> productDTOs = new ArrayList<>();
        Iterable<Product> products = this.productRepository.findAll();

        if(products!=null){
            for(Product product : products){
                productDTOs.add(ProductDTO.prepareDTO(product));
            }
        }

        return productDTOs;
    }

    public ProductDTO getProductById(Integer productId) throws ArchiMartException{
        Product product = productRepository.findById(productId).orElseThrow(()->new ArchiMartException("ProductService.PRODUCT_NOT_AVAILABLE"));
        return ProductDTO.prepareDTO(product);
    }


    public void reduceAvailableQuantity(Integer productId, Integer quantity) throws ArchiMartException{
        Product product = productRepository.findById(productId).orElseThrow(()->new ArchiMartException("ProductService.PRODUCT_NOT_AVAILABLE"));

        product.setAvailableQuantity(product.getAvailableQuantity() - quantity);
    }
}
