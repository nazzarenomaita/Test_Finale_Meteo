package com.nazzareno.it.service;

import java.util.Optional;

import com.nazzareno.it.dto.UserDTO;
import com.nazzareno.it.dto.UserLoginDTO;
import com.nazzareno.it.dto.UserSignupDTO;
import com.nazzareno.it.model.User;

public interface UserService {
    UserDTO signUp(UserSignupDTO userSignupDTO);
    UserDTO signIn(UserLoginDTO userLoginDTO);
    User findById(Long id);
	User getUserByEmail(String email);
}
