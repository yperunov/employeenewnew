package dev.ji.employeenewnew.service;

import dev.ji.employeenewnew.exception.EmployeeHasAlreadyAddedException;
import dev.ji.employeenewnew.exception.EmployeeNotFoundException;
import dev.ji.employeenewnew.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceStreamImpl implements EmployeeServiceStream {

    private Map<String,Employee> employees = new HashMap<>();//мапа сотрудников на основе уникальных идентификаторов

    @Override
    public String addEmployee(String firstName, String lastName, int salary, int departmentId) {

        Employee addingEmployee = new Employee(firstName, lastName, salary, departmentId);

        String keyForMap = firstName + lastName;

        if (employees.containsKey(keyForMap)) {
            throw new EmployeeHasAlreadyAddedException("This employee has been already added");
        }

        employees.put(keyForMap,addingEmployee);
        return "Сотрудник " + addingEmployee.getFirstName() + " " + addingEmployee.getLastName() + " успешно создан";
    }

    @Override
    public String removeEmployee(String firstName, String lastName) {
        Employee removingEmployee = new Employee(firstName, lastName);

        String keyForMap = firstName + lastName;

        if (!employees.containsKey(keyForMap)) {
            throw new EmployeeNotFoundException ("We can't find this employee");
        }
        employees.remove(keyForMap);

        return "Сотрудник " + removingEmployee.getFirstName() + " " + removingEmployee.getLastName() + " успешно удален";
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {

        String keyForMap = firstName + lastName;

        if (!employees.containsKey(keyForMap)) {
            throw new EmployeeNotFoundException ("We can't find this employee");
        }

        return employees.get(keyForMap);
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }

}
