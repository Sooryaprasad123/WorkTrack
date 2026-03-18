package com.worktrack.worktrack.serviceImpl;

import com.worktrack.worktrack.dto.EmployeeResponse;
import com.worktrack.worktrack.dto.EmployeeUpdateRequest;
import com.worktrack.worktrack.dto.SuccessResponse;
import com.worktrack.worktrack.entity.Employee;
import com.worktrack.worktrack.enums.Status;
import com.worktrack.worktrack.exception.EmployeeDeactivatedException;
import com.worktrack.worktrack.exception.EmployeeNotFoundException;
import com.worktrack.worktrack.repository.EmployeeRepository;
import com.worktrack.worktrack.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponse createEmployee(Employee employee) {
        //Employee empDetails = new Employee();
        EmployeeResponse empResponse = new EmployeeResponse();

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Employee E mail already exist");
        }

        if (employee.getCreatedAt() == null) {
            employee.setCreatedAt(LocalDateTime.now());
        }
        employee.setStatus(Status.ACTIVE);

        employeeRepository.save(employee);

        empResponse.setName(employee.getName());

        BeanUtils.copyProperties(employee, empResponse);
        return empResponse;
    }

    @Override
    public EmployeeResponse GetEmployeeById(Long id) {

        EmployeeResponse empResponseById = new EmployeeResponse();
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("User not found"));

        BeanUtils.copyProperties(employee, empResponseById);

        return empResponseById;
    }

    @Override
    public Page<EmployeeResponse> getAllEmployeeDetails( Pageable pageable) {
//
        Page<Employee> employee =  employeeRepository.findAll(pageable);
      //    =  new ArrayList<>();
        Page<EmployeeResponse> empDetailsResponse = employee.map(emp-> {
                    EmployeeResponse empDetailResponse = new EmployeeResponse();
                    BeanUtils.copyProperties(emp,empDetailResponse);
                    return empDetailResponse;
                }
        ) ;

        return  empDetailsResponse;

//        return employeeRepository.findAll().stream().map(emp->{
//            EmployeeResponse res = new EmployeeResponse();
//            BeanUtils.copyProperties(emp,res);
//            return res;
//        }).toList();

    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest request) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("User Not found"));
            if(request.getName()!=null) {
                employee.setName(request.getName());

        }
            if(request.getRole()!=null){
                employee.setRole(request.getRole());
            }
            if(request.getStatus()!=null){
                employee.setStatus(request.getStatus());
            }
            employeeRepository.save(employee);
            EmployeeResponse response =  new EmployeeResponse();
            BeanUtils.copyProperties(employee,response);

        return response;
    }

    @Override
    public SuccessResponse softDeleteEmployee(Long id) {

        SuccessResponse response = new SuccessResponse();

        Employee employee =  employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException ("User not found"));

        if(employee.getStatus()==Status.INACTIVE){
            throw new EmployeeDeactivatedException("Employee already deactivated");
        }
        else {
            employee.setStatus(Status.INACTIVE);
            employeeRepository.save(employee);
           response.setMessage("Success");
        }

        return response;
    }


}
