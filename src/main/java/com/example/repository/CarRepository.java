package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

	boolean existsByBrand(String brand);

	Car findByBrand(String brand);

}
