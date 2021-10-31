package com.company.Transfers;

import java.util.List;

public class MoneyTransferDaoImpl implements MoneyTransferDao{

    @Override
    public void addMoneyTransfer(MoneyTransfer transfer) {

    }

    @Override
    public void updateMoneyTransfer(MoneyTransfer transfer) {

    }

    @Override
    public void deleteMoneyTransfer(int id) {

    }

    @Override
    public List<MoneyTransfer> getAllTransfersFrom(int senderID) {
        return null;
    }

    @Override
    public List<MoneyTransfer> getAllPendingTransfersFrom(int senderID) {
        return null;
    }

    @Override
    public List<MoneyTransfer> getAllCompletedTransfersFrom(int senderID) {
        return null;
    }

    @Override
    public List<MoneyTransfer> getAllTransfersTo(int receiverID) {
        return null;
    }

    @Override
    public List<MoneyTransfer> getAllPendingTransfersTo(int receiverID) {
        return null;
    }

    @Override
    public List<MoneyTransfer> getAllCompletedTransfersTo(int receiverID) {
        return null;
    }
}
