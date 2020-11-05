package com.bsu;

import com.sun.media.sound.InvalidFormatException;

import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Request {
    static void registerUser(List<User> usersList, String[] userInfo, FileWriter of) throws IOException, CustomException {
        if (userInfo.length != 4) throw new InvalidFormatException("Invalid number of arguments");
        else {
            for (User u : usersList) {
                if (u.getLogin().equalsIgnoreCase(userInfo[1]))
                    throw new CustomException("User with such login is already exists");
            }
            User newUser = new User(userInfo, "user");
            usersList.add(newUser);
            of.write(newUser.toString());
        }
    }

    static User login(List<User> usersList, String[] userInfo) throws InvalidFormatException, CustomException {
        if (userInfo.length != 2) throw new InvalidFormatException("Invalid number of arguments");
        else {
            for (User u : usersList) {
                if (u.getLogin().equalsIgnoreCase(userInfo[0]))
                    if (u.getPassword().equals(userInfo[1])) {
                        System.out.println("Entrance succeed!\n");
                        return u;
                    } else throw new CustomException("Invalid password!");
            }
            throw new CustomException("No users with such login");
        }
    }

    static void showAll(HashMap<Integer, Client> clientsList) {
        clientsList.forEach((k,v)-> System.out.println(k + " " + v.toString()));
    }

    static Client getClient(HashMap<Integer, Client> clientsList, User currentUser, Integer key) throws CustomException {
        if (currentUser.getRole().toLowerCase().equals("user")) throw new CustomException("Access exception");
        else {
            return clientsList.get(key);
        }
    }

    static void statistics(HashMap<Integer, Client> clientsList) throws ParseException {
        Date date = Client.format.parse("05.11.2019");
        System.out.println(Client.format.format(date));
        clientsList.forEach((k, v) -> System.out.println(k + " " + v.getStatisticsFrom(date)));
    }

    static void exit(User currentUser) {
        currentUser = null;
    }
}
