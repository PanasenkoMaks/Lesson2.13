package com.example.lesson213.service;

import com.example.lesson213.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void BeforeEach(){
        List<Employee> employees = List.of(
                new Employee("Ivan", "Ivanov", 32_0000, 1),
                new Employee("Petr", "Ivanov", 10_000, 1),
                new Employee("Ivan", "Petrov", 20_000, 2),
                new Employee("Ivan", "Kurenkov", 21_000, 2)
        );
        when(employeeService.findAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryPositiveTest")
    public void employeeWithMaxSalaryPositiveTest(int department, Employee exspected){
        Assertions.assertThat(departmentService.findMaxSalaryDepartmet(department)).isEqualTo(exspected);
    }
    @Test
    public void employeeWithMaxSalaryNegativeTest(){
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->departmentService.findMaxSalaryDepartmet(3));
    }
    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryPositiveTest")
    public void employeeWithMinSalaryPositiveTest(int department, Employee exspected){
        Assertions.assertThat(departmentService.findMinSalaryDepartmet(department)).isEqualTo(exspected);
    }
    @Test
    public void employeeWithMinSalaryNegativeTest(){
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->departmentService.findMinSalaryDepartmet(3));
    }
    @ParameterizedTest
    @MethodSource("employeeWFindAllByDepartmentTest")
    public void employeeFindAllByDepartmentTest(int department, Employee exspected){
        org.junit.jupiter.api.Assertions.assertTrue(departmentService.findAllByDepartment(department)
                .contains(exspected));
    }
    @Test
    public void employeeFindAllTest(){
        Map<Integer, List<Employee>> result = departmentService.findAll();
        Assertions.assertThat(departmentService.findAll()).isEqualTo(result);
    }


    public static Stream<Arguments> employeeWithMaxSalaryPositiveTest(){
        return Stream.of(
                Arguments.of(1, new Employee("Ivan", "Ivanov", 32_0000, 1)),
                Arguments.of(2, new Employee("Ivan", "Kurenkov", 21_000, 2))
                );
    }
    public static Stream<Arguments> employeeWithMinSalaryPositiveTest(){
        return Stream.of(
                Arguments.of(1, new Employee("Petr", "Ivanov", 10_000, 1)),
                Arguments.of(2, new Employee("Ivan", "Petrov", 20_000, 2))
        );
    }
    public static Stream<Arguments> employeeWFindAllByDepartmentTest(){
        return Stream.of(
                Arguments.of(1, new Employee("Ivan", "Ivanov", 32_0000, 1)),
                Arguments.of(1, new Employee("Petr", "Ivanov", 10_000, 1)),
                Arguments.of(2, new Employee("Ivan", "Petrov", 20_000, 2)),
                Arguments.of(2, new Employee("Ivan", "Kurenkov", 21_000, 2))
        );
    }
}