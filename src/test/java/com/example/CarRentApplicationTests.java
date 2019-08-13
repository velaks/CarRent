package com.example;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.domain.Car;
import com.example.domain.Customer;
import com.example.repository.CarRepository;
import com.example.repository.CustomerRepository;
import com.example.service.CarService;
import com.example.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRentApplicationTests {
	
	@MockBean
	private CustomerRepository cusRep;
	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CarRepository carRep;
	@Autowired
	private CarService carService;
	

	@Test
	public void ifExistsTest() {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
		when(cusRep.existsByName(cus.getName())).thenReturn(true); 
		when(cusRep.existsByYear(cus.getYear())).thenReturn(true);
		assertEquals(true, customerService.ifExists(cus));
	}
	@Test
	public void ifExistsByNameTest() {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
        when(cusRep.existsByName(cus.getName())).thenReturn(true);
		assertEquals(true, customerService.ifExistsByName(cus.getName()));
	}
	@Test
	public void ifExistsByBrandTest() {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
        when(carRep.existsByBrand(car.getBrand())).thenReturn(true);
		assertEquals(true, carService.ifExistsByBrand(car.getBrand()));
	}
	@Test
	public void findCarByBrandTest() {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
        when(carRep.findByBrand(car.getBrand())).thenReturn(car);
		assertEquals(car, carService.findCarByBrand(car.getBrand()));
	}
	@Test
	public void findCustomerByNameTest() {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
        when(cusRep.findByName(cus.getName())).thenReturn(cus);
		assertEquals(cus, customerService.findCustomerByName(cus.getName()));
	}
	@Test
	public void deleteCustomerTest() throws Exception {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
        customerService.delete(cus.getId());
        verify(cusRep, times(1)).deleteById(cus.getId());
	}
	@Test
	public void saveCustomerTest() throws Exception {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
        customerService.saveCustomer(cus);
        verify(cusRep, times(1)).save(cus);
	}
	
}
