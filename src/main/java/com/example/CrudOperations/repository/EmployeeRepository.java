package com.example.CrudOperations.repository;


import com.example.CrudOperations.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    ArrayList<Employee> findByAge(int age);

    @Modifying
    @Query("UPDATE Employee e SET  e.salary = :salary WHERE e.id = :id")
    void updateSalaryById(@Param("id") Long id, @Param("salary") double salary);
}
