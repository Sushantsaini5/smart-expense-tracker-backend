package com.smart_expense_tracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
	
	@NotBlank(message = "Name Required")
	private String name;
	
	@Email(message = "Invalid Email")
    private String email;
    
	@Size(min = 8, message = "Password must be minimum 8 characters")
    private String password;

}
