package com.example.lesson213.service;
import com.example.lesson213.Employee;

import java.util.Collection;

public interface EmployeeService {

    Employee add(String name, String surname, int salary, int department);
    Employee find(String name, String surname);
    Employee remove (String name, String surname);

    Collection<Employee> findAll();
}
