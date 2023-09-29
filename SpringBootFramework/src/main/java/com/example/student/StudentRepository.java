package com.example.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	//1. SELECT * FROM student WHERE email = ?
	//2. Optional<Student> findStudentByEmail(String email); 
	
	@Query("SELECT s FROM Student s Where s.email = ?1")
	Optional<Student> findStudentByEmail(String email); 
}
