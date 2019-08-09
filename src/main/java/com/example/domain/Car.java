package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about car")
@Entity
public class Car {
	@Id @GeneratedValue
	@Column(name = "id")
	@JsonIgnore 
	private Long id;
	@ApiModelProperty(notes = "Brand of car")
	private String brand;
	@ApiModelProperty(notes = "Year of manufacture")
	private int year;
	@JsonIgnore 
	private String client;
	
	@OneToOne(mappedBy = "car")
	private Customer owner;
	
	public Car() {
	
	}
	

	public Car(Long id, String brand, int year, String client) {
		super();
		this.id = id;
		this.brand = brand;
		this.year = year;
		this.client = client;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Customer getOwner() {
		return owner;
	}
	public void setOwner(Customer owner) {
		this.owner = owner;
	}
}
