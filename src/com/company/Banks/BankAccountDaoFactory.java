package com.company.Banks;

import com.company.Cust.CustomerDao;
import com.company.Cust.CustomerDaoImpl;

public class BankAccountDaoFactory {
    private static BankAccountDao dao;

    private BankAccountDaoFactory(){

    }

    public static BankAccountDao getBankAccountDao(){
        if(dao == null){
            dao = new BankAccountDaoImpl();
        }
        return dao;
    }
}
