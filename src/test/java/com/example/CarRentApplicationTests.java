package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.Controller;
import com.example.domain.Car;
import com.example.domain.Customer;
import com.example.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarRentApplicationTests {
	
	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerRepository mCus;
	
	@Before
    public void init() {
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);
    }

	@Test
    public void testAdd() throws Exception {
		
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);

        mockMvc.perform(post("/rent/add")
                .content(om.writeValueAsString(cus))
                .accept(MediaType.APPLICATION_JSON))
                /*.andDo(print())*/
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Sasha")))
                .andExpect(jsonPath("$.brand", is("Maz")))
                .andExpect(jsonPath("$.year", is(80)));
                
    }
	
	@Test
    public void testAdd2() throws Exception {
		
		Car car = new Car(2L, "Maz", 85, "Sasha");
        Customer cus = new Customer(1L, "Sasha", "Maz", 80, car);

        mockMvc.perform(post("/rent/add")
                .content(om.writeValueAsString(cus))
                .contentType(MediaType.APPLICATION_JSON))
                /*.andDo(print())*/
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Sasha")))
                .andExpect(jsonPath("$.brand", is("Maz")))
                .andExpect(jsonPath("$.year", is(80)))
                .andExpect(jsonPath("$.car", is(car)));
                
    }

}
