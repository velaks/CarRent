package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.domain.Car;
import com.example.domain.Customer;
import com.example.service.CarService;
import com.example.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarRentApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CustomerService service;
	@MockBean
	private CustomerService mockService;
	
	@Autowired
	private CarService carService;
	@MockBean
	private CarService mockCarService;
	
	@Test
	public void ifExistsTest() {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
		when(mockService.ifExists(cus)).thenReturn(false);
		assertEquals(false, service.ifExists(cus));
	}
	@Test
	public void ifExistsByNameTest() {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
        when(mockService.ifExistsByName(cus.getName())).thenReturn(false);
		assertEquals(false, service.ifExistsByName(cus.getName()));
	}
	@Test
	public void ifExistsByBrandTest() {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
        when(mockCarService.ifExistsByBrand(car.getBrand())).thenReturn(false);
		assertEquals(false, carService.ifExistsByBrand(car.getBrand()));
	}
	@Test
	public void findCarByBrandTest() {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
        when(mockCarService.findCarByBrand(car.getBrand())).thenReturn(car);
		assertEquals(car, carService.findCarByBrand(car.getBrand()));
	}
	@Test
	public void findCustomerByNameTest() {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
        when(mockService.findCustomerByName(cus.getName())).thenReturn(cus);
		assertEquals(cus, service.findCustomerByName(cus.getName()));
	}
	@Test
	public void deleteCustomerTest() throws Exception {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
		service.delete(cus.getId());
		verify(mockService, times(1)).delete(cus.getId());
	}
	@Test
	public void saveCustomerTest() throws Exception {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
        mockService.saveCustomer(cus);
        verify(service, times(1)).saveCustomer(cus);
	}
	@Test
	public void deleteTest() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/rent/delete")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
	}
}
