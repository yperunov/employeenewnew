package dev.ji.employeenewnew.service;

import dev.ji.employeenewnew.exception.EmployeeNotFoundException;
import dev.ji.employeenewnew.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static dev.ji.employeenewnew.service.EmployeeTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeDeptServiceStreamImplTest {

    @Mock
    private EmployeeServiceStream employeeServiceStreamMock;

    @InjectMocks
    private EmployeeDeptServiceStreamImpl out;

    @BeforeEach
    void settingUpTestClassMockito() {
        Mockito.when(employeeServiceStreamMock.getAllEmployees()).
                thenReturn(List.of(
                        new Employee(FIRST_NAME_1,LAST_NAME_1,SALARY_1,DEPARTMENT_ID_1),
                        new Employee(FIRST_NAME_2,LAST_NAME_2,SALARY_2,DEPARTMENT_ID_1)
                ));
    }

    @Test
    public void shouldReturnEmployeeWithMinimalSalaryWhenRequestedEmployeeWithMinimalSalary() {
        Employee expectedEmployee = new Employee(FIRST_NAME_2, LAST_NAME_2, SALARY_2, DEPARTMENT_ID_1);
        Employee actualEmployee = out.getEmployeeWithMinimalSalaryByDept(DEPARTMENT_ID_1);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void shouldReturnEmployeeWithMaximalSalaryWhenRequestedEmployeeWithMaximalSalary() {
        Employee expectedEmployee = new Employee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1);
        Employee actualEmployee = out.getEmployeeWithMaximumSalaryByDept(DEPARTMENT_ID_1);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void shouldThrowCorrectExceptionWhenRequestingMinimalSalaryAndEnteredNonExistingDepartmentId() {
        assertThrowsExactly(EmployeeNotFoundException.class,
                () -> out.getEmployeeWithMinimalSalaryByDept(DEPARTMENT_ID_2));
    }
    @Test
    public void shouldThrowCorrectExceptionWhenRequestingMaximalSalaryAndEnteredNonExistingDepartmentId() {
        assertThrowsExactly(EmployeeNotFoundException.class,
                () -> out.getEmployeeWithMaximumSalaryByDept(DEPARTMENT_ID_2));
    }

    @Test
    public void shouldReturnCorrectCollectionWhenRequestingEmployeesCollectionOnlyThisDeptByDept() {
        Collection<Employee> expectedEmployeeCollection = List.of(
                new Employee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1),
                new Employee(FIRST_NAME_2, LAST_NAME_2,SALARY_2, DEPARTMENT_ID_1));
        Collection<Employee> actualEmployeeCollection = out.getEmployeesOnlyThisDeptByDept(DEPARTMENT_ID_1);
        assertEquals(expectedEmployeeCollection.size(), actualEmployeeCollection.size());
        assertTrue(actualEmployeeCollection.containsAll(expectedEmployeeCollection));
    }

    @Test
    public void shouldReturnCorrectMapWhenRequestingAllEmployeesByDept() {
        Map<Integer,List<Employee>> expectedEmployeeMap = Map.of(
                DEPARTMENT_ID_1,List.of(
                new Employee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1),
                new Employee(FIRST_NAME_2, LAST_NAME_2,SALARY_2, DEPARTMENT_ID_1)));
        Map<Integer,List<Employee>> actualEmployeeMap = out.getAllEmployeesByDept();
        assertEquals(expectedEmployeeMap, actualEmployeeMap);
    }

    @Test
    public void shouldReturnEmptyCollectionWhenRequestingNonExistingDepartmentOnlyThisDeptByDept() {
        Collection<Employee> expectedEmployeeCollection = Collections.emptyList();
        Collection<Employee> actualEmployeeCollection = out.getEmployeesOnlyThisDeptByDept(DEPARTMENT_ID_2);
        assertEquals(expectedEmployeeCollection.size(), actualEmployeeCollection.size());
        assertTrue(actualEmployeeCollection.containsAll(expectedEmployeeCollection));
    }

    @Test
    public void shouldReturnEmptyMapWhenRequestingEmptyAllEmployeesByDept() {
        Mockito.when(employeeServiceStreamMock.getAllEmployees()).
                thenReturn(Collections.emptyList());
        Map<Integer,List<Employee>> expectedEmployeeMap = Collections.emptyMap();
        Map<Integer,List<Employee>> actualEmployeeMap = out.getAllEmployeesByDept();
        assertEquals(expectedEmployeeMap, actualEmployeeMap);
    }








}
