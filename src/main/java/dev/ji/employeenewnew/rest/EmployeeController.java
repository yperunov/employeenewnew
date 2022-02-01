package dev.ji.employeenewnew.rest;

import dev.ji.employeenewnew.model.Employee;
import dev.ji.employeenewnew.service.EmployeeServiceCollections;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeServiceCollections employeeService;

    public EmployeeController(EmployeeServiceCollections employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String greetEmployee() {
        return "Hello, employee";
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName) {
        String addedEmployee = employeeService.addEmployee(firstName, lastName);
        return addedEmployee;
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        String removedEmployee = employeeService.removeEmployee(firstName, lastName);
        return removedEmployee;
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName) {
        Employee foundEmployee = employeeService.findEmployee(firstName, lastName);
        return foundEmployee;
    }

    @GetMapping("/all")
    public Set<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


}
