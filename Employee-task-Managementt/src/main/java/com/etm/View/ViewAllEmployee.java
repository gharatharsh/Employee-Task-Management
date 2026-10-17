package com.etm.View;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etm.dao.EmployeeDao;
import com.etm.entity.Employee;

@WebServlet("/ViewEmployee.html")
public class ViewAllEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        EmployeeDao employeeDao = new EmployeeDao();

        // Check login
        Employee employee = employeeDao.loginEmployee(email, password);

        if (employee != null) {

            // Login successful
            List<Employee> employees = employeeDao.findAllEmployee();

            req.setAttribute("employees", employees);
            req.setAttribute("loginSuccess", true);

        } else {

            // Login failed
            req.setAttribute("loginError", "Invalid Email or Password");
        }

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/ViewAllEmployee.jsp");

        dispatcher.forward(req, resp);
    }
}