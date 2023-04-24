package com.olegkochurov.spring.rest.controller;

import com.olegkochurov.spring.rest.entity.Employee;
import com.olegkochurov.spring.rest.exception_handling.EmployeeIncorrectData;
import com.olegkochurov.spring.rest.exception_handling.NoSuchEmployeeException;
import com.olegkochurov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/employees/{id}")  // метод для возвращения работника по его ID
    public Employee getEmployee(@PathVariable int id) throws NoSuchFieldException {    // @PathVariable - используется для получения временной переменной из адреса запроса
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchFieldException("There is employee with id = " + id + " in DataBase");
        }

        return employee;


    }

    @PostMapping("/employees")  // добавляем нового сотрудника
    //  @PostMapping связывает HTTP запрос, использующий HTTP  метод POST с методом контроллера
    public Employee addNewEmployee(@RequestBody Employee employee) {   // @RequestBody Employee employee - получаем работника из тела запроса
        employeeService.saveEmployee(employee);
        return employee;

    }

    @PutMapping("/employees")   // изменяем работика
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}") // удаляем работника по его ID
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id); // проверяем есть ли такой работник в базе
        if (employee == null) {
            throw new NoSuchEmployeeException("с таким ID работника не сущесствует");
        }
        employeeService.deleteEmployee(id);
        return "работник с ID = " + id + "удален";

    }


}
