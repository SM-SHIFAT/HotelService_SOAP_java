
package com.wstutorial.ws;

import com.wstutorial.ws.generated.getallstatement.GetAllStatementResponse;
import com.wstutorial.ws.generated.payment.PaymentResponse;
import com.wstutorial.ws.generated.payment.PaymentRequest;

import com.wstutorial.ws.generated.room.RoomType;
import com.wstutorial.ws.roomclients.*;
import org.springframework.beans.factory.annotation.Autowired;
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
	private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceEndpoint(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    private static final String NAMESPACE_URI = "http://hotelservice.com/payment";


    @PayloadRoot(namespace = "http://hotelservice.com/getallstatement", localPart = "GetAllStatementRequest")
    @ResponsePayload
    public GetAllStatementResponse getAllStatement() {
        GetAllStatementResponse response = new GetAllStatementResponse();

        response.setStatement(paymentRepository.getAllStatements());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PaymentRequest")
    @ResponsePayload
    public PaymentResponse makePayment(@RequestPayload PaymentRequest request) {
        PaymentResponse response = new PaymentResponse();
        boolean paymentSuccess =  processPayment(request);

        response.setSuccess(paymentSuccess);
        return response;
    }

    private boolean processPayment(PaymentRequest request) {
        try {

            int roomNumber = request.getRoomNumber();
            int amount = request.getAmount();
            String date = request.getDate();
            String uid = request.getUserId();

            boolean a = RoomClients.addLog("Payment request for room: "+roomNumber+". user: "+uid+" paid: "+amount+". date: "+ date);
            String statement =date+": "+ amount+" paid by user "+ uid+ " for room "+ roomNumber;
            paymentRepository.addPaymentStatement(statement);
                System.out.print(statement);
                return a;




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