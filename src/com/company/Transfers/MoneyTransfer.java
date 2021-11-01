package com.company.Transfers;


import java.sql.Timestamp;
import java.util.Date;

public class MoneyTransfer {
    private int transferID;
    private double amount;         //How much money is going from point A to point B
    private int sender;    //Which account id sent this?
    private int recipient; //Which account id is getting this?
    private boolean pending;
    private Timestamp completedOn;

    public MoneyTransfer(){
    }



    public MoneyTransfer(int transferID, double amount, int sender, int recipient, boolean pending, Timestamp completedOn) {
        this.transferID = transferID;
        this.amount = amount;
        this.sender = sender;
        this.recipient = recipient;
        this.pending = pending;
        this.completedOn = completedOn;
    }

    public int getTransferID() {
        return transferID;
    }

    public void setTransferID(int transferID) {
        this.transferID = transferID;
    }

    public Timestamp getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(Timestamp completedOn) {
        this.completedOn = completedOn;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending() {
        pending = true;
    }

    public void setComplete(){
        pending = false;
        completedOn = new Timestamp(System.currentTimeMillis());
    }

    public void process(){
        pending = false;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "MoneyTransfer No. " + transferID +
                ": Sum: " + amount;
    }
}
