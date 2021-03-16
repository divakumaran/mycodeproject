package com.genesys.techtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.genesys.techtest.dto.UserDto;
import com.genesys.techtest.model.UserModel;
import com.genesys.techtest.service.UserService;
/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestUserServiceImpl{
	
	@Mock
	UserService userService;
	
	@Before
    public void init() {
		UserDto userDto = new UserDto("ABBCDCBBC","TestUser","testuser@test.com","testPass",null,"TestAdmin",new Timestamp(System.currentTimeMillis()), null , null);
		when(userService.findByEmail("testuser@test.com")).thenReturn(userDto);
		when(userService.login("testuser@test.com", "testPass")).thenReturn(true);
    }
	
	@Test
	public void testfindByEmail() {

		UserDto userDto  = userService.findByEmail("testuser@test.com");
		
		ModelMapper modelMapper = new ModelMapper();

		UserModel user = modelMapper.map(userDto, UserModel.class);

         
        assertEquals("TestUser", user.getUserName());
        assertEquals("testuser@test.com", user.getUserEmail());
        assertNull(user.getLastLoginDate());
    }
	
	
	@Test
	public void testLogin() {

		boolean isLoggedIn  = userService.login("testuser@test.com", "testPass");
         
        assertTrue(isLoggedIn);
    }
	
	@Test
	public void testBadLogin() {

		boolean isLoggedIn  = userService.login("testuser@test.com", "password");
         
        assertFalse(isLoggedIn);
    }
		
}
