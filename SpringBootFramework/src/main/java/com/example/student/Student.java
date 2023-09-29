package com.example.student;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	private Integer age;
	
	public Student(String name, String email, LocalDate dob, Integer age) {
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.age = age;
	}
	
	
	
	
	
}
