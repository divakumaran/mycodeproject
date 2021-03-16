/**
 * 
 */
package com.genesys.techtest.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genesys.techtest.dto.UserDto;

/**
 * @author diva
 *
 */
public interface UserRepository extends JpaRepository<UserDto, String> {
	
	List<UserDto> findByUserEmail(String email);
	
	List<UserDto> findByUserEmailAndUserPass(String email , String password);

}
