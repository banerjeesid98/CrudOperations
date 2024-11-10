package com.example.CrudOperations.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.example.CrudOperations.entity.Employee;
import com.example.CrudOperations.repository.EmployeeRepository;
import com.example.CrudOperations.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.MockitoAnnotations;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setAge(30);
        employee.setSalary(50000.0);
    }

    @Test
    public void testCreateEmployee() {
        employeeService.createEmployee(employee);
        Mockito.verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGetEmployeeById() {
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Employee found = employeeService.getEmployeeById(1L);
        assertThat(found).isNotNull();
        assertEquals("John Doe", found.getName());
    }

    @Test
    public void testUpdateSalaryById() {
        Mockito.when(employeeRepository.existsById(1L)).thenReturn(true);
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Employee updatedEmployee = employeeService.updateSalaryById(1L, 60000.0);
        /*Mockito.verify(employeeRepository, times(1)).updateSalaryById(1L, 60000.0);

        Employee updatedEmployee = employeeService.getEmployeeById(1L);*/
        assert updatedEmployee != null;
        assertEquals(60000.0, updatedEmployee.getSalary());
    }

    @Test
    public void testDeleteEmployeeById() {
        Mockito.when(employeeRepository.existsById(1L)).thenReturn(true);
        employeeService.deleteEmployeeById(1L);
        Mockito.verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        ArrayList<Employee> allEmployees = employeeService.getAllEmployees();
        assertThat(allEmployees).isNotEmpty();
        assertEquals(1, allEmployees.size());
    }


}



