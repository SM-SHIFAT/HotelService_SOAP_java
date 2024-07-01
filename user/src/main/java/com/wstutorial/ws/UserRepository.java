package com.wstutorial.ws;

import com.wstutorial.ws.generated.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class UserRepository {
    private static final HashMap<String, User> users = new HashMap<String, User>();


    public User getUser(String username){
        return users.get(username);
    }

    public User getUserByUid(String uid){
        for (User user : users.values()) {
            if (user.getUniqueid().equals(uid)) {
                return user;
            }
        }
        return null;
    }



    public int getListSize(){
        return users.size();
    }

    public void setUser(User user){
        users.put(user.getUsername(),user);

    }
}
