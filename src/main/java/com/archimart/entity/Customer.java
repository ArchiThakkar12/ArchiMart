package com.archimart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    private String emailId;

    private String name;

    private String password;

    private String phoneNumber;

    private String address;
}
