package com.etm.View;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etm.dao.EmployeeDao;
import com.etm.entity.Employee;
@WebServlet("/addDeveloper")
public class addDeveloper extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");

        long phoneNumber = Long.parseLong(req.getParameter("phoneNumber"));
        double salary = Double.parseDouble(req.getParameter("salary"));
        
        Employee employee = new Employee();
        
        employee.setEmail(email);
        employee.setEmployeeName(name);
        employee.setGender(gender);
        employee.setPassword(password);
        employee.setPhoneNumber(phoneNumber);
        employee.setRole("Developer");
        employee.setSalary(salary);

        EmployeeDao employeeDao = new EmployeeDao();

        employeeDao.saveEmployee(employee);

        PrintWriter printWriter = resp.getWriter();
        printWriter.print("<h1>Employee Saved Successfully</h1>");
	}

}
