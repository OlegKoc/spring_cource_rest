package com.olegkochurov.spring.rest.controller;

import com.olegkochurov.spring.rest.entity.Employee;
import com.olegkochurov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController   // аннотация , которая управляет REST запросами  и ответами
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {       // метод возвращает всех работников(List<Employee>
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

}
