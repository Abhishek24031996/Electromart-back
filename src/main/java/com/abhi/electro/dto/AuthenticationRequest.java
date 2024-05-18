package com.abhi.electro.dto;


import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class AuthenticationRequest {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
