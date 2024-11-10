package com.example.CrudOperations.controllerTest;


import com.example.CrudOperations.controller.EmployeeController;
import com.example.CrudOperations.repository.EmployeeRepository;
import com.example.CrudOperations.service.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CrudOperations.entity.Employee;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
@SpringBootTest
public class EmployeeControllerTest {


        @Autowired
        private EmployeeController employeeController;

        @MockBean
        private EmployeeServiceImpl employeeServiceImpl;

        @Test
        public void getEmployeeByIdFoundTest() {
            Employee employee = new Employee();
            employee.setId(11L);
            employee.setName("John Doe");

            when(employeeServiceImpl.getEmployeeById(11L)).thenReturn(employee);

            ResponseEntity<Employee> response = employeeController.getEmployeeById(11L);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            Assertions.assertThat(response.getBody()).isNotNull();
            Assertions.assertThat(response.getBody().getId()).isEqualTo(11L);
            Assertions.assertThat(response.getBody().getName()).isEqualTo("John Doe");
        }

        @Test
        public void getEmployeeByIdNotFoundTest() {
            when(employeeServiceImpl.getEmployeeById(11L)).thenReturn(null);

            ResponseEntity<Employee> response = employeeController.getEmployeeById(11L);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }

        @Test
        public void getAllEmployeesTest() {
            Employee emp1 = new Employee();
            Employee emp2 = new Employee();
            emp1.setId(11L);
            emp1.setName("John Doe");
            emp2.setId(12L);
            emp2.setName("Jane Doe");
            List<Employee> employees = Arrays.asList(emp1, emp2);

            when(employeeServiceImpl.getAllEmployees()).thenReturn((ArrayList<Employee>) employees);

            ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            Assertions.assertThat(response.getBody()).isNotNull().isNotEmpty();
            Assertions.assertThat(response.getBody()).containsExactly(emp1, emp2);
        }

        @Test
        public void getAllEmployeesNotFoundTest() {
            when(employeeServiceImpl.getAllEmployees()).thenReturn(null);

            ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }

        @Test
        public void createEmployeeTest() {
            Employee employee = new Employee();
            employee.setId(11L);
            employee.setName("John Doe");
            Mockito.doNothing().when(employeeServiceImpl).createEmployee(employee);

            employeeController.createEmployee(employee);

            Mockito.verify(employeeServiceImpl, Mockito.times(1)).createEmployee(employee);
        }

        @Test
        public void updateEmployeeFoundTest() {
            Employee employee = new Employee();
            employee.setId(11L);
            employee.setSalary(50000.0);

            when(employeeServiceImpl.getEmployeeById(11L)).thenReturn(employee);
            when(employeeServiceImpl.updateSalaryById(11L, 60000.0)).thenReturn(employee);

            ResponseEntity<Employee> response = employeeController.updateEmployee(11L, 60000.0);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            Assertions.assertThat(response.getBody()).isNotNull();
            Assertions.assertThat(response.getBody().getSalary()).isEqualTo(60000.0);
        }

        @Test
        public void updateEmployeeNotFoundTest() {
            when(employeeServiceImpl.getEmployeeById(11L)).thenReturn(null);

            ResponseEntity<Employee> response = employeeController.updateEmployee(11L, 50000.0);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }

        @Test
        public void deleteEmployeeFoundTest() {
            Employee employee = new Employee();
            employee.setId(11L);

            when(employeeServiceImpl.getEmployeeById(11L)).thenReturn(employee);
            Mockito.doNothing().when(employeeServiceImpl).deleteEmployeeById(11L);

            ResponseEntity<String> response = employeeController.deleteEmployee(11L);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            Assertions.assertThat(response.getBody()).isEqualTo("{\"message\":\"Employee deleted successfully\"}");
        }

        @Test
        public void deleteEmployeeNotFoundTest() {
            when(employeeServiceImpl.getEmployeeById(11L)).thenReturn(null);

            ResponseEntity<String> response = employeeController.deleteEmployee(11L);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            Assertions.assertThat(response.getBody()).isEqualTo("{\"message\":\"Employee with id 11 does not exist.\"}");
        }
    }


   // getEmployeeById

