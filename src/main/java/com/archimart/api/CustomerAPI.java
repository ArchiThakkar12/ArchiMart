package com.archimart.api;


import com.archimart.dto.CustomerDTO;
import com.archimart.exception.ArchiMartException;
import com.archimart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/customer-api")
@RestController
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Environment environment;


    @PostMapping(value = "/login")
    public ResponseEntity<CustomerDTO> authenticateCustomer(@RequestBody CustomerDTO customerDTO)
            throws ArchiMartException {

        CustomerDTO customerDTOFromDB = customerService.authenticateCustomer(customerDTO.getEmailId(),
                customerDTO.getPassword());
        System.out.println("CUSTOMER LOGIN SUCCESS, CUSTOMER EMAIL : " + customerDTOFromDB.getEmailId());
        return new ResponseEntity<>(customerDTOFromDB, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerDTO customerDTO)
            throws ArchiMartException {

        System.out.println("CUSTOMER TRYING TO REGISTER. CUSTOMER EMAIL ID: " + customerDTO.getEmailId());
        String registeredWithEmailID = customerService.registerNewCustomer(customerDTO);
        registeredWithEmailID = environment.getProperty("CustomerAPI.CUSTOMER_REGISTRATION_SUCCESS")
                + registeredWithEmailID;
        return new ResponseEntity<>(registeredWithEmailID, HttpStatus.OK);
    }

    @PutMapping(value = "/customer/{customerEmailId:.+}/address/")
    public ResponseEntity<String> updateShippingAddress(String customerEmailId, @RequestBody String address) throws ArchiMartException {

        customerService.updateShippingAddress(customerEmailId, address);
        String modificationSuccessMsg = environment.getProperty("CustomerAPI.UPDATE_ADDRESS_SUCCESS");
        return new ResponseEntity<>(modificationSuccessMsg, HttpStatus.OK);

    }

    @DeleteMapping(value = "/customer/{customerEmailId:.+}")
    public ResponseEntity<String> deleteShippingAddress( @PathVariable("customerEmailId") String customerEmailId) throws ArchiMartException {

        customerService.deleteShippingAddress(customerEmailId);
        String modificationSuccessMsg = environment.getProperty("CustomerAPI.CUSTOMER_ADDRESS_DELETED_SUCCESS");
        return new ResponseEntity<>(modificationSuccessMsg, HttpStatus.OK);

    }

}
