package com.jpmorgan.trade.model;

import com.jpmorgan.trade.constants.Currency;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by dn86vid on 22/08/2019.
 */
public class Instruction implements Serializable {



    private String entity;
    private String type;
    private double agreedFx;
    private Currency currency;
    private LocalDate instructionDate;
    private LocalDate settlementDate;
    private long units;
    private double pricePerUnit;


    public String getEntity() {
        return entity;
    }


    public void setEntity(String entity) {
        this.entity = entity;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public double getAgreedFx() {
        return agreedFx;
    }


    public void setAgreedFx(double agreedFx) {
        this.agreedFx = agreedFx;
    }


    public Currency getCurrency() {
        return currency;
    }


    public void setCurrency(Currency currency) {
        this.currency = currency;
    }


    public LocalDate getInstructionDate() {
        return instructionDate;
    }


    public void setInstructionDate(LocalDate instructionDate) {
        this.instructionDate = instructionDate;
    }


    public LocalDate getSettlementDate() {
        return settlementDate;
    }


    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }


    public long getUnits() {
        return units;
    }


    public void setUnits(long units) {
        this.units = units;
    }


    public double getPricePerUnit() {
        return pricePerUnit;
    }


    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public double calculateUSDAmount(){
        return pricePerUnit*units*agreedFx;
    }


    @Override
    public String toString() {
        return "Instruction [entity=" + entity + ", type=" + type + ", agreedFx=" + agreedFx + ", currency=" + currency
                + ", instructionDate=" + instructionDate + ", settlementDate=" + settlementDate + ", units=" + units
                + ", pricePerUnit=" + pricePerUnit + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instruction)) return false;
        Instruction that = (Instruction) o;
        return Double.compare(that.getAgreedFx(), getAgreedFx()) == 0 &&
                getUnits() == that.getUnits() &&
                Double.compare(that.getPricePerUnit(), getPricePerUnit()) == 0 &&
                getEntity().equals(that.getEntity()) &&
                getType().equals(that.getType()) &&
                getCurrency() == that.getCurrency() &&
                getInstructionDate().equals(that.getInstructionDate()) &&
                getSettlementDate().equals(that.getSettlementDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntity(), getType(), getAgreedFx(), getCurrency(), getInstructionDate(), getSettlementDate(), getUnits(), getPricePerUnit());
    }
}
