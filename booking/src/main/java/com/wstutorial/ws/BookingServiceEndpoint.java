
package com.wstutorial.ws;

import com.wstutorial.ws.generated.availablerooms.GetAvailableRoomsResponse;
import com.wstutorial.ws.generated.booking.BookingRequest;
import com.wstutorial.ws.generated.booking.BookingResponse;
import com.wstutorial.ws.generated.checkout.CheckouResponse;
import com.wstutorial.ws.generated.checkout.CheckoutRequest;
import com.wstutorial.ws.generated.room.RoomType;
import com.wstutorial.ws.roomclients.RoomClients;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Endpoint
public class BookingServiceEndpoint {
//	private final RoomRepository roomRepository;

    private static final String NAMESPACE_URI = "http://hotelservice.com/booking";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BookingRequest")
    @ResponsePayload
    public BookingResponse giveBooking(@RequestPayload BookingRequest request) {
        BookingResponse response = new BookingResponse();
        boolean bookingtSuccess = processBooking(request);
        response.setSuccess(bookingtSuccess);
        return response;
    }

    @PayloadRoot(namespace = "http://hotelservice.com/checkout", localPart = "CheckoutRequest")
    @ResponsePayload
    public CheckouResponse makeCheckout(@RequestPayload CheckoutRequest request) {
        CheckouResponse response = new CheckouResponse();
        boolean checkoutSuccess = processCheckout(request);
        response.setSuccess(checkoutSuccess);
        return response;
    }

    @PayloadRoot(namespace = "http://hotelservice.com/availablerooms", localPart = "GetAvailableRoomsRequest")
    @ResponsePayload
    public GetAvailableRoomsResponse getAvailableRooms() {
        GetAvailableRoomsResponse response = new GetAvailableRoomsResponse();
        List<RoomType> roomList = getAvailableRoomsList();
        response.setRooms(roomList);
        return response;
    }

    private List<RoomType> getAvailableRoomsList() {
        try {
            List<RoomType> roomList = RoomClients.getAllRooms();
            List<RoomType> availableRoomList = new ArrayList<RoomType>();


            for (RoomType room : roomList) {
                if (room.isIsAvailable()) {
                    availableRoomList.add(room);
                }
            }

           return availableRoomList;


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean processBooking(BookingRequest request) {
        try {
            List<RoomType> roomList = RoomClients.getAllRooms();
            int roomNumber = request.getRoomNumber();
            boolean isAvailable = false;

            System.out.print("Room number:"+ roomNumber);

            for (RoomType room : roomList) {
                if (room.getRoomNumber() == roomNumber) {
                    isAvailable = room.isIsAvailable();
                }
            }



            if (isAvailable){
                System.out.print("Room available. Payment success");
                String userId =request.getUserId();
                String date =request.getDate();
                int roomNum =request.getRoomNumber();
                int amount =request.getAmount();

                boolean s = RoomClients.paymentRequest(userId,roomNum,amount,date);
                if(!s){
                    return false;
                }
                RoomClients.bookRoomAfterPay(roomNumber);
                System.out.print("Room booked: "+ roomNumber);
                return true;
            }
            else{
                System.out.print("Room is not available. Payment failed");
                return false;

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean processCheckout(CheckoutRequest request) {
        try {
            List<RoomType> roomList = RoomClients.getAllRooms();
            int roomNumber = request.getRoomNumber();
            boolean isAvailable = true;

            System.out.print("Room number:"+ roomNumber);

            for (RoomType room : roomList) {
                if (room.getRoomNumber() == roomNumber) {
                    isAvailable = room.isIsAvailable();
                }
            }

            if (!isAvailable){

                RoomClients.checkoutRoom(roomNumber);
                System.out.print("Room checkout success: "+ roomNumber);
                return true;
            }
            else{
                System.out.print("Room is not booked. Checkout failed");
                return false;

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

    }

//	@Autowired
//	public RoomServiceEndpoint(RoomRepository roomRepository) {
//		this.roomRepository = roomRepository;
//	}

//	@PayloadRoot(namespace = "http://hotelservice.com/getallrooms", localPart = "GetAllRoomsRequest")
//	@ResponsePayload
//	public GetAllRoomsResponse getAllRoom() {
//		List<RoomType> roomList= roomRepository.getAllRoom();
//		GetAllRoomsResponse response = new GetAllRoomsResponse();
//		response.setRooms(roomList);
//		return response;
//	}

//	@PayloadRoot(namespace = "http://hotelservice.com/bookroom", localPart = "BookRoomRequest")
//	@ResponsePayload
//	public BookRoomResponse bookRoom(@RequestPayload BookRoomRequest request) {
//		roomRepository.bookRoom(request.getRoomNumber());
//		BookRoomResponse response = new BookRoomResponse();
//		response.setSuccess(true);
//		return response;
//	}

//	@PayloadRoot(namespace = "http://hotelservice.com/checkoutroom", localPart = "CheckoutRoomRequest")
//	@ResponsePayload
//	public CheckoutRoomResponse checkoutRoom(@RequestPayload CheckoutRoomRequest request) {
//		roomRepository.checkoutRoom(request.getRoomNumber());
//		CheckoutRoomResponse response = new CheckoutRoomResponse();
//		response.setSuccess(true);
//		return response;
//	}


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