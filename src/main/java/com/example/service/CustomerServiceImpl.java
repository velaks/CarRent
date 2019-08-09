package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private final CustomerRepository cusRep;
	
	public CustomerServiceImpl(CustomerRepository cusRep) {
		this.cusRep = cusRep;
	}
	
	@Override
	public Customer findCustomerById(Long id) {
		return cusRep.findById(id).get();
	}
	
	@Override
	public void saveCustomer(Customer customer) {
		cusRep.save (customer);
	}

	@Override
	public boolean ifExists(Customer customer) {
		return (cusRep.existsByName(customer.getName()) && 
				cusRep.existsByYear(customer.getYear()));
	}

	@Override
	public boolean ifExistsByName(String name) {
		return cusRep.existsByName(name);
	}
	
	@Override
	public Customer findCustomerByName(String name) {
		return cusRep.findByName(name);
	}

	@Override
	public void delete(Long id) {
		cusRep.deleteById(id);
		
	}
	
}
