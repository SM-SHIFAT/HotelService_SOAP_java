
package com.wstutorial.ws;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.wstutorial.ws.generated.addlog.AddLogRequest;
import com.wstutorial.ws.generated.addlog.AddLogResponse;
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
	//private final UserRepository userRepository;

	// @Autowired
	// public UserServiceEndpoint(UserRepository userRepository) {
	//	this.userRepository = userRepository;
	// }

	@PayloadRoot(namespace = "http://hotelservice.com/addlog", localPart = "AddLogRequest")
	@ResponsePayload
	public AddLogResponse login(@RequestPayload AddLogRequest req) {
		AddLogResponse response = new AddLogResponse();

		Date currentTime = new Date();
		// Create a SimpleDateFormat object to format the date
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		// Format the current time using the formatter
		String formattedTime = formatter.format(currentTime);

		System.out.println(formattedTime + ": " + req.getLogMessage());

		response.setSuccess(true);
		return response;
	}

	
}