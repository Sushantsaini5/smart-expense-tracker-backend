package com.smart_expense_tracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;

}
