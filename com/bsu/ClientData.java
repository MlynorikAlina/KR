package com.bsu;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Objects;

public class ClientData {
    private final Date dateOfContractConclusion;
    private final String typeOfInsurance;
    private final int sum;
    private final String currency;

    public ClientData(Date dateOfContractConclusion, String typeOfInsurance, int sum, String currency) {
        this.dateOfContractConclusion = dateOfContractConclusion;
        this.typeOfInsurance = typeOfInsurance;
        this.sum = sum;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientData that = (ClientData) o;
        return sum == that.sum &&
                Objects.equals(dateOfContractConclusion, that.dateOfContractConclusion) &&
                Objects.equals(typeOfInsurance, that.typeOfInsurance) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfContractConclusion, typeOfInsurance, sum, currency);
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "dateOfContractConclusion=" + Client.format.format(dateOfContractConclusion) +
                ", typeOfInsurance='" + typeOfInsurance + '\'' +
                ", sum=" + sum +
                ", currency='" + currency + '\'' +
                '}';
    }

    public Date getDateOfContractConclusion() {
        return dateOfContractConclusion;
    }

    public String getCurrency() {
        return currency;
    }

    public String getTypeOfInsurance() {
        return typeOfInsurance;
    }

    public int getSum() {
        return sum;
    }
}

