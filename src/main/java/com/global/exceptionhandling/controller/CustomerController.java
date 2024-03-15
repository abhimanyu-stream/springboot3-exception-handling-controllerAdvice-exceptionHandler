package com.global.exceptionhandling.controller;


import com.global.exceptionhandling.dto.CustomerRequest;
import com.global.exceptionhandling.exception.ResourceNotFoundException;
import com.global.exceptionhandling.model.Customer;
import com.global.exceptionhandling.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody @Validated CustomerRequest customerRequest) {

        Customer customer = customerService.createCustomer(customerRequest);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);

    }

    @GetMapping("/find/customer/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Not found Customer with id = " + id);
        }

    }

    @DeleteMapping("/delete/customer/{id}")
    public ResponseEntity<String> deleteCustomerBtId(@PathVariable("id") Long id) {
        String message = customerService.deleteCustomerById(id);
        if (message.equals("failed")) {
            throw new ResourceNotFoundException("Not found Customer with id = " + id);
        }
        //if (message.equals("successful"))
        return new ResponseEntity<>("Customer with id " + id + " deleted successfully from DB", HttpStatus.OK);


    }
}









