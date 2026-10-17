package com.etm.View;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etm.dao.EmployeeDao;
import com.etm.dao.taskDao;
import com.etm.entity.Employee;
import com.etm.entity.Task;

@WebServlet("/viewAllTask")
public class viewAllTask extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		int id = (Integer) session.getAttribute("empId");

		EmployeeDao employeeDao = new EmployeeDao();
		Employee employee = employeeDao.findEmployee(id);
		
		
		List<Task> taskList = employee.getTasks();
		
		req.setAttribute("taskList", taskList);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("displayTask.jsp");
		requestDispatcher.include(req, resp);
	}

}