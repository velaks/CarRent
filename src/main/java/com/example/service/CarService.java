package com.example.service;


import com.example.domain.Car;

public interface CarService {
	Car findCarById(Long id);
	void saveCar(Car car);
	boolean ifExistsByBrand(String brand);
	Car findCarByBrand(String brand);

}
