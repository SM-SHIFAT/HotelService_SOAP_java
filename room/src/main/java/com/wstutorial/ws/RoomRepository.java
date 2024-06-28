package com.wstutorial.ws;

import com.wstutorial.ws.generated.room.RoomType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Component
public class RoomRepository {
    private static final HashMap<String, RoomType> rooms = new HashMap<String, RoomType>();

    @PostConstruct
    public void init() {
        for(int i=101;i<=110;i++){
            RoomType room = new RoomType();

            room.setRoomNumber(i);
            if(i<=105){
                room.setRoomCost(1000);
                room.setRoomType("Basic");
            } else if (i==110) {
                room.setRoomCost(7000);
                room.setRoomType("Suite");

            } else{
                room.setRoomCost(4000);
                room.setRoomType("Premium");
            }
            room.setIsAvailable(true);

            rooms.put(String.valueOf(room.getRoomNumber()), room);
        }
    }

    public List<RoomType> getAllRoom(){
        // Get the collection of RoomType values from the map
        Collection<RoomType> roomCollection = rooms.values();

        // Convert the collection to a list
        List<RoomType> roomList = new ArrayList<RoomType>(roomCollection);

        return roomList;
    }



    public void bookRoom(int roomnumber){
        RoomType oldRoom  = rooms.get(String.valueOf(roomnumber)) ;

        oldRoom.setIsAvailable(false);

        rooms.remove(String.valueOf(roomnumber));
        rooms.put(String.valueOf(roomnumber),oldRoom);
    }

    public void checkoutRoom(int roomnumber){
        RoomType oldRoom  = rooms.get(String.valueOf(roomnumber)) ;

        oldRoom.setIsAvailable(true);

        rooms.remove(String.valueOf(roomnumber));
        rooms.put(String.valueOf(roomnumber),oldRoom);
    }

//    public List<String> listUsernames() {
//        List<String> usernames=new ArrayList<String>();
//        for (User user : users.values()) {
//            usernames.add(user.getUsername());
//        }
//        return usernames;
//    }
}
