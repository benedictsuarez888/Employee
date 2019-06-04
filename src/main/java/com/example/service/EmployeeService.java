package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Page<Employee> selectAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Optional<Employee> selectEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee editEmployee(Long id,
                                 Employee employeeRequest){

        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(employeeRequest.getFirstName());
                    employee.setLastName(employeeRequest.getLastName());
                    return employeeRepository.save(employee);
                }).orElseThrow(() ->
                        new NotFoundException(
                                String.format("Employee ID Number %d not found.", id)));
    }

    public ResponseEntity<?> removeEmployee(Long id){
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() ->
                        new NotFoundException(
                                String.format("Employee ID Number %d not found.", id)));
    }
}
