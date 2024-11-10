package com.example.CrudOperations.controller;


import com.example.CrudOperations.entity.Employee;
import com.example.CrudOperations.service.EmployeeService;

import com.example.CrudOperations.service.EmployeeServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {



    Gson gson= new Gson();
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeServiceImpl.getEmployeeById(id);
        if(employee == null){
            System.out.println("Employee with id " + id + " does not exist.");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        }
    }

    /*@GetMapping("/employee/{age}")
    public ResponseEntity<List<Employee>> getEmployeesByAge(@PathVariable Integer age) {
        List<Employee> employees = employeeServiceImpl.getEmployeesByAge(age);
        if(employees == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(employees,HttpStatus.OK);
        }
    }*/

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeServiceImpl.getAllEmployees();
        if(employees == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(employees,HttpStatus.OK);
        }
    }

    @PostMapping("/employee")
    public void createEmployee (@RequestBody Employee employee) {
        employeeServiceImpl.createEmployee(employee);
        System.out.println("Created Employee successfully");
    }

    @PutMapping("/employee/{id}/{salary}")
    public ResponseEntity<Employee> updateEmployee (@PathVariable Long id, @PathVariable Double salary) {

        if(employeeServiceImpl.getEmployeeById(id) == null){
            System.out.println("Employee with id " + id + " does not exist.");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        Employee employee = employeeServiceImpl.updateSalaryById(id, salary);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);

    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if(employeeServiceImpl.getEmployeeById(id) == null){
            System.out.println("Employee with id " + id + " does not exist.");
            return new ResponseEntity<String>(gson.toJson("Employee with id " + id + " does not exist."),HttpStatus.NOT_FOUND);
        }
        employeeServiceImpl.deleteEmployeeById(id);
        return new ResponseEntity<String>(gson.toJson("Employee deleted successfully"),HttpStatus.OK);
    }


}
