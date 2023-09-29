package com.example.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	
	private boolean result=true;
	private String message="success";
	private Object data;

}
