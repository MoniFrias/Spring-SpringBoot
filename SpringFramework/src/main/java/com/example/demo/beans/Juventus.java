package com.example.demo.beans;

import com.example.demo.interfaces.ITeam;

public class Juventus implements ITeam {

	@Override
	public String showTeam() {
		return "Juventus";
	}
}
