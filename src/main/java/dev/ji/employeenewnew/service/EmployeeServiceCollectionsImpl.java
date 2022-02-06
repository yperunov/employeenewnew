//package dev.ji.employeenewnew.service;
//
//import dev.ji.employeenewnew.exception.EmployeeHasAlreadyAddedException;
//import dev.ji.employeenewnew.exception.EmployeeNotFoundException;
//import dev.ji.employeenewnew.model.Employee;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class EmployeeServiceCollectionsImpl implements EmployeeServiceCollections {
//
//    private Set<Employee> employees = new HashSet<>();//коллекция сотрудников
//
//    @Override
//    public String addEmployee(String firstName, String lastName) {
//
//        Employee addingEmployee = new Employee(firstName, lastName);
//
//        if (employees.contains(addingEmployee)) {
//            throw new EmployeeHasAlreadyAddedException("This employee has been already added");
//        }
//
//        employees.add(addingEmployee);
//        return "Сотрудник " + addingEmployee.getFirstName() + " " + addingEmployee.getLastName() + " успешно создан";
//    }
//
//    @Override
//    public String removeEmployee(String firstName, String lastName) {
//        Employee removingEmployee = new Employee(firstName, lastName);
//
//        if (!employees.remove(removingEmployee)) {
//            throw new EmployeeNotFoundException ("We can't find this employee");
//        }
//
//        return "Сотрудник " + removingEmployee.getFirstName() + " " + removingEmployee.getLastName() + " успешно удален";
//    }
//
//    @Override
//    public Employee findEmployee(String firstName, String lastName) {
//        Employee findingEmployee = new Employee(firstName, lastName);
//
//        if (!employees.contains(findingEmployee)) {
//            throw new EmployeeNotFoundException ("We can't find this employee");
//        }
//
//        return findingEmployee;
//    }
//
//    @Override
//    public Set<Employee> getAllEmployees() {
//        return employees;
//    }
//
//}
