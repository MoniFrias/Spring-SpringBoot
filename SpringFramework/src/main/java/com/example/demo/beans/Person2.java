package com.example.demo.beans;


public class Person2 {

	private int id;
	private String name;
	private String lastName;
	private Country2 country;

	public Country2 getCountry() {
		return country;
	}

	public void setCountry(Country2 country) {
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
