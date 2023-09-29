package com.example.demo.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Person5 implements InitializingBean, DisposableBean {

	private int id;
	private String name;
	private String lastName;
	private Country country;
	private City city;

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("BEFORE START BEAN PERSON");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("BEAN PERSON ABOUT TO BE DESTROYED");
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
