package com.wstutorial.ws;

import com.wstutorial.ws.generated.room.RoomType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Component
public class PaymentRepository {
    private static final List<String> rooms = new ArrayList<String>();


    public List<String> getAllStatements(){

        return rooms;
    }



    public void addPaymentStatement(String s){
        rooms.add(s);
    }



}
