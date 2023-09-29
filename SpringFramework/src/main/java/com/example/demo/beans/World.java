package com.example.demo.beans;

import org.springframework.beans.factory.annotation.Value;

public class World {
	
	@Value("Hello world!")
	private String msj;

	public String getMsj() {
		return msj;
	}

	public void setMsj(String msj) {
		this.msj = msj;
	}
	

}
