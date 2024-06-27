
package com.wstutorial.ws;

import com.com.hotelservice.roomtype.RoomType;
import com.wstutorial.ws.hotelservice.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Endpoint
public class HotelServiceEndpoint {
	private static final String NAMESPACE_URI = "http://www.wstutorial.com/ws/HotelService";

	
//	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateTutorialRequest" )
//	@ResponsePayload
//	public UpdateTutorialResponse updateTutorial(@RequestPayload UpdateTutorialRequest request)throws Exception  {
//		ObjectFactory factory = new ObjectFactory();
//		StatusCode code = factory.createStatusCode();
//		UpdateTutorialResponse response = factory.createUpdateTutorialResponse();
//		code.setCode(200);
//		response.setStatusCode(code);
//		return response;
//	}
	
//	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteTutorialRequest" )
//	@ResponsePayload
//	public DeleteTutorialResponse deleteTutorial(@RequestPayload DeleteTutorialRequest request)throws Exception  {
//		System.out.println("-->deleteTutorial<--");
//		ObjectFactory factory = new ObjectFactory();
//		DeleteTutorialResponse response = factory.createDeleteTutorialResponse();
//		StatusCode code = factory.createStatusCode();
//		code.setCode(204);
//		response.setStatusCode(code);
//		return response;
//	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "CheckAvailabilityRequest" )
	@ResponsePayload
	public CheckAvailabilityResponse getTutorials(@RequestPayload CheckAvailabilityRequest request)throws Exception  {
		ObjectFactory factory = new ObjectFactory();
		CheckAvailabilityResponse response = factory.createCheckAvailabilityResponse();
		
		List<RoomType> tutorials = getTutorials();
		
		response.getAvailableRooms().addAll(tutorials);
		return response;
	}

	private List<RoomType> getTutorials() {
		List<RoomType> tutorials= new ArrayList<RoomType>();
		RoomType room1 = new RoomType();
		room1.setType("Regular");
		room1.setRoomNumber("1");
		room1.setPrice(BigDecimal.valueOf(1000.0));

		RoomType room2 = new RoomType();
		room2.setType("Regular");
		room2.setRoomNumber("1");
		room2.setPrice(BigDecimal.valueOf(1000.0));
		
		tutorials.add(room1);
		tutorials.add(room2);
		return tutorials;
	}
	
}