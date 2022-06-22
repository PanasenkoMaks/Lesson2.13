package com.example.lesson213.service;

import com.example.lesson213.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.stream.Stream;

class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeServiceImpl();

    @ParameterizedTest
    @MethodSource("paramsForPositiveTest")
    void addPositiveTest(String name, String surname, int department, int salary) {
       Employee result = new Employee(name, surname, department, salary);
        Assertions.assertThat(employeeService.add(name, surname, department, salary)).isEqualTo(result);

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(()->employeeService.add(name, surname, department, salary));

    }

    @ParameterizedTest
    @MethodSource("paramsForPositiveTest")
    void findTest(String name, String surname, int department, int salary) {
        Employee result = new Employee(name, surname, department, salary);
        employeeService.add(name, surname, department, salary);
        Assertions.assertThat(employeeService.find(name, surname)).isEqualTo(result);

        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.find(null, null));

    }

    @ParameterizedTest
    @MethodSource("paramsForPositiveTest")
    void removeTest(String name, String surname, int department, int salary) {
        Employee result = new Employee(name, surname, department, salary);
        employeeService.add(name, surname, department, salary);
        Assertions.assertThat(employeeService.remove(name, surname)).isEqualTo(result);

        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.remove(name, surname));
    }
    @Test
    public void findAll(){
        Collection<Employee> result = employeeService.findAll();
        Assertions.assertThat(employeeService.findAll()).isEqualTo(result);

    }

    public static Stream<Arguments> paramsForPositiveTest() {
        return Stream.of(
                Arguments.of("Ivan", "Ivanov", 1, 43_000),
                Arguments.of("Maks", "Ivanov", 1, 23_000),
                Arguments.of("Petr", "Petrov", 2, 43_0000)
        );
    }
    }
