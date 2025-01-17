package com.abhi.electro.controller;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.electro.dto.AuthenticationRequest;
import com.abhi.electro.dto.SignupRequest;
import com.abhi.electro.dto.UserDto;
import com.abhi.electro.entity.User;
import com.abhi.electro.repository.UserRepository;
import com.abhi.electro.services.jwt.auth.AuthService;
import com.abhi.electro.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.json.JSONObject;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	public static final String TOKEN_PREFIX = "Bearer " ;
	public static final String HEADER_STRING = "Authorization" ;
	private final AuthService authService ;

	public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
			UserRepository userRepository, JwtUtil jwtUtil, AuthService authService) {
		super();
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
		this.authService = authService;
	}

	@PostMapping("/authenticate")
	public  void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, 
			HttpServletResponse response) throws IOException, JSONException {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
					authenticationRequest.getPassword()));
			
		} catch (BadCredentialsException e) {
				throw new BadCredentialsException("Incorrect username or password");
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		Optional <User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		if (optionalUser.isPresent()) {
			response.getWriter().write(new JSONObject() 
			.put ("userId", optionalUser.get().getId())
			.put ("role", optionalUser.get().getRole())
			.toString()
			);
			response.addHeader("Access-Control-Expose-Headers", "Authorization");
		    response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin,"
		    		+ " X-Requested-With, Content-Type, Accept, X-Custom-header");
			response.addHeader(HEADER_STRING,  TOKEN_PREFIX + jwt);

		}
	}
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
		if (authService.hasUserWithEmail(signupRequest.getEmail())) {
			return new ResponseEntity<>("User already registered", HttpStatus.NOT_ACCEPTABLE);
		}
		
		UserDto userDto = authService.createUser(signupRequest);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
}
