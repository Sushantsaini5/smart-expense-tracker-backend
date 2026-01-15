package com.smart_expense_tracker.service;

import com.smart_expense_tracker.dto.LoginRequestDTO;
import com.smart_expense_tracker.dto.UserRequestDTO;
import com.smart_expense_tracker.model.User;

public interface UserService {
	
	User register(UserRequestDTO request);
	
	String login(LoginRequestDTO request);


}
