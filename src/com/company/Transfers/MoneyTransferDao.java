package com.company.Transfers;

import com.company.Banks.BankAccount;

import java.util.List;

public interface MoneyTransferDao {
    void addMoneyTransfer(MoneyTransfer transfer);
    void updateMoneyTransfer(MoneyTransfer transfer);
    void deleteMoneyTransfer(int id);

    List<MoneyTransfer> getAllTransfersFrom(int senderID);              //Get all the transfers where the sender is this bank account
    List<MoneyTransfer> getAllPendingTransfersFrom(int senderID);       //Get all the unfinished transfers where the sender is this bank account
    List<MoneyTransfer> getAllCompletedTransfersFrom(int senderID);     //Get all the finished transfers where the sender is this bank account (LOGGING)

    List<MoneyTransfer> getAllTransfersTo(int receiverID);              //Get all the transfers where the destination is this bank account
    List<MoneyTransfer> getAllPendingTransfersTo(int receiverID);       //Get all the unfinished transfers where the destination is this bank account
    List<MoneyTransfer> getAllCompletedTransfersTo(int receiverID);     //Get all the finished transfers where the destination is this bank account (LOGGING)
}
