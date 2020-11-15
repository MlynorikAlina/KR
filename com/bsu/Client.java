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
            if (args.length != 6) throw new InvalidFormatException("Invalid number of arguments");
            else {
                clientDataList.add(new ClientData(format.parse(args[2]),args[3],Integer.parseInt(args[4]),args[5]));
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
        StringBuilder res = new StringBuilder(name + ": ");
        double sum=0;
        List<ClientData> founded = clientDataList.stream().filter(data -> data.getDateOfContractConclusion().compareTo(startDate) >= 0).collect(Collectors.toList());
        for(ClientData d : founded){
            sum +=Currency.toUSD(d.getCurrency(),d.getSum());
            res.append(d.getTypeOfInsurance()).append(", ");
        }
        res.append("\nSummary(in USD) = ").append(sum);
        return res.toString();
    }
    public double sumBillsInCur(String cur){
        double sum=0;
        for(ClientData d : clientDataList){
            if(cur.equalsIgnoreCase(d.getCurrency())) sum +=d.getSum();
        }
        return  sum;
    }
}
