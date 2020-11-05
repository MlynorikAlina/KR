package com.bsu;

import com.sun.media.sound.InvalidFormatException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length >= 3) {
            try (Scanner scanner = new Scanner(System.in);
                 Scanner users = new Scanner(new FileReader(args[0]));
                 Scanner clients = new Scanner(new FileReader(args[1]));
                 FileWriter outputFile = new FileWriter(args[0],true)) {


                String[] lineArgs;
                String request;
                List<User> usersList = new ArrayList<>();
                HashMap<Integer, Client> clientsList = new HashMap<>();

                while (users.hasNextLine()) {
                    lineArgs = users.nextLine().split("[ ]+");
                    usersList.add(new User(lineArgs));
                }
                users.close();

                while (clients.hasNextLine()) {
                    lineArgs = clients.nextLine().split("[ ]+");
                    Integer id = Integer.parseInt(lineArgs[0]);
                    if(clientsList.containsKey(id))clientsList.get(id).addClientData(lineArgs);
                    else clientsList.put(id, new Client(lineArgs));
                }
                requests(scanner,usersList,clientsList,outputFile);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static void requests(Scanner scanner, List<User> userList, HashMap<Integer,Client> clientHashMap, FileWriter of) throws IOException, CustomException, ParseException {
        int key;
        String requestData;
        User currentUser = null;
        while (true) {
            printMenu();
            System.out.println("Enter key: ");
            key = scanner.nextInt();
            scanner.nextLine();
            switch (key){
                case 1:
                    System.out.println("Enter username, login, email, pw:");
                    requestData = scanner.nextLine();
                    String[] userInfo = requestData.split(" ");
                    Request.registerUser(userList,userInfo, of);
                    break;
                case 2:
                    System.out.println("Enter login, pw:");
                    requestData = scanner.nextLine();
                    String[] loginInfo = requestData.split(" ");
                    currentUser = Request.login(userList,loginInfo);
                    break;
                case 3:
                    Request.showAll(clientHashMap);
                    break;
                case 4:
                    System.out.println("Enter client id:");
                    Integer id= scanner.nextInt();
                    if(currentUser == null)throw new CustomException("Unauthorised user");
                    System.out.println(Request.getClient(clientHashMap,currentUser,id));
                    break;
                case 5:
                    Request.statistics(clientHashMap);
                    break;
                case 6:
                    System.out.println("Enter n(top-n), currency:");
                    requestData = scanner.nextLine();
                    String[] data = requestData.split(" ");
                    if (data.length != 2) throw new InvalidFormatException("Invalid number of arguments");
                    Request.getTop(clientHashMap,Integer.parseInt(data[0]),data[1]);
                    break;
                default:
                    return;
            }
        }
    }
    static void printMenu() {
        System.out.println(
                "1 - регистрация пользователя (добавление в файл с пользователями новой записи. перед добавлением проверить, что пользователя с таким логином еще не было. Роль проставляется как USER)\n" +
                        "2 - вход в систему (ввод логина и пароля)\n" +
                        "3 - просмотр всех записей\n" +
                        "4 - поиск клиента по номеру (только для админа)\n" +
                        "5 - вывод статистики за год по каждому клиенту: какие виды страховки заключал и общую сумму (перевод по курсу валют)\n" +
                        "6 - топ-N клиентов по максимальной сумме страховки в определенной валюте (только по совпадающей валюте, без перевода остальных) (N и валюта вводится пользователем)\n" +
                        "7 - выход\n" +

                        "-------------------"
        );
    }
}