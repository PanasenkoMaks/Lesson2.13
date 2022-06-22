package com.example.lesson213;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Objects;

public class Employee {
    private final String name;
    private final String surname;
    private final int salary;
    private final int department;

    public Employee(String name, String surname, int salary, int department) {
        this.name = StringUtils.capitalize(name.toLowerCase(Locale.ROOT));
        this.surname = StringUtils.capitalize(surname.toLowerCase(Locale.ROOT));
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getSalary() {
        return salary;
    }

    public int getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && department == employee.department && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, salary, department);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}
