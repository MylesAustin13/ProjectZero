package com.company.Banks;

import com.company.Transfers.MoneyTransfer;

import java.util.List;

public class BankAccount {
    private int BID;
    private double balance;              //How much money?
    private boolean pendingApproval;     //Currently waiting for Employee approval
    private boolean approved;            //Got approved by an employee
    private boolean rejected;            //Got rejected by an employee
    private int ownerID;                //Who owns this account?

    private List<MoneyTransfer> pendingTransfers;
    private List<MoneyTransfer> completedTransfers;

    public BankAccount(double balance){
        this.balance = balance;
    }

    public int getBID() {
        return BID;
    }

    public int getOwnerID(){
        return ownerID;
    }
    public void deposit(double amount){ //Put money into the bank account
        balance += amount;
    }

    public boolean withdraw(double amount){ //Take money from the bank account
        if(amount <= 0){ // Reject outright if amount is invalid
            System.out.println("The entered amount is invalid.");
            return false;
        }
        else if (balance < amount){ //Reject if not enough money to withdraw
            System.out.println("Insufficient funds.");
            return false;
        }
        else{
            balance -= amount;
            System.out.println("Withdrawal successful!");
            return true;
        }

    }

    public double getBalance(){
        return balance;
    }

    public void getApproved(){
        approved = true;
        pendingApproval = false;
    }

    public void getRejected(){
        rejected = true;
        pendingApproval = false;
    }

    public void setPending(){
        pendingApproval = true;
        approved = false;
        rejected = false;
    }

    /*public boolean postTransferTo(BankAccount other){
        return true;

    }

    public boolean acceptTransferFrom(BankAccount other){
        return true;
    }*/


}
