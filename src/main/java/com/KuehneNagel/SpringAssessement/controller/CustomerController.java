package com.KuehneNagel.SpringAssessement.controller;

import com.KuehneNagel.SpringAssessement.model.Customer;
import com.KuehneNagel.SpringAssessement.service.CustomerService;
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
    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomerToDB (@RequestBody Customer customer ){
        Customer newCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    //find customer by id
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomer(@PathVariable Long id){
        Customer customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // returnes all customer in the db
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findAllCustomers(){
        List<Customer> customersList = customerService.findAllCustomers();
        return new ResponseEntity<>(customersList, HttpStatus.OK);
    }

}
