/**
 * 
 */
package com.genesys.techtest.service;

import java.util.List;

import com.genesys.techtest.dto.UserDto;
import com.genesys.techtest.model.UserModel;

/**
 * @author diva
 *
 */
public interface UserService {

	public List<UserDto> listAll() ;
     
    public void addNewUser(UserModel user);
    
    public void updateUser(UserModel user);
     
    public UserDto get(String userId) ;
     
    public void delete(String userId);
    
    public UserDto findByEmail(String email);
    
    public boolean  login(String email, String password);
}
