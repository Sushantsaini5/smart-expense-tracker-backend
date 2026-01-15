package com.smart_expense_tracker.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart_expense_tracker.dto.LoginRequestDTO;
import com.smart_expense_tracker.dto.UserRequestDTO;
import com.smart_expense_tracker.model.User;
import com.smart_expense_tracker.repository.UserRepository;
import com.smart_expense_tracker.security.JwtUtil;
import com.smart_expense_tracker.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private final JwtUtil jwtUtil;
	@Override
	public User register(UserRequestDTO request) {
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists!");
		}

		User user = User.builder().name(request.getName()).email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).build();

		return userRepository.save(user);
	}

	@Override
	public String login(LoginRequestDTO request) {
		User user= userRepository.findByEmail(request.getEmail())
				.orElseThrow(()->new RuntimeException("Invalid email or password"));
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid email or password");
		}
		return jwtUtil.generateToken(user.getEmail()); 
	}

}
