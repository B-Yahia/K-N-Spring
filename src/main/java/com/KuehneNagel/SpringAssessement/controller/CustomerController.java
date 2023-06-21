package com.KuehneNagel.SpringAssessement.controller;

import com.KuehneNagel.SpringAssessement.dto.CustomerDTO;
import com.KuehneNagel.SpringAssessement.mapper.CustomerMapper;
import com.KuehneNagel.SpringAssessement.model.Customer;
import com.KuehneNagel.SpringAssessement.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    //Create Customer entity
    @PostMapping
    public ResponseEntity<CustomerDTO> addCustomerToDB (@Valid @RequestBody CustomerDTO customerDTO ){
        // Convert the received CustomerDTO to a Customer entity and save it to the database through the customer service.
        var newCustomer = customerService.addCustomer(customerMapper.toEntity(customerDTO));
        // Convert the saved Customer entity back to a CustomerDTO return it with 201 http status
        return new ResponseEntity<>(customerMapper.toDto(newCustomer), HttpStatus.CREATED);
    }

    //Find customer by id
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findCustomer(@PathVariable Long id){
        var customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
    }

    // Returns all customer in the db
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAllCustomers(){
        var customersList = customerService.findAllCustomers();
        var ccustomersDTOList = customersList.stream().map(customerMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(ccustomersDTOList, HttpStatus.OK);
    }

}
