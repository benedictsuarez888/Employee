package com.example.controller;

import com.example.model.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public Page<Employee> getEmployee(Pageable pageable) {
        return employeeService.selectAllEmployees(pageable);
    }

    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @Valid @RequestBody Employee employeeRequest){

        return employeeService.editEmployee(id, employeeRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        return employeeService.removeEmployee(id);
    }
}
