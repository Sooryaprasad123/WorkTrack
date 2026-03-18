package com.worktrack.worktrack.service;

import com.worktrack.worktrack.dto.EmployeeResponse;
import com.worktrack.worktrack.dto.EmployeeUpdateRequest;
import com.worktrack.worktrack.dto.SuccessResponse;
import com.worktrack.worktrack.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

    EmployeeResponse createEmployee(Employee employee);

    EmployeeResponse GetEmployeeById(Long id);

    Page<EmployeeResponse> getAllEmployeeDetails(Pageable pageable);

    EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest request);

    SuccessResponse softDeleteEmployee(Long id);

}
