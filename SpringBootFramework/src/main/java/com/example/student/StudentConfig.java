//package com.example.student;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class StudentConfig {
//
//	@Bean
//	CommandLineRunner commandLineRunner(StudentRepository repository) {
//		return args -> {
//				Student mariana = new Student( 
//							"Mariana", 
//							"mariana@email.com", 
//							LocalDate.of(2000, Month.MARCH, 22),
//							23
//				);
//				
//				Student luis = new Student( 
//						"Luis", 
//						"luis@email.com", 
//						LocalDate.of(1987, Month.APRIL, 2),
//						36
//				);
//				
//				repository.saveAll(
//						List.of(mariana, luis));					
//		};
//		
//		
//	}
//}
