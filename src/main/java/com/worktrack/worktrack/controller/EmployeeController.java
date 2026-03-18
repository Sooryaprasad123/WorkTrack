package com.worktrack.worktrack.controller;

import com.worktrack.worktrack.dto.EmployeeResponse;
import com.worktrack.worktrack.dto.EmployeeUpdateRequest;
import com.worktrack.worktrack.dto.SuccessResponse;
import com.worktrack.worktrack.entity.Employee;
import com.worktrack.worktrack.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService empService;

    public EmployeeController(EmployeeService empService) {
        this.empService = empService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody Employee employee) {
        EmployeeResponse savedEmployee = empService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeDetailsById(@PathVariable Long id) {
        EmployeeResponse response = empService.GetEmployeeById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity <Page<EmployeeResponse>> getAllEmployeeDetails(@RequestParam int page, @RequestParam(defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size) ;

        Page<EmployeeResponse> getAllEmployeeResponse = empService.getAllEmployeeDetails(pageable);
        return ResponseEntity.ok(getAllEmployeeResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeUpdateRequest request, @PathVariable Long id){

        EmployeeResponse response =  empService.updateEmployee(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<SuccessResponse> deleteEmployee(@PathVariable Long id){

            SuccessResponse response = empService.softDeleteEmployee(id);

            return ResponseEntity.ok(response);
        }


}
