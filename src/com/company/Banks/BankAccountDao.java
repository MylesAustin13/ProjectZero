package com.company.Banks;

import com.company.Cust.Customer;

import java.util.List;

public interface BankAccountDao {

    void addBankAccount( BankAccount account);
    void updateBankAccount( BankAccount account);
    void deleteBankAccount (int id);

    BankAccount getBankAccountByID(int id);
    List<BankAccount> getAllBankAccountsOwned(Customer customer); //All the accounts owned by a customer



}
