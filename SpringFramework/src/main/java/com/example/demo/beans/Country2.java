package com.example.demo.beans;

import java.util.List;

public class Country2 {
	
	private String name;	
	private List<City> citys;
	

	public List<City> getCitys() {
		return citys;
	}

	public void setCitys(List<City> citys) {
		this.citys = citys;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
