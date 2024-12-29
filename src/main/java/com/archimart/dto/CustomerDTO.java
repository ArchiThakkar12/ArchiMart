package com.archimart.dto;

import com.archimart.entity.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {

    private String emailId;

    private String name;

    private String password;

    private String newPassword;

    private String phoneNumber;

    private String address;

}
