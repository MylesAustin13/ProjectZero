package com.company.Banks;

public class BankAccount {
    private double balance;              //How much money?
    private boolean pendingApproval;     //Currently waiting for Employee approval
    private boolean approved;            //Got approved by an employee
    private boolean rejected;            //Got rejected by an employee

    private int ownerID;                //Who owns this account?

    public BankAccount(double balance){
        this.balance = balance;
    }

    public boolean deposit(double amount){ //Put money into the bank account
        return true;
    }

    public boolean withdraw(double amount){ //Take money from the bank account
        return true;
    }

    public double getBalance(){
        return balance;
    }

    public boolean getApproved(){
        return true;
    }

    public boolean getRejected(){
        return true;
    }

    public boolean setPending(){
        return true;
    }

    public boolean postTransferTo(BankAccount other){
        return true;
    }

    public boolean acceptTransferFrom(BankAccount other){
        return true;
    }


}
