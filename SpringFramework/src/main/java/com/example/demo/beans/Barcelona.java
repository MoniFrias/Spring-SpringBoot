package com.example.demo.beans;

import com.example.demo.interfaces.ITeam;

public class Barcelona implements ITeam{

	@Override
	public String showTeam() {
		return "Barcelona FC";
	}

}
