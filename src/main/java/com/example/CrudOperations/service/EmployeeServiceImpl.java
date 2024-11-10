package com.example.CrudOperations.service;


import com.example.CrudOperations.entity.Employee;
import com.example.CrudOperations.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public Employee updateSalaryById(Long id, Double salary) {
        if (employeeRepository.existsById(id)) {
            Employee employee = employeeRepository.findById(id).orElse(null);
            if (employee != null) {
                employee.setSalary(salary); // Update the salary here
                employeeRepository.save(employee); // Save the updated employee
            }
            return employee; // Return the updated employee
        }
        return null;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        if(employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        }
        else {
            System.out.println("Employee with id " + id + " does not exist.");
        }
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        return (ArrayList<Employee>)employeeRepository.findAll();
    }

    @Override
    public ArrayList<Employee> getEmployeesByAge(int age) {
        return (ArrayList<Employee>)employeeRepository.findByAge(age);
    }
}
