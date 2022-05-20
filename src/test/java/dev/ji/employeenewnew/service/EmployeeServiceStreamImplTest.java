package dev.ji.employeenewnew.service;

import dev.ji.employeenewnew.exception.EmployeeHasAlreadyAddedException;
import dev.ji.employeenewnew.exception.EmployeeNotFoundException;
import dev.ji.employeenewnew.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static dev.ji.employeenewnew.service.EmployeeTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceStreamImplTest {

    private EmployeeServiceStreamImpl out;

    @BeforeEach
    void settingUpTestClass() {
        this.out = new EmployeeServiceStreamImpl();
    }

    @Test
    public void shouldAddEmployeeWhenAddingNewEmployee() {
        Employee expectedEmployee = new Employee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1);
        String expectedStringAfterExpectedEmployeeAdding =
                "Сотрудник " + expectedEmployee.getFirstName() + " "
                        + expectedEmployee.getLastName() + " успешно создан";
        String actualStringAfterActualEmployeeAdding =
                out.addEmployee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1);
        assertEquals(expectedStringAfterExpectedEmployeeAdding, actualStringAfterActualEmployeeAdding);
    }

    @Test
    public void shouldAddTwoEmployeesWhenAddingTwoNewEmployees() {
        Employee expectedFirstEmployee = new Employee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1);
        String expectedStringAfterExpectedFirstEmployeeAdding =
                "Сотрудник " + expectedFirstEmployee.getFirstName() + " " +
                        expectedFirstEmployee.getLastName() + " успешно создан";
        String actualStringAfterActualFirstEmployeeAdding =
                out.addEmployee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1);
        assertEquals(expectedStringAfterExpectedFirstEmployeeAdding, actualStringAfterActualFirstEmployeeAdding);
        Employee expectedSecondEmployee = new Employee(FIRST_NAME_2, LAST_NAME_2, SALARY_2, DEPARTMENT_ID_2);
        String expectedStringAfterExpectedSecondEmployeeAdding = "Сотрудник " + expectedSecondEmployee.getFirstName() +
                " " + expectedSecondEmployee.getLastName() + " успешно создан";
        String actualStringAfterActualSecondEmployeeAdding =
                out.addEmployee(FIRST_NAME_2, LAST_NAME_2, SALARY_2, DEPARTMENT_ID_2);
        assertEquals(expectedStringAfterExpectedSecondEmployeeAdding, actualStringAfterActualSecondEmployeeAdding);
    }

    @Test
    public void shouldThrowCorrectExceptionWhenAddingDuplicateEmployee() {
        out.addEmployee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1);
        assertThrowsExactly(EmployeeHasAlreadyAddedException.class,
                () -> out.addEmployee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1));
    }

    @Test
    public void shouldRemoveThisEmployeeWhenRemovingThisEmployee() {
        out.addEmployee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1);
        String expectedStringAfterExpectedEmployeeRemoving = "Сотрудник " + FIRST_NAME_1 + " " +
                LAST_NAME_1 + " успешно удален";
        String actualStringAfterActualEmployeeRemoving = out.removeEmployee(FIRST_NAME_1, LAST_NAME_1);
        assertEquals(expectedStringAfterExpectedEmployeeRemoving, actualStringAfterActualEmployeeRemoving);
        Employee removedEmployee = new Employee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1);
        assertFalse(out.getAllEmployees().contains(removedEmployee));
    }

    @Test
    public void shouldThrowCorrectExceptionWhenRemovingNonExistingEmployee() {
        assertThrowsExactly(EmployeeNotFoundException.class, () -> out.removeEmployee(FIRST_NAME_1, LAST_NAME_1));
    }

    @Test
    public void shouldFindThisEmployeeAfterAddingThisEmployee() {
        out.addEmployee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1);
        Employee expectedEmployee = new Employee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1);
        Employee foundEmployee = out.findEmployee(FIRST_NAME_1, LAST_NAME_1);
        assertEquals(expectedEmployee, foundEmployee);
        assertTrue(out.getAllEmployees().contains(foundEmployee));
    }

    @Test
    public void shouldThrowCorrectExceptionWhenTryingToFindNonExistingEmployee() {
        assertThrowsExactly(EmployeeNotFoundException.class, () -> out.findEmployee(FIRST_NAME_1, LAST_NAME_1));
    }

    @Test
    public void shouldCreateCorrectCollectionAfterAddingOneEmployee() {
        out.addEmployee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1);
        Collection<Employee> expectedEmployeeCollection = List.of(
                new Employee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1));
        Collection<Employee> actualEmployeeCollection = out.getAllEmployees();
        assertEquals(expectedEmployeeCollection.size(), actualEmployeeCollection.size());
        assertTrue(actualEmployeeCollection.containsAll(expectedEmployeeCollection));
    }

    @Test
    public void shouldCreateCorrectCollectionAfterAddingTwoEmployees() {
        out.addEmployee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1);
        out.addEmployee(FIRST_NAME_2, LAST_NAME_2, SALARY_2, DEPARTMENT_ID_2);
        Collection<Employee> expectedEmployeeCollection = List.of(
                new Employee(FIRST_NAME_1, LAST_NAME_1, SALARY_1, DEPARTMENT_ID_1),
                new Employee(FIRST_NAME_2, LAST_NAME_2, SALARY_2, DEPARTMENT_ID_2));
        Collection<Employee> actualEmployeeCollection = out.getAllEmployees();
        assertEquals(expectedEmployeeCollection.size(), actualEmployeeCollection.size());
        assertTrue(actualEmployeeCollection.containsAll(expectedEmployeeCollection));
    }

}
