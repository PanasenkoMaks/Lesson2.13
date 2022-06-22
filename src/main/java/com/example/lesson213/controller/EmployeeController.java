package com.example.lesson213.controller;

import com.example.lesson213.Employee;
import com.example.lesson213.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public Employee add (@RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam int salary,
                         @RequestParam int department){
        return employeeService.add(name, surname, salary, department);
    }
    @GetMapping("/find")
    public Employee find (@RequestParam String name, @RequestParam String surname){
        return employeeService.find(name, surname);
    }
    @GetMapping("/remove")
    public Employee remove (@RequestParam String name, @RequestParam String surname){
        return employeeService.remove(name, surname);
    }
    @GetMapping("/all")
    public Collection<Employee> findAll(){
        return employeeService.findAll();
    }

}
