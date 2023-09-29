package com.example.demo.beans;

public class Person4  {

	private int id;
	private String name;
	private String lastName;
	private Country country;
	private City city;

	
	private void initBean() {
		System.out.println("Before start bean person");
	}
	
	private void destroyBean() {
		System.out.println("Bean about to be destroyed person");
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
