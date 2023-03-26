package com.KuehneNagel.SpringAssessement.service;

import com.KuehneNagel.SpringAssessement.model.Customer;
import com.KuehneNagel.SpringAssessement.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    public void setUp() {
        customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFullName("Yahya");
        customer1.setEmail("Yahya@gmail.com");
        customer1.setPhone("1234567890");

        customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFullName("Najat");
        customer2.setEmail("Najat@gmail.com");
        customer2.setPhone("0987654321");
    }

    @Test
    public void addCustomerTest() {
        when(customerRepository.save(customer1)).thenReturn(customer1);

        Customer result = customerService.addCustomer(customer1);

        assertEquals(customer1, result);
        verify(customerRepository, times(1)).save(customer1);
    }

    @Test
    public void findCustomerByIdTest() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer1));

        Customer result = customerService.findCustomerById(1L);

        assertEquals(customer1, result);
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void findCustomerByIdNotFoundTest() {
        when(customerRepository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> customerService.findCustomerById(3L));
        verify(customerRepository, times(1)).findById(3L);
    }

    @Test
    public void findAllCustomersTest() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> result = customerService.findAllCustomers();

        assertEquals(2, result.size());
        assertEquals(customer1, result.get(0));
        assertEquals(customer2, result.get(1));
        verify(customerRepository, times(1)).findAll();
    }
}