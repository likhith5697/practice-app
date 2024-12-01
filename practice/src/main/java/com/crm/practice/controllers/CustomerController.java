package com.crm.practice.controllers;


import com.crm.practice.models.Customer;
import com.crm.practice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customers")
public class CustomerController {


    @Autowired
    private CustomerRepository customerRepository;



    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }


    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(updatedCustomer.getName());
                    customer.setEmail(updatedCustomer.getEmail());
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }


    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id);

    }

}
