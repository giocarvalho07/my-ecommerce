package com.mycommerce.service;

import com.mycommerce.model.Customer;
import com.mycommerce.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(Customer customer) {
        // Erro 21:52 corrigido (getEmail)
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new RuntimeException("A customer with this email address already exists.");
        }
        // Erros 24:17 e 25:17 corrigidos (setDateRegistration e setActive)
        customer.setDateRegistration(LocalDateTime.now());
        customer.setActive(true);
        return customerRepository.save(customer);
    }

    public Customer searchById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
    }

    public List<Customer> listActivesCustomer() {
        return customerRepository.findByActiveTrue();
    }

    public Customer updateCustomer(Long id, Customer customerUpdate) {
        Customer customerExist = searchById(id);

        // Erros 42:27 e 42:60 corrigidos (getEmail)
        if (!customerExist.getEmail().equals(customerUpdate.getEmail()) &&
                customerRepository.findByEmail(customerUpdate.getEmail()).isPresent()) {
            throw new RuntimeException("The new email address is already in use by another customer.");
        }

        // Erros 47 a 50 corrigidos (getName, getEmail, getPhone, getAddress)
        customerExist.setName(customerUpdate.getName());
        customerExist.setEmail(customerUpdate.getEmail());
        customerExist.setPhone(customerUpdate.getPhone());
        customerExist.setAddress(customerUpdate.getAddress());

        return customerRepository.save(customerExist);
    }

    public void inactivateCustomer(Long id) {
        Customer customer = searchById(id);
        // Erro 57:17 corrigido (setActive)
        customer.setActive(false);
        customerRepository.save(customer);
    }
}