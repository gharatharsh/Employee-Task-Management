package com.etm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.etm.entity.Employee;
import com.etm.entity.Task;

public class EmployeeDao {

    EntityManager entityManager = Persistence
            .createEntityManagerFactory("dbInfo")
            .createEntityManager();

    EntityTransaction entityTransaction = entityManager.getTransaction();

    // Save Employee
    public void saveEmployee(Employee employee) {

        entityTransaction.begin();
        entityManager.persist(employee);
        entityTransaction.commit();
    }

    // Update Employee
    public void updateEmployee(Employee employee) {

        entityTransaction.begin();
        entityManager.merge(employee);
        entityTransaction.commit();
    }

    // Delete Employee
    public void deleteEmployee(int employeeId) {

        Employee employee = entityManager.find(Employee.class, employeeId);

        if (employee != null) {
            entityTransaction.begin();
            entityManager.remove(employee);
            entityTransaction.commit();
        }
    }

    // Find Employee by ID
    public Employee findEmployee(int employeeId) {

        return entityManager.find(Employee.class, employeeId);
    }

    // Find All Employees
    public List<Employee> findAllEmployee() {

        return entityManager
                .createQuery(
                        "SELECT employee FROM Employee employee",
                        Employee.class)
                .getResultList();
    }

    // Employee Login
    public Employee loginEmployee(String email, String password) {

        Query query = entityManager.createQuery(
                "SELECT employee FROM Employee employee " +
                "WHERE employee.email = ?1 AND employee.password = ?2");

        query.setParameter(1, email);
        query.setParameter(2, password);

        try {
            return (Employee) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Assign Task to Employee
    public void assignTask(int taskId, int employeeId) {

        Employee employee = entityManager.find(Employee.class, employeeId);
        Task task = entityManager.find(Task.class, taskId);

        if (employee != null && task != null) {

            employee.getTasks().add(task);

            entityTransaction.begin();
            entityManager.merge(employee);
            entityTransaction.commit();
        }
    }

    // Get Developer Status
    public String getDeveloperStatus(int employeeId) {

        Employee employee = entityManager.find(Employee.class, employeeId);

        if (employee == null) {
            return null;
        }

        if (employee.getTasks().isEmpty()) {
            return "Complete";
        }

        for (Task task : employee.getTasks()) {

            if ("Ongoing".equalsIgnoreCase(task.getStatus())) {
                return "Ongoing";
            }
        }

        return "Complete";
    }

    // Delete Developer by ID
    public void deleteDeveloper(int employeeId) {

        Employee employee = entityManager.find(Employee.class, employeeId);

        if (employee != null) {

            entityTransaction.begin();
            entityManager.remove(employee);
            entityTransaction.commit();
        }
    }
    
 // Delete Developer only when all assigned tasks are completed
    public boolean DeleteDeveloper(int employeeId) {

        Employee employee = entityManager.find(Employee.class, employeeId);

        if (employee == null) {
            return false;
        }

        // Check assigned tasks
        for (Task task : employee.getTasks()) {

            if ("Ongoing".equalsIgnoreCase(task.getStatus())) {
                return false;
            }
        }

        entityTransaction.begin();

        // Break relationship between Employee and Tasks
        employee.getTasks().clear();

        // Delete employee
        entityManager.remove(employee);

        entityTransaction.commit();

        return true;
    }


}