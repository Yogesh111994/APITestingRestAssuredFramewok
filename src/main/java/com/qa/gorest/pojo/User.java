package com.qa.gorest.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User{
	
	private Integer id;
	private String name;
	private String gender;
	private String email;
	private String status;
public User(String name, String gender, String email, String status) {
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.status = status;
	}

}

