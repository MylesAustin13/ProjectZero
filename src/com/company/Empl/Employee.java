package com.company.Empl;

import com.company.Banks.BankAccount;
import com.company.Cust.Customer;

import java.util.List;

public class Employee {
    private int emplID;             //Unique identifier for employee
    private String user_name;
    private String password;
    private String first_name;
    private String last_name;
    private String email;

    public boolean approveAccount(BankAccount other){ //Approve the pending account (TRUE if successfully approved, FALSE if failed)
        return false;
    }

    public boolean rejectAccount(BankAccount other){ //Reject the pending account (TRUE if successfully approved, FALSE if failed)
        return false;
    }

    public List<BankAccount> getCustomerBankAccounts(Customer other){
        return other.getBankAccounts();
    }

    public int getEmplID(){
        return emplID;
    }
    public String viewLogs(){
        return "lol we didnt learn about this but this placeholder will remind me later i hope";
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
