package com.example.dto.responseDto;

import java.util.List;

import lombok.Data;

@Data
public class AuthorResponseDto {
	
	private Long id;
    private String name;
    private String lastName;
    private List<String> bookNames;
    private String zipcodeName;

}
