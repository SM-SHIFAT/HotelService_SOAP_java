
package com.wstutorial.ws;

import com.hotelservice.payment.MakePaymentResponse;
import com.hotelservice.payment.PaymentRequest;
import com.wstutorial.ws.generated.room.RoomType;
import com.wstutorial.ws.roomclients.RoomClients;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@Endpoint
public class PaymentServiceEndpoint {
//	private final RoomRepository roomRepository;

    private static final String NAMESPACE_URI = "http://hotelservice.com/payment";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PaymentRequest")
    @ResponsePayload
    public MakePaymentResponse makePayment(@RequestPayload PaymentRequest request) {
        MakePaymentResponse response = new MakePaymentResponse();
        boolean paymentSuccess = processPayment(request);
        response.setSuccess(paymentSuccess);
        return response;
    }

    private boolean processPayment(PaymentRequest request) {
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