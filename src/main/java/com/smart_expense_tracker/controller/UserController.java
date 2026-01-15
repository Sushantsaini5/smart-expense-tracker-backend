package com.smart_expense_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_expense_tracker.dto.LoginRequestDTO;
import com.smart_expense_tracker.dto.LoginResponseDTO;
import com.smart_expense_tracker.dto.UserRequestDTO;
import com.smart_expense_tracker.model.User;
import com.smart_expense_tracker.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserRequestDTO request){
		
		return ResponseEntity.ok(userService.register(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request){
		String token=userService.login(request);
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

}
