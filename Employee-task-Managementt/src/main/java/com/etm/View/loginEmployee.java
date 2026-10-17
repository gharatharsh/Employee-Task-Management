package com.etm.View;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.etm.dao.EmployeeDao;
import com.etm.entity.Employee;

@WebServlet("/loginEmployee")
public class loginEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        

        EmployeeDao employeeDao = new EmployeeDao();
        Employee employee = employeeDao.loginEmployee(email, password);

        PrintWriter printWriter = resp.getWriter();

        if (employee != null) {
            printWriter.print("<h1>Employee Login Successfully</h1>");
            String role = employee.getRole();
            if(role.equals("Manager")) {
            	RequestDispatcher requestDispacter = req.getRequestDispatcher("manager.html");
            	
            	requestDispacter.include(req, resp);
            }
            else if(role.equals("Developer")) {
            	int id = employee.getEmployeeId();
            	//
            	HttpSession s =  req.getSession();
            	s.setAttribute("empId",id);
            	RequestDispatcher requestDispacter = req.getRequestDispatcher("Developer.html");
            	
            	requestDispacter.include(req, resp);
            }
        } else {
            printWriter.print("<h1>Invalid Email or Password</h1>");
            
            RequestDispatcher requestDispacter = req.getRequestDispatcher("loginEmplopyee.html");
            
            requestDispacter.include(req, resp);
        
        }
    }
}
