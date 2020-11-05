package com.bsu;

import com.sun.media.sound.InvalidFormatException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Client {
    private final String name;
    private List<ClientData> clientDataList;


    public static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public Client(String[] args) throws CustomException {
        try {
            if (args.length != 6) throw new InvalidFormatException("Invalid number of arguments");
            else {
                name = args[1];
                clientDataList = new ArrayList<>();
                clientDataList.add(new ClientData(format.parse(args[2]),args[3],Integer.parseInt(args[4]),args[5]));
            }
        } catch (Exception ex) {
            throw new CustomException("Reading input file is failed :: " + ex);
        }
    }
    public void addClientData(String[] args) throws CustomException {
        try {
            if (args.length != 5) throw new InvalidFormatException("Invalid number of arguments");
            else {
                clientDataList.add(new ClientData(format.parse(args[1]),args[2],Integer.parseInt(args[3]),args[4]));
            }
        } catch (Exception ex) {
            throw new CustomException("Reading input file is failed :: " + ex);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) &&
                Objects.equals(clientDataList, client.clientDataList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, clientDataList);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", clientDataList=" + clientDataList +
                '}';
    }
    public String getStatisticsFrom(Date startDate) {
        String res = name + ":\n";
        int sum=0;
        List<ClientData> founded = clientDataList.stream().filter(data -> data.getDateOfContractConclusion().compareTo(startDate) >= 0).collect(Collectors.toList());
        for(ClientData d : founded){
            sum +=d.getSum();
            res = res + d.toString() + "\n";
        }
        return res;
    }
}
