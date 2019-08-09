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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about client")
@Entity
public class Customer {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore 
	private Long id;
	@ApiModelProperty(notes = "Name of client")
	private String name;
	@ApiModelProperty(notes = "Birth year")
	private int year;
	@JsonIgnore 
	private String brand;
	
	@OneToOne(cascade = {CascadeType.PERSIST, 
						CascadeType.REFRESH, 
						CascadeType.MERGE,
						CascadeType.DETACH})
	@JoinColumn(name = "car_id")
	private Car car;
	
	public Customer() {

	}
	

	public Customer(Long id, String name, String brand, int year, Car car) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.year = year;
		this.car = car;
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
