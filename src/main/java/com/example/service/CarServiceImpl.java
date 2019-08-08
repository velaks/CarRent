package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Car;
import com.example.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private final CarRepository carRep;
	
	public CarServiceImpl(CarRepository carRep) {
		this.carRep = carRep;
	}

	@Override
	public List<Car> findAllCars() {
		return carRep.findAll();
	}
	
	@Override
	public Car findCarById(Long id) {
		return carRep.findById(id).get();
	}
	
	@Override
	public void saveCar(Car car) {
		carRep.save (car);
		
	}

	@Override
	public boolean ifExistsByBrand(String brand) {
		return carRep.existsByBrand(brand);
	}

	@Override
	public Car findCarByBrand(String brand) {
		return carRep.findByBrand(brand);
	}

}
