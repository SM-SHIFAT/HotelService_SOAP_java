
package com.wstutorial.ws;

import com.wstutorial.ws.generated.login.*;
import com.wstutorial.ws.generated.signup.SignupRequest;
import com.wstutorial.ws.generated.signup.SignupResponse;
import com.wstutorial.ws.generated.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;

@Endpoint
public class UserServiceEndpoint {
	private final UserRepository userRepository;

	@Autowired
	public UserServiceEndpoint(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PayloadRoot(namespace = "http://hotelservice.com/login", localPart = "LoginRequest")
	@ResponsePayload
	public LoginResponse login(@RequestPayload LoginRequest loginRequest) {
		User u = userRepository.getUser(loginRequest.getUsername());
		LoginResponse response = new LoginResponse();
		if (u == null || !u.getPassword().equals(loginRequest.getPassword())) {
			response.setSuccess(false);
			return response;
		}
		response.setSuccess(true);
		response.setUser(u);
		return response;
	}

	@PayloadRoot(namespace = "http://hotelservice.com/signup", localPart = "SignupRequest")
	@ResponsePayload
	public SignupResponse signup(@RequestPayload SignupRequest signupRequest) {
		User u = userRepository.getUser(signupRequest.getUsername());
		SignupResponse response = new SignupResponse();
		if(u!=null){
			response.setSuccess(false);
			return response;
		}
		u = new User();
		response.setSuccess(true);

		u.setUsername(signupRequest.getUsername());
		u.setPassword(signupRequest.getPassword());
		u.setUniqueid(String.valueOf(random()));
		userRepository.setUser(u);
		response.setUser(u);
		return response;
	}

	
}