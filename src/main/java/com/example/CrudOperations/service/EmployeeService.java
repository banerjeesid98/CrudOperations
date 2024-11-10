package com.example.CrudOperations.service;

import com.example.CrudOperations.entity.Employee;

import java.util.ArrayList;

public interface EmployeeService {

    // TODO: Implement CRUD operations for Employee entity
    public void createEmployee(Employee employee);
    public Employee getEmployeeById(Long id);
    public Employee updateSalaryById(Long id,Double salary);
    public void deleteEmployeeById(Long id);
    public ArrayList<Employee> getAllEmployees();
    public ArrayList<Employee> getEmployeesByAge(int age);
}
