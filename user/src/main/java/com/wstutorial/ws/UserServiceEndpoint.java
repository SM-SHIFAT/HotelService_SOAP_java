
package com.wstutorial.ws;

import com.wstutorial.ws.generated.getuser.GetUserRequest;
import com.wstutorial.ws.generated.getuser.GetUserResponse;
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
import java.util.Random;

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
	public SignupResponse signup(@RequestPayload SignupRequest req) {
		User u = userRepository.getUser(req.getUsername());
		SignupResponse response = new SignupResponse();
		if(u!=null){
			// System.out.println("Here1");
			response.setSuccess(false);
			return response;
		}
		u = new User();

		if(req.getUsername() == null || req.getPassword() == null){
			response.setSuccess(false);
			// System.out.println("Here2");
			return response;
		}

		 int randomNumber = 1000 + userRepository.getListSize(); // Generate a 4-digit random number
		 String uid = "uid" + String.valueOf(randomNumber);
		// System.out.println("Here3");
		u.setUsername(req.getUsername());
		u.setPassword(req.getPassword());
		u.setUniqueid(uid);
		userRepository.setUser(u);
		response.setSuccess(true);
		response.setUser(u);
		return response;
	}

	@PayloadRoot(namespace = "http://hotelservice.com/getuser", localPart = "GetUserRequest")
	@ResponsePayload
	public GetUserResponse getUser(@RequestPayload GetUserRequest req) {
		User u;
		GetUserResponse response = new GetUserResponse();

		if(!req.getUsername().isEmpty()){
		//	System.out.println("here1"+ req.getUsername());
		 u = userRepository.getUser(req.getUsername());
		}
		else if (!req.getUid().isEmpty()){
		//	System.out.println("here2");
			u =	userRepository.getUserByUid(req.getUid());
		}
		else{
		//	System.out.println("here3");
			response.setSuccess(false);
			return response;
		}

		if (u == null ) {
		//	System.out.println("here4");
			response.setSuccess(false);
			return response;
		}
		response.setSuccess(true);
		response.setUser(u);
		return response;
	}

	
}