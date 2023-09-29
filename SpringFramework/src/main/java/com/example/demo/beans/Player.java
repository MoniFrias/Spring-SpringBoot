package com.example.demo.beans;

import org.springframework.beans.factory.annotation.Required;

import com.example.demo.interfaces.ITeam;

public class Player {
	private int num;
	private String name;
	private ITeam team;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ITeam getTeam() {
		return team;
	}
	
	@Required
	public void setTeam(ITeam team) {
		this.team = team;
	}

	
}
