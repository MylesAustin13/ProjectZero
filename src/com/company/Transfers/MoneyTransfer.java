package com.company.Transfers;


import java.util.Date;

public class MoneyTransfer {
    private int transferID;
    private double amount;         //How much money is going from point A to point B
    private int sender;    //Which account id sent this?
    private int recipient; //Which account id is getting this?
    private boolean pending;
    private Date completedOn;

    public boolean isPending() {
        return pending;
    }

    public void setPending() {
        pending = true;
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
}
