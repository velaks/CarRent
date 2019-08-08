package com.example.service;

import java.util.List;

import com.example.domain.Car;

public interface CarService {
	Car findCarById(Long id);
	void saveCar(Car car);
	List<Car> findAllCars();
	boolean ifExistsByBrand(String brand);
	Car findCarByBrand(String brand);

}
