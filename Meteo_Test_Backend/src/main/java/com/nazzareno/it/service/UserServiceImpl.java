package com.nazzareno.it.service;


import com.nazzareno.it.dao.UserDao;
import com.nazzareno.it.dto.UserDTO;
import com.nazzareno.it.dto.UserLoginDTO;
import com.nazzareno.it.dto.UserSignupDTO;
import com.nazzareno.it.model.User;
import com.nazzareno.it.service.UserService;

import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

 
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDTO signUp(UserSignupDTO userSignupDTO) {
        if (userDao.findByEmail(userSignupDTO.getEmail()) != null) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User();
        user.setNome(userSignupDTO.getNome());
        user.setCognome(userSignupDTO.getCognome());
        user.setEmail(userSignupDTO.getEmail());
        user.setPassword(DigestUtils.sha256Hex(userSignupDTO.getPassword()));
        user.setRuolo("USER");
        User savedUser = userDao.save(user);

        return new UserDTO();
    }

    @Override
    public UserDTO signIn(UserLoginDTO userLoginDTO) {
        User user = userDao.findByEmail(userLoginDTO.getEmail());

        if (user == null || !user.getPassword().equals(DigestUtils.sha256Hex(userLoginDTO.getPassword()))) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User findById(Long id) {
        User user = userDao.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user;
    }

	@Override
	public User getUserByEmail(String email) {
        User userOptional = userDao.findByEmail(email);
		return userOptional;
	}
}
