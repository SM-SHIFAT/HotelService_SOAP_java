package com.wstutorial.ws;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class UserRepository {
 //   private static final HashMap<String, User> users = new HashMap<String, User>();

//    @PostConstruct
//    public void init() {
//        for(int i=1;i<=10;i++){
//            PublicProfile profile = new PublicProfile();
//            profile.setUsername("username"+i);
//            for(int j = i%2==0?2:1; j<=10; j+=2){
//                if(j!=i){
//                    profile.getFollowers().add("username"+j);
//                    profile.getFollowings().add("username"+j);
//                }
//            }
//            User user = new User();
//            user.setProfile(profile);
//            user.setPassword("password"+i);
//            users.put(profile.getUsername(), user);
//        }
//    }

//    public User getUser(String username){
//        return users.get(username);
//    }
//
//    public void setUser(User user){
//        users.put(user.getUsername(),user);
//
//    }

//    public List<String> listUsernames() {
//        List<String> usernames=new ArrayList<String>();
//        for (User user : users.values()) {
//            usernames.add(user.getUsername());
//        }
//        return usernames;
//    }
}
