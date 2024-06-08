package com.abhi.electro.services.jwt.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abhi.electro.dto.SignupRequest;
import com.abhi.electro.dto.UserDto;
import com.abhi.electro.entity.Order;
import com.abhi.electro.entity.User;
import com.abhi.electro.enums.OrderStatus;
import com.abhi.electro.enums.UserRole;
import com.abhi.electro.repository.OrderRepository;
import com.abhi.electro.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public UserDto createUser(SignupRequest signupRequest) {
		User user = new User();
		
		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setRole(UserRole.CUSTOMER);
		User createdUser = userRepository.save(user);
		
		Order order = new Order();
		order.setAmount(0L);
		order.setTotalAmount(0L);
		order.setDiscount(0L);
		order.setUser(createdUser);
		order.setOrderStatus(OrderStatus.Pending);
		orderRepository.save(order);
		
		UserDto userDto = new UserDto();
		userDto.setId(createdUser.getId());
		
		return userDto;
	}
	
	public Boolean hasUserWithEmail(String email) {
		return userRepository.findFirstByEmail(email).isPresent();
	}
	
	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if(null == adminAccount) {
			User user = new User();
			user.setEmail("admin@test.com");
			user.setName("admin");
			user.setRole(UserRole.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
