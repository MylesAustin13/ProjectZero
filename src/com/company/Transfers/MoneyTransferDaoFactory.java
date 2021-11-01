package com.company.Transfers;



public class MoneyTransferDaoFactory {
    private static MoneyTransferDao dao;

    private MoneyTransferDaoFactory(){

    }

    public static MoneyTransferDao getMoneyTransferDao(){
        if(dao == null){
            dao = new MoneyTransferDaoImpl();
        }
        return dao;
    }
}
