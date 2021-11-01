package com.company.Empl;

import com.company.Cust.CustomerDao;
import com.company.Cust.CustomerDaoImpl;

public class EmployeeDaoFactory {
    private static EmployeeDao dao;

    private EmployeeDaoFactory(){

    }

    public static EmployeeDao getEmployeeDao(){
        if(dao == null){
            dao = new EmployeeDaoImpl();
        }
        return dao;
    }
}
