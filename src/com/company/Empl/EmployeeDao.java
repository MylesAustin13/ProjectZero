package com.company.Empl;

import java.util.List;

public interface EmployeeDao{
    void addEmployee( Employee employee);
    void updateEmployee (Employee employee);
    void deleteEmployee (int id); //Here for the sake of being thorough

    Employee getEmployeeByUserName(String username); //Get the Employee with the matching username
    Employee getEmployeeByID(int id); //Get the Employee with the matching id

    List<Employee> getAllEmployees();
}
