package io.dxnet.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.dxnet.AbstractTest;
import io.dxnet.dto.CustomUserRequestDto;
import io.dxnet.service.CustomUserService;


@SpringBootTest
public class HelloControllerTest extends AbstractTest{
	public static final String API_ROOT = "/api/v1";
	
	@Autowired
	CustomUserService customUserService;
	
    @Test
    void test_should_Not_Allow_Access_To_Unauthenticated_Users() throws Exception {
    	mockMvc.perform(get(API_ROOT + "/hello"))
        		.andExpect(status().isUnauthorized());
    }
    
    @Test
    void test_should_Allow_Access_To_Authenticated_Users() throws Exception {
    	customUserService.createUser(new CustomUserRequestDto("kundan", "password"));
    	String token = customUserService.createAuthToken(new CustomUserRequestDto("kundan", "password"));
    	
    	assertNotNull(token);
    	
    	mockMvc.perform(get(API_ROOT + "/hello")
    			.header("Authorization", "Bearer " + token))
    			.andExpect(status().isOk());
    }   
    
}
