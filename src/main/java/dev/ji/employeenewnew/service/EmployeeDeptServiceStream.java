package dev.ji.employeenewnew.service;

import dev.ji.employeenewnew.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeDeptServiceStream {

    Employee getEmployeeWithMinimalSalaryByDept(int departmentId);

    Employee getEmployeeWithMaximumSalaryByDept(int departmentId);

    Collection<Employee> getEmployeesOnlyThisDeptByDept(int departmentId);

    Map<Integer, List<Employee>> getAllEmployeesByDept();

}



