package com.example.lesson213.service;

import com.example.lesson213.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee add(String name, String surname, int salary, int department) {
        if (employees.containsKey(getKeys(name, surname))) {
            throw new IllegalArgumentException("Такой сотрудник уже есть");
        }
        if (StringUtils.isEmpty(getKeys(name, surname))){
            throw new BadParamsException();
        }
        Employee employee = new Employee(name, surname, salary, department);
        employees.put(getKeys(name, surname), employee);
        return employee;
    }

    @Override
    public Employee find(String name, String surname) {
        Employee employee = employees.get(getKeys(name, surname));
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Employee remove(String name, String surname) {
        Employee employee = employees.remove(getKeys(name, surname));
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    private String getKeys(String name, String surname) {
        return name + surname;
    }
    @Override
    public Collection<Employee> findAll(){
        return employees.values();
    }

}
