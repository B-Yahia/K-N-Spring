package com.KuehneNagel.SpringAssessement.service;

import com.KuehneNagel.SpringAssessement.model.Customer;
import com.KuehneNagel.SpringAssessement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer findCustomerById (Long customerId){
        return customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException("Customer not found"));
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
}
