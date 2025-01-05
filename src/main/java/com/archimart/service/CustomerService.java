package com.archimart.service;

import com.archimart.dto.CustomerDTO;
import com.archimart.entity.Customer;
import com.archimart.exception.ArchiMartException;
import com.archimart.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "customerService")
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void updateShippingAddress(String customerId , String address) throws ArchiMartException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId.toLowerCase());
        Customer customer = optionalCustomer.orElseThrow(() -> new ArchiMartException("CustomerService.CUSTOMER_NOT_FOUND"));
        customer.setAddress(address);
    }

    public void deleteShippingAddress(String customerEmailId) throws ArchiMartException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerEmailId.toLowerCase());
        Customer customer = optionalCustomer.orElseThrow(() -> new ArchiMartException("CustomerService.CUSTOMER_NOT_FOUND"));
        customer.setAddress(null);
    }

    public CustomerDTO authenticateCustomer(String emailId, String password) throws ArchiMartException {
        CustomerDTO customerDTO = null;

        Optional<Customer> optionalCustomer = customerRepository.findById(emailId.toLowerCase());
        Customer customer = optionalCustomer.orElseThrow(() -> new ArchiMartException("CustomerService.CUSTOMER_NOT_FOUND"));
        if (!password.equals(customer.getPassword()))
            throw new ArchiMartException("CustomerService.INVALID_CREDENTIALS");

        customerDTO = new CustomerDTO();
        customerDTO.setEmailId(customer.getEmailId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setAddress(customer.getAddress());
        return customerDTO;

    }

    public CustomerDTO getCustomerByEmailId(String emailId) throws ArchiMartException {
        CustomerDTO customerDTO = null;

        Optional<Customer> optionalCustomer = customerRepository.findById(emailId.toLowerCase());
        Customer customer = optionalCustomer.orElseThrow(() -> new ArchiMartException("CustomerService.CUSTOMER_NOT_FOUND"));

        customerDTO = new CustomerDTO();
        customerDTO.setEmailId(customer.getEmailId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setAddress(customer.getAddress());
        return customerDTO;
    }

    public String registerNewCustomer(CustomerDTO customerDTO) throws ArchiMartException {
        String registeredWithEmailId = null;
        boolean isEmailNotAvailable = customerRepository.findById(customerDTO.getEmailId().toLowerCase()).isEmpty();
        boolean isPhoneNumberNotAvailable = customerRepository.findByPhoneNumber(customerDTO.getPhoneNumber()).isEmpty();
        if (isEmailNotAvailable) {
            if (isPhoneNumberNotAvailable) {
                Customer customer = new Customer();
                customer.setEmailId(customerDTO.getEmailId().toLowerCase());
                customer.setName(customerDTO.getName());
                customer.setPassword(customerDTO.getPassword());
                customer.setPhoneNumber(customerDTO.getPhoneNumber());
                customer.setAddress(customerDTO.getAddress());
                customerRepository.save(customer);
                registeredWithEmailId = customer.getEmailId();
            } else {
                throw new ArchiMartException("CustomerService.PHONE_NUMBER_ALREADY_IN_USE");
            }
        } else {
            throw new ArchiMartException("CustomerService.EMAIL_ID_ALREADY_IN_USE");
        }
        return registeredWithEmailId;

    }
}
