package com.example.lesson213.service;

import com.example.lesson213.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;
    public DepartmentServiceImpl(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @Override
    public Employee findMinSalaryDepartmet (int department){
       return employeeService.findAll().stream()
               .filter(e -> e.getDepartment() == department)
               .min(Comparator.comparingInt(Employee::getSalary))
               .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee findMaxSalaryDepartmet(int department) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);

    }

    @Override
    public List<Employee> findAllByDepartment(int department) {
       return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> findAll() {
       return employeeService.findAll().stream()
               .collect(Collectors.groupingBy(Employee::getDepartment));

    }
}
