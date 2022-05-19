//package dev.ji.employeenewnew.service;
//
//import dev.ji.employeenewnew.exception.EmployeeArrayFullException;
//import dev.ji.employeenewnew.exception.EmployeeHasAlreadyAddedException;
//import dev.ji.employeenewnew.exception.EmployeeNotFoundException;
//import dev.ji.employeenewnew.model.Employee;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmployeeServiceArchiveImpl implements EmployeeService {
//
//    private int arrayCapacity = 2;//емкость массива сотрудников
//    private Employee[] employees = new Employee[arrayCapacity];//массив сотрудников
//    private int size;//счетчик для методов добавления и удаления
//
//    @Override
//    public String addEmployee(String firstName, String lastName) {
//        if (size >= arrayCapacity) {
//            throw new EmployeeArrayFullException("Employee array is full");
//        }
//
//        Employee addingEmployee = new Employee(firstName, lastName);
//        int addingEmployeeIndex = getEmloyeeIndex(addingEmployee);
//
//        if (addingEmployeeIndex != -1) {
//            throw new EmployeeHasAlreadyAddedException("This employee has been already added");
//        }
//
//        employees[size++] = addingEmployee;
//        return "Сотрудник " + addingEmployee.getFirstName() + " " + addingEmployee.getLastName() + " успешно создан";
//        //return addingEmployee - на разборе 22.01 предлагали такое решение,
//        //но, внимательно прочитав задание, переправил на корректный вывод согласно ТЗ из ДЗ (шаг 10) - и метод на String;
//
//    }
//
//    @Override
//    public String removeEmployee(String firstName, String lastName) {
//        Employee removingEmployee = new Employee(firstName, lastName);
//        int employeeIndex = getEmloyeeIndex(removingEmployee);
//
//        if (employeeIndex == -1) {
//            throw new EmployeeNotFoundException ("We can't find this employee");
//        }
//
//        Employee removedEmployee = employees[employeeIndex];
//        System.arraycopy(employees,employeeIndex+1, employees, employeeIndex,size - employeeIndex);
//        size--;
//        return "Сотрудник " + removedEmployee.getFirstName() + " " + removedEmployee.getLastName() + " успешно удален";
//        //return removedEmployee - на разборе 22.01 предлагали такое решение,
//        //но, внимательно прочитав задание, переправил на корректный вывод согласно ТЗ из ДЗ (шаг 10) - и метод на String;
//    }
//
//    private int getEmloyeeIndex(Employee employee) {
//        for (int i = 0; i < size; i++) {
//            if (employee.equals(employees[i])) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    @Override
//    public Employee findEmployee(String firstName, String lastName) {
//        Employee findingEmployee = new Employee(firstName, lastName);
//        int employeeIndex = getEmloyeeIndex(findingEmployee);
//
//        if (employeeIndex == -1) {
//            throw new EmployeeNotFoundException ("We can't find this employee");
//        }
//
//        return employees[employeeIndex];
//    }
//
//}
