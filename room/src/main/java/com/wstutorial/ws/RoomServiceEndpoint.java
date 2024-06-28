
package com.wstutorial.ws;


import com.wstutorial.ws.generated.bookroom.BookRoomRequest;
import com.wstutorial.ws.generated.bookroom.BookRoomResponse;
import com.wstutorial.ws.generated.checkoutroom.CheckoutRoomRequest;
import com.wstutorial.ws.generated.checkoutroom.CheckoutRoomResponse;
import com.wstutorial.ws.generated.getallrooms.GetAllRoomsResponse;
import com.wstutorial.ws.generated.room.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import java.util.List;

@Endpoint
public class RoomServiceEndpoint {
	private final RoomRepository roomRepository;

	@Autowired
	public RoomServiceEndpoint(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	@PayloadRoot(namespace = "http://hotelservice.com/getallrooms", localPart = "GetAllRoomsRequest")
	@ResponsePayload
	public GetAllRoomsResponse getAllRoom() {
		List<RoomType> roomList= roomRepository.getAllRoom();
		GetAllRoomsResponse response = new GetAllRoomsResponse();
		response.setRooms(roomList);
		return response;
	}

	@PayloadRoot(namespace = "http://hotelservice.com/bookroom", localPart = "BookRoomRequest")
	@ResponsePayload
	public BookRoomResponse bookRoom(@RequestPayload BookRoomRequest request) {
		roomRepository.bookRoom(request.getRoomNumber());
		BookRoomResponse response = new BookRoomResponse();
		response.setSuccess(true);
		return response;
	}

	@PayloadRoot(namespace = "http://hotelservice.com/checkoutroom", localPart = "CheckoutRoomRequest")
	@ResponsePayload
	public CheckoutRoomResponse checkoutRoom(@RequestPayload CheckoutRoomRequest request) {
		roomRepository.checkoutRoom(request.getRoomNumber());
		CheckoutRoomResponse response = new CheckoutRoomResponse();
		response.setSuccess(true);
		return response;
	}


//	@PayloadRoot(namespace = "http://hotelservice.com/signup", localPart = "SignupRequest")
//	@ResponsePayload
//	public SignupResponse signup(@RequestPayload SignupRequest signupRequest) {
//		User u = userRepository.getUser(signupRequest.getUsername());
//		SignupResponse response = new SignupResponse();
//		if(u!=null){
//			response.setSuccess(false);
//			return response;
//		}
//		u = new User();
//		response.setSuccess(true);
//
//		u.setUsername(signupRequest.getUsername());
//		u.setPassword(signupRequest.getPassword());
//		u.setUniqueid(String.valueOf(random()));
//		userRepository.setUser(u);
//		response.setUser(u);
//		return response;
//	}

	
}