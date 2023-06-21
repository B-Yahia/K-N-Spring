package com.KuehneNagel.SpringAssessement.controller;

import com.KuehneNagel.SpringAssessement.model.Customer;
import com.KuehneNagel.SpringAssessement.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //Create Customer entity
    @PostMapping
    public ResponseEntity<Customer> addCustomerToDB (@Valid @RequestBody Customer customer ){
        var newCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    //Find customer by id
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomer(@PathVariable Long id){
        var customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // Returns all customer in the db
    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomers(){
        var customersList = customerService.findAllCustomers();
        return new ResponseEntity<>(customersList, HttpStatus.OK);
    }

}
