/**
 * 
 */
package com.genesys.techtest.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genesys.techtest.dto.UserDto;
import com.genesys.techtest.exception.ResourceNotFoundException;
import com.genesys.techtest.model.UserModel;
import com.genesys.techtest.service.UserService;

/**
 * @author diva
 *
 */

@RestController
@Validated
@RequestMapping("/api")
public class UserDataController {

	@Autowired
    private UserService service;
	
	@GetMapping("/users")
	public List<UserDto> list() {
	    return service.listAll();
	}
	
	@GetMapping("/user/{useremail}")
	public ResponseEntity<?> getUserByEmail(@Email @PathVariable("useremail") String useremail) {
	    try {
			UserDto userDto = service.findByEmail(useremail);
			
			if(userDto == null){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not found with given email " + useremail);
			}
			ModelMapper modelMapper = new ModelMapper();

			UserModel user = modelMapper.map(userDto, UserModel.class);

			//show the result as json object
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
	    catch(ResourceNotFoundException ex) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    } catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
		}
	}
	
	@PostMapping(value="/addusers")
    public ResponseEntity<String> addUser( @Valid @RequestBody UserModel user) {

        try {
			service.addNewUser(user);
			return ResponseEntity.status(HttpStatus.OK).body("User added successfully");
		} catch(ResourceNotFoundException ex) {
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    } catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Failed processing User add request");
		}
     }
	
	@PostMapping(value="/updateuser")
    public ResponseEntity<String> updateUser( @Valid @RequestBody UserModel user) {

        try {
			service.updateUser(user);
			return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
		}  catch(ResourceNotFoundException ex) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Failed processing User add request");
		}
     }
	
	@PostMapping(value="/login")
    public ResponseEntity<String> login( @Valid  @RequestParam @Email String  useremail ,@NotNull @NotEmpty String password ) {

        try {
			boolean isLoginSucess = service.login(useremail, password);
			if(isLoginSucess) {
				return ResponseEntity.status(HttpStatus.OK).body("User logged in successfully");
			}else {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("User logged in successfully");
			}
		}  catch(ResourceNotFoundException ex) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed processing User add request");
		}
     }
	
	@DeleteMapping("/user/delete/{useremail}")
	public ResponseEntity<?> deleteUser(@PathVariable  @Email String useremail) {
	    try {
			UserDto userDto = service.findByEmail(useremail);
			
			if(userDto == null){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not found with given email " + useremail);
			}
			service.delete(userDto.getUserId());
			return ResponseEntity.status(HttpStatus.OK).body("User deleted Successfully");
		}
	    catch(ResourceNotFoundException ex) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    } catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
		}
	}
	
	
}
