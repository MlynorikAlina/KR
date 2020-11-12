package com.bsu;

import java.util.Date;
import java.util.Objects;

public class ClientData {
    private final Date dateOfContractConclusion;
    private final String typeOfInsurance;
    private final int amount;
    private final Currency currency;

    public ClientData(Date dateOfContractConclusion, String typeOfInsurance, int amount, String currency) {
        this.dateOfContractConclusion = dateOfContractConclusion;
        this.typeOfInsurance = typeOfInsurance;
        this.amount = amount;
        this.currency = Currency.getCurrency(currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientData that = (ClientData) o;
        return amount == that.amount &&
                Objects.equals(dateOfContractConclusion, that.dateOfContractConclusion) &&
                Objects.equals(typeOfInsurance, that.typeOfInsurance) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfContractConclusion, typeOfInsurance, amount, currency);
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "dateOfContractConclusion=" + Client.format.format(dateOfContractConclusion) +
                ", typeOfInsurance='" + typeOfInsurance + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    public Date getDateOfContractConclusion() {
        return dateOfContractConclusion;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getTypeOfInsurance() {
        return typeOfInsurance;
    }

    public int getAmount() {
        return amount;
    }
}

