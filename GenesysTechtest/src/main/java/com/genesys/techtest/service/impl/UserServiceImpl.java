/**
 * 
 */
package com.genesys.techtest.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesys.techtest.dto.UserDto;
import com.genesys.techtest.exception.ResourceNotFoundException;
import com.genesys.techtest.jpa.UserRepository;
import com.genesys.techtest.model.UserModel;
import com.genesys.techtest.service.UserService;

/**
 * @author diva
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	public List<UserDto> listAll() {
		return userRepo.findAll();
	}

	public void addNewUser(UserModel user) {

		UserDto userDto = userRepo.findByUserEmail(user.getUserEmail()).stream().findFirst().orElse(null);
		if (userDto != null) { // Update existing User
			throw new  ResourceNotFoundException("User with email :" + user.getUserEmail() + " already exists!");
		} else {
			userDto = new UserDto();
			userDto.setUserId(UUID.randomUUID().toString());
			userDto.setUserEmail(user.getUserEmail());
			userDto.setUserName(user.getUserName());
			userDto.setUserPass(user.getUserPass());
			userDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			userDto.setCreatedBy("API User"); // Update based on the API authentication
			userDto.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			userDto.setUpdatedBy("API User"); // Update based on the API authentication
			userRepo.save(userDto);
		}
	}

	public void updateUser(UserModel user) {
		UserDto userDto = findByEmail(user.getUserEmail());
		// Update existing User
		userDto.setUserName(user.getUserName());
		userDto.setUserPass(user.getUserPass());
		userDto.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		userDto.setUpdatedBy("API User"); // Update based on the API authentication
		userRepo.save(userDto);
	}

	public UserDto get(String userId) {
		return userRepo.findById(userId).get();
	}

	public void delete(String userId) {
		userRepo.deleteById(userId);
	}

	public UserDto findByEmail(String email) {
		return userRepo.findByUserEmail(email).stream().findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("User with email :" + email + " Not Found!"));
	}

	@Override
	public boolean login(String email, String password) {
		
		UserDto userDto = userRepo.findByUserEmailAndUserPass(email, password).stream().findFirst()
		.orElseThrow(() -> new ResourceNotFoundException("Invalid Username or Password"));
		userDto.setLastLoginDate(new Timestamp(System.currentTimeMillis()));
		userRepo.save(userDto);
		return true;
	}
};
