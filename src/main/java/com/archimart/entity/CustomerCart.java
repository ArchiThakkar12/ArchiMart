package com.archimart.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="CUSTOMER_CART")
public class CustomerCart {

    @Id
    @Column(name="CART_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer cartId;


    @Column(name="CUSTOMER_EMAIL_ID")
    private String customerEmailId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="cartId")
    private Set<CartProduct> cartProducts;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public void setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    public Set<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(Set<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }
}

