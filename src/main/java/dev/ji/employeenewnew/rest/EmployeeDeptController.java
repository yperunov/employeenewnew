package dev.ji.employeenewnew.rest;

import dev.ji.employeenewnew.model.Employee;
import dev.ji.employeenewnew.service.EmployeeDeptServiceStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class EmployeeDeptController {

    private final EmployeeDeptServiceStream employeeDeptServiceStream;

    public EmployeeDeptController(EmployeeDeptServiceStream employeeDeptServiceStream) {
        this.employeeDeptServiceStream = employeeDeptServiceStream;
    }

    @GetMapping("/")
    public String greetDepartments() {
        return "Hello, departments";
    }

    @GetMapping(path = "/max-salary")
    public Employee getEmployeeWithMaximumSalaryByDept(@RequestParam int departmentId) {
        return employeeDeptServiceStream.getEmployeeWithMaximumSalaryByDept(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee getEmployeeWithMaSalaryByDept(@RequestParam int departmentId) {
        return employeeDeptServiceStream.getEmployeeWithMinimalSalaryByDept(departmentId);
    }

    @GetMapping(value ="/all",params = {"departmentId"})
    public Collection<Employee> getEmployeesOnlyThisDeptByDept(@RequestParam(name="departmentId") int departmentId) {
        return employeeDeptServiceStream.getEmployeesOnlyThisDeptByDept(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getEmployeesOnlyThisDeptByDept() {
        return employeeDeptServiceStream.getAllEmployeesByDept();
    }
}
