package com.example.demo.beans;

public class City2 {

	private String name;

	private void initBean() {
		System.out.println("Before start bean city");
	}
	
	private void destroyBean() {
		System.out.println("Bean about to be destroyed city");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
