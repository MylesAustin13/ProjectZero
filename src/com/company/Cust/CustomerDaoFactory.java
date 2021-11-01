package com.company.Cust;

import com.company.Empl.EmployeeDao;

public class CustomerDaoFactory {
    private static CustomerDao dao;

    private CustomerDaoFactory(){

    }

    public static CustomerDao getCustomerDao(){
        if(dao == null){
            dao = new CustomerDaoImpl();
        }
        return dao;
    }
}
