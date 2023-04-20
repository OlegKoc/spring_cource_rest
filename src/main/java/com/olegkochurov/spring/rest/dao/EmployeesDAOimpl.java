package com.olegkochurov.spring.rest.dao;

import com.olegkochurov.spring.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeesDAOimpl implements EmployeeDao {    // класс ответственный за работу с БД(к нему обращается контроллер)

    @Autowired
    private SessionFactory sessionFactory; // открываем сессию(прописано в зависимостях

    @Override

    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession(); // получаем сессию
        List<Employee> allEmployees = session.createQuery("from Employee ", Employee.class).getResultList(); // получаем работников и сохраняем в allEployees
        return allEmployees;
    }


    @Override
    public void saveEmployee(Employee employee) {     // метод ответственный за БД и его метод вызывает класс Сервис
        Session session = sessionFactory.getCurrentSession(); // получаем сессию
        session.saveOrUpdate(employee); // сохраняем работника

    }


    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();


    }
}
