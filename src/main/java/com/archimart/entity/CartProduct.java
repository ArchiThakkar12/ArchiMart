package com.archimart.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CART_PRODUCT")
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartProductId;
    private Integer productId;
    private Integer quantity;

    public Integer getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(Integer cartProductId) {
        this.cartProductId = cartProductId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}