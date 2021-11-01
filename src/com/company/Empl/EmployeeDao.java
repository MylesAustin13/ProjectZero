package com.company.Empl;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao{
    void addEmployee( Employee employee) throws SQLException;
    void updateEmployee (Employee employee) throws SQLException;
    void deleteEmployee (int id) throws SQLException; //Here for the sake of being thorough

    Employee getEmployeeByUserName(String username) throws SQLException; //Get the Employee with the matching username
    Employee getEmployeeByID(int id) throws SQLException; //Get the Employee with the matching id

    List<Employee> getAllEmployees() throws SQLException;
}
