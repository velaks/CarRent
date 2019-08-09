package com.example.service;

import com.example.domain.Customer;

public interface CustomerService {
	Customer findCustomerById(Long id);
	void saveCustomer(Customer customer);
	boolean ifExists(Customer customer);
	boolean ifExistsByName(String name);
	Customer findCustomerByName(String name);
	void delete(Long id);

}
