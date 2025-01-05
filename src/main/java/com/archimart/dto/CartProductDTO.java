package com.archimart.dto;

public class CartProductDTO {

    private Integer cartProductId;
    private ProductDTO product;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getCartProductId() {
        return cartProductId;
    }
    public void setCartProductId(Integer cartProductId) {
        this.cartProductId = cartProductId;
    }
    public ProductDTO getProduct() {
        return product;
    }
    public void setProduct(ProductDTO product) {
        this.product = product;
    }

}
