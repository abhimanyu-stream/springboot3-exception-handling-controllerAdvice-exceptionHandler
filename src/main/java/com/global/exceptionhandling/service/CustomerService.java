package com.global.exceptionhandling.service;


import com.global.exceptionhandling.dto.CustomerRequest;
import com.global.exceptionhandling.exception.EmailAlreadyRegisteredException;
import com.global.exceptionhandling.exception.ResourceNotFoundException;
import com.global.exceptionhandling.model.Customer;
import com.global.exceptionhandling.repository.CustomerRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRespository customerRespository;

    @Autowired
    public CustomerService(CustomerRespository customerRespository) {
        this.customerRespository = customerRespository;
    }

    public Customer createCustomer(CustomerRequest customerRequest){

        Customer customer = null;
        customer   = customerRespository.findByEmail(customerRequest.getEmail());

        if(customer != null) {
            throw new EmailAlreadyRegisteredException("Email "+ customerRequest.getEmail()+" already registered ");
        }else{
            customer = new Customer(customerRequest.getName(), customerRequest.getEmail(), customerRequest.getMobile());
            customer = customerRespository.save(customer);
            return customer;
        }


    }

    public Customer findCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRespository.findById(id);
        Customer customer = null;
        if(optionalCustomer.isPresent()){
            customer = optionalCustomer.get();
        }
        return customer;
    }

    public String  deleteCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRespository.findById(id);
        Customer customer = null;
        if(optionalCustomer.isPresent()){
            customerRespository.deleteById(id);
            return "successful";
        }else{
            return "failed";
        }

    }
}
