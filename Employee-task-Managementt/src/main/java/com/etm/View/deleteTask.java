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

@WebServlet("/deleteTask")
public class deleteTask extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {

            int taskId = Integer.parseInt(req.getParameter("taskId"));

            taskDao taskDao = new taskDao();
            Task task = taskDao.findTask(taskId);

            if (task == null) {
                out.print("<h2>Task Not Found</h2>");
                return;
            }

            taskDao.deleteTask(task);

            out.print("<h2>Task Deleted Successfully</h2>");

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("<h2>Invalid Task ID</h2>");

        } catch (Exception e) {
            getServletContext().log("Failed to delete task", e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print(
                "<h2>Unable to delete task. Please try again.</h2>"
            );
        }
    }
}