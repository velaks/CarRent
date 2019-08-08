package com.example.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore 
	private Long id;
	private String name;
	@JsonIgnore 
	private String brand;
	private int year;
	
	@OneToOne(cascade = {CascadeType.PERSIST, 
						CascadeType.REFRESH, 
						CascadeType.MERGE,
						CascadeType.DETACH})
	@JoinColumn(name = "car_id")
	private Car car;
	
	public Customer() {

	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		getCar().setClient(name);
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
		setBrand(car.getBrand());
	}
	
	
}
