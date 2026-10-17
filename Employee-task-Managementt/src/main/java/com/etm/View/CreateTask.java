package com.etm.View;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etm.dao.taskDao;
import com.etm.entity.Task;

@WebServlet("/createTask")
public class CreateTask extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String taskname = req.getParameter("task");
        int duration = Integer.parseInt(req.getParameter("duration"));

        Task task = new Task();

        task.setTaskName(taskname);
        task.setDuration(duration);
        task.setStatus("Ongoing");

        taskDao TaskDao = new taskDao();
        TaskDao.saveTask(task);

        PrintWriter printWriter = resp.getWriter();
        printWriter.print("<h1>Task Saved Successfully</h1>");
    }
}