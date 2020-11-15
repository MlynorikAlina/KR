package com.bsu;

import com.sun.media.sound.InvalidFormatException;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Request {
    static void registerUser(Map<String, User> userMap, String[] userInfo, FileWriter of) throws IOException, CustomException {
        if (userInfo.length != 4) throw new InvalidFormatException("Invalid number of arguments");
        else {
            if (userMap.containsKey(userInfo[1]))
                throw new CustomException("User with such login is already exists");
            else {
                User newUser = new User(userInfo, "user");
                userMap.put(userInfo[1], newUser);
                of.write(newUser.toString());
            }
        }
    }

    static User login(Map<String, User> userMap, String[] userInfo) throws InvalidFormatException, CustomException {
        if (userInfo.length != 2) throw new InvalidFormatException("Invalid number of arguments");
        else {
            if (userMap.containsKey(userInfo[0]))
                if (userMap.get(userInfo[0]).getPassword().equals(userInfo[1])) {
                    System.out.println("Entrance succeed!\n");
                    return userMap.get(userInfo[0]);
                } else {
                    throw new CustomException("Invalid password!");
                }
            else throw new CustomException("No users with such login");
        }
    }

    static void showAll(Map<Integer, Client> clientMap) {
        clientMap.forEach((k, v) -> System.out.println(k + " " + v.toString()));
    }

    static Client getClient(Map<Integer, Client> clientMap, User currentUser, Integer key) throws CustomException {
        if (currentUser.getRole().toLowerCase().equals("user")) throw new CustomException("Access exception");
        else {
            return clientMap.get(key);
        }
    }

    static void statistics(Map<Integer, Client> clientMap) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy");
        Date today = new Date(System.currentTimeMillis());
        Date lastYear = Client.format.parse(format1.format(today) + (Integer.parseInt(format2.format(today)) - 1));
        clientMap.forEach((k, v) -> System.out.println(k + " " + v.getStatisticsFrom(lastYear, today)));
    }

    static void getTop(Map<Integer, Client> clientMap, int n, Currency cur) {
        List<Pair<Double, Integer>> top = new ArrayList<>();
        clientMap.forEach((k, v) -> top.add(new Pair<>(v.sumBillsInCur(cur), k)));
        top.sort((x, y) -> y.getKey().compareTo(x.getKey()));
        for (int i = 0; i < Math.min(n, top.size()); i++) {
            System.out.println("ID: " + top.get(i).getValue() + " amount =" + top.get(i).getKey());
        }
    }
}

class Pair<L, R> {
    private L first;
    private R second;

    public Pair(L first, R second) {
        this.first = first;
        this.second = second;
    }

    public L getKey() {
        return first;
    }

    public R getValue() {
        return second;
    }
}