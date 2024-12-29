package com.archimart.service;

import com.archimart.dto.CustomerDTO;
import com.archimart.exception.ArchiMartException;

public interface CustomerService {

    CustomerDTO authenticateCustomer(String emailId, String password) throws ArchiMartException;

    String registerNewCustomer(CustomerDTO customerDTO) throws ArchiMartException;

    void updateShippingAddress(String customerEmailId , String address) throws ArchiMartException;

    void deleteShippingAddress(String customerEmailId) throws ArchiMartException;

    CustomerDTO getCustomerByEmailId(String emailId) throws ArchiMartException;

}
