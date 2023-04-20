package com.olegkochurov.spring.rest.dao;

import com.olegkochurov.spring.rest.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

   public Employee getEmployee(int id);

    public void deleteEmployee(int id);
}
