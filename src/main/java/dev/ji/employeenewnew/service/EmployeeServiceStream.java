package dev.ji.employeenewnew.service;

import dev.ji.employeenewnew.model.Employee;

import java.util.Collection;

public interface EmployeeServiceStream {
    String addEmployee(String firstName, String lastName, int salary, int departmentID);

    String removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> getAllEmployees();
}
