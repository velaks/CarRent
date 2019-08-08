package com.example.controller;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.domain.Customer;
import com.example.service.CarService;
import com.example.service.CustomerService;

@RestController
@RequestMapping(produces = "application/json", path = "/rent")
public class Controller {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CarService carService;
	
	public Controller(CustomerService customerService, CarService carService) {
		this.customerService = customerService;
		this.carService = carService;
	}
	
	/**
	   * Post method for adding customers and cars.
	   */
	@PostMapping("/add")
	public Map<String, String> add(@RequestBody Customer customer) {
		
		if(customerService.ifExists(customer)) {
			return Collections.singletonMap("response", "This client is already in journal");
			
		} else {
			customerService.saveCustomer(customer);
			return Collections.singletonMap("response", "Client added");
		}
	}
	
	/**
	   * Delete method for deleting customers from journal.
	   */
	@DeleteMapping("delete/{name}/{brand}")
	public Map<String, String> delete(@PathVariable String name, @PathVariable String brand) {
		
		/**
		   * Checking if the DB already has such customer with rented car
		   */
		if(customerService.ifExistsByName(name)  && 
				carService.ifExistsByBrand(brand) && 
				(carService.findCarByBrand(brand).getId() == 
				customerService.findCustomerByName(name).getCar().getId())) {
			
			customerService.findCustomerByName(name).getCar().setClient(""); //Setting record about client in Car table to null
			customerService.delete(customerService.findCustomerByName(name).getId()); //Deleting the customer from Customer table
			
			return Collections.singletonMap("response", "Client has been deleted");
		} else {
			
			return Collections.singletonMap("response", "No data");
		}
	}
	
}
