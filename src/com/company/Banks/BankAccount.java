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

    //private List<MoneyTransfer> pendingTransfers;
    //private List<MoneyTransfer> completedTransfers;

    public BankAccount(){}

    public BankAccount(double balance){
        this.balance = balance;
    }

    public BankAccount(int BID, double balance, boolean pendingApproval, boolean approved, boolean rejected, int ownerID) {
        this.BID = BID;
        this.balance = balance;
        this.pendingApproval = pendingApproval;
        this.approved = approved;
        this.rejected = rejected;
        this.ownerID = ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getBID() {
        return BID;
    }
    public void setBID(int id){
        BID = id;
    }

    public int getOwnerID(){
        return ownerID;
    }
    public boolean deposit(double amount){ //Put money into the bank account
        if(amount < 0){
            System.out.println("The entered amount is invalid.");
            return false;
        }
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount){ //Take money from the bank account
        if(amount < 0){ // Reject outright if amount is invalid
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
        rejected = false;
        pendingApproval = false;
    }

    public void getRejected(){
        approved = false;
        rejected = true;
        pendingApproval = false;
    }

    public void setPending(){
        pendingApproval = true;
        approved = false;
        rejected = false;
    }

    public boolean getPendingStatus(){
        return pendingApproval;
    }
    public boolean getApprovalStatus(){
        return approved;
    }
    public boolean getRejectionStatus(){
        return rejected;
    }


    @Override
    public String toString() {
        return "BankAccount No. " + BID + ": " + "Balance: $" + balance;
    }
    /*
    public MoneyTransfer createTransferTo(double amount, BankAccount other){
        MoneyTransfer transfer = null;

        if(amount <= 0){ // Reject outright if amount is invalid
            System.out.println("The entered amount is invalid.");
            return null;
        }
        else if (balance < amount){ //Reject if not enough money to withdraw
            System.out.println("Insufficient funds.");
            return null;
        }
        else{
            balance -= amount;
            System.out.println("Withdrawal successful!");
            transfer= new MoneyTransfer(); //Create a transfer and set the value
            transfer.setAmount(amount);
            //pendingTransfers.add(transfer);
            return transfer;
        }



    }

     */


}
