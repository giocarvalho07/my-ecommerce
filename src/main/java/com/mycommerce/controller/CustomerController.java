package com.mycommerce.controller;

import com.mycommerce.model.Customer;
import com.mycommerce.service.CustomerService;
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

    @PostMapping
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer){
        Customer newCustomer = customerService.registerCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> listCustomer(){
        List<Customer> customerList = customerService.listActivesCustomer();
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> searchByCustomerId(@PathVariable Long id){
        Customer customer = customerService.searchById(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer (@PathVariable Long id, @RequestBody Customer customer){
        Customer customerUpdate = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(customerUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.inactivateCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
