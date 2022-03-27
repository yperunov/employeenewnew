package dev.ji.employeenewnew.service;

import dev.ji.employeenewnew.exception.EmployeeHasAlreadyAddedException;
import dev.ji.employeenewnew.exception.EmployeeNotFoundException;
import dev.ji.employeenewnew.exception.InvalidNameException;
import dev.ji.employeenewnew.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceMapsImpl implements EmployeeServiceMaps {

    private Map<String,Employee> employees = new HashMap<>();//мапа сотрудников на основе уникальных идентификаторов

    @Override
    public String addEmployee(String firstName, String lastName, int salary, int departmentId) {
        validateNames(firstName, lastName);

        String keyForMap = getKey(firstName, lastName);

        Employee addingEmployee = new Employee(firstName, lastName, salary, departmentId);

        if (employees.containsKey(keyForMap)) {
            throw new EmployeeHasAlreadyAddedException("This employee has been already added");
        }

        employees.put(keyForMap,addingEmployee);
        return "Сотрудник " + addingEmployee.getFirstName() + " " + addingEmployee.getLastName() + " успешно создан";
    }

    private String getKey(String firstName, String lastName) {
        String correctedFirstName = capitalize(firstName.toLowerCase());
        String correctedLastName = capitalize(lastName.toLowerCase());
        return correctedFirstName + "_" + correctedLastName;
    }

    private void validateNames(String... names) {
        Arrays.stream(names).forEach(name -> {
            if (!isAlpha(name)) {
                throw new InvalidNameException("Name & Surname expected from Alpha case! Pls check");
            }
        });
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
