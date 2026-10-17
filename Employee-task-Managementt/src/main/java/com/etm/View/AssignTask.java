package com.etm.View;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etm.dao.EmployeeDao;

@WebServlet("/assignTask")
public class AssignTask extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int taskId = Integer.parseInt(req.getParameter("taskId"));
        int employeeId = Integer.parseInt(req.getParameter("employeeId"));

        EmployeeDao employeeDao = new EmployeeDao();

        employeeDao.assignTask(taskId, employeeId);

        PrintWriter printWriter = resp.getWriter();

        printWriter.print("<h1>Task Assigned Successfully</h1>");
    }
}
