package com.example.lesson213.controller;

import com.example.lesson213.Employee;
import com.example.lesson213.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

        private final DepartmentService departmentService;

        public DepartmentController(DepartmentService departmentService) {
            this.departmentService = departmentService;
        }
        @GetMapping("/min-salary")
        public Employee findMinSalaryDepartmet  (@RequestParam int department){
            return departmentService.findMinSalaryDepartmet(department);
        }
        @GetMapping("/max-salary")
        public Employee findMaxSalaryDepartmet (@RequestParam int department){
            return departmentService.findMaxSalaryDepartmet(department);
        }
        @GetMapping("/all")
        public List<Employee> findAllByDepartment (@RequestParam int department){
            return departmentService.findAllByDepartment(department);
        }
        @GetMapping(value = "/all", params = {"department"})
        public Map<Integer, List<Employee>> findAll(){
            return departmentService.findAll();
        }

    }
