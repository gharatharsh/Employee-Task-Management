package com.etm.View;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etm.dao.EmployeeDao;

@WebServlet("/deleteDeveloper")
public class deleteDeveloper extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int employeeId = Integer.parseInt(req.getParameter("employeeId"));

     
        EmployeeDao employeeDao = new EmployeeDao();

        // Get developer status from database
        String status = employeeDao.getDeveloperStatus(employeeId);

        PrintWriter printWriter = resp.getWriter();

       
        if (status == null) {

            printWriter.print("<h1>Developer Not Found</h1>");

        }
        // Developer task is completed
        else if (status.equals("Complete")) {

            employeeDao.deleteDeveloper(employeeId);

            printWriter.print("<h1>Developer Deleted Successfully</h1>");

        }
        // Developer task is still ongoing
        else if (status.equals("Ongoing")) {

            printWriter.print("<h1>Can't Delete Developer Yet</h1>");
            printWriter.print("<h2>Because the task is Ongoing.</h2>");

        }
        
        else {

            printWriter.print("<h1>Can't Delete Developer</h1>");

        }
    }
}