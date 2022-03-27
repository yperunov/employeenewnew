package dev.ji.employeenewnew.service;

import dev.ji.employeenewnew.exception.EmployeeNotFoundException;
import dev.ji.employeenewnew.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeDeptServiceImpl implements EmployeeDeptService {

    private final EmployeeServiceMaps employeeServiceMaps;

    public EmployeeDeptServiceImpl(EmployeeServiceMaps employeeServiceMaps) {
        this.employeeServiceMaps = employeeServiceMaps;
    }


    @Override
    public Employee getEmployeeWithMinimalSalaryByDept(int departmentId) {
        return employeeServiceMaps.getAllEmployees().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee getEmployeeWithMaximumSalaryByDept(int departmentId) {
        return employeeServiceMaps.getAllEmployees().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Collection<Employee> getEmployeesOnlyThisDeptByDept(int departmentId) {
        return employeeServiceMaps.getAllEmployees().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByDept() {
        return employeeServiceMaps.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
}
