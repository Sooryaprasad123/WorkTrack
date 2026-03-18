package com.worktrack.worktrack.repository;

import com.worktrack.worktrack.entity.Employee;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Email
    boolean existsByEmail(String email);
    //Optional<Employee> findByEmail(String email);



}
