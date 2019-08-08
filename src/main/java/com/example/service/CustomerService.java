package com.example.service;

import java.util.List;
import com.example.domain.Customer;

public interface CustomerService {
	Customer findCustomerById(Long id);
	void saveCustomer(Customer customer);
	List<Customer> findAllCustomers();
	boolean ifExists(Customer customer);
	boolean ifExistsByName(String name);
	Customer findCustomerByName(String name);
	void delete(Long id);

}
