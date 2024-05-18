package com.abhi.electro.services.jwt.auth;

import com.abhi.electro.dto.SignupRequest;
import com.abhi.electro.dto.UserDto;

public interface AuthService {
	
	UserDto createUser(SignupRequest signupRequest);
	
	Boolean hasUserWithEmail(String email);

}
