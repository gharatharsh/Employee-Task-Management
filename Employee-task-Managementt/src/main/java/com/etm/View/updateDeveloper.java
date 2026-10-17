package com.etm.View;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etm.dao.EmployeeDao;
import com.etm.dao.taskDao;
import com.etm.entity.Employee;
import com.etm.entity.Task;

@WebServlet("/updateDeveloper")
public class updateDeveloper extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		try {
			int employeeId = Integer.parseInt(req.getParameter("employeeId"));

			String option = req.getParameter("option");
			String value = req.getParameter("value");

			EmployeeDao employeeDao = new EmployeeDao();
			taskDao taskDao = new taskDao();

			if (option.equals("employeeName")) {

				Employee employee = employeeDao.findEmployee(employeeId);

				if (employee != null) {
					employee.setEmployeeName(value);
					employeeDao.updateEmployee(employee);
					out.println("<h2>Name Updated Successfully</h2>");
				} else {
					out.println("<h2>Employee Not Found</h2>");
				}

			} else if (option.equals("email")) {

				Employee employee = employeeDao.findEmployee(employeeId);

				if (employee != null) {
					employee.setEmail(value);
					employeeDao.updateEmployee(employee);
					out.println("<h2>Email Updated Successfully</h2>");
				} else {
					out.println("<h2>Employee Not Found</h2>");
				}

			} else if (option.equals("phoneNumber")) {

				Employee employee = employeeDao.findEmployee(employeeId);

				if (employee != null) {
					long phoneNumber = Long.parseLong(value);
					employee.setPhoneNumber(phoneNumber);
					employeeDao.updateEmployee(employee);
					out.println("<h2>Phone Number Updated Successfully</h2>");
				} else {
					out.println("<h2>Employee Not Found</h2>");
				}

			} else if (option.equals("status")) {

				int taskId = Integer.parseInt(req.getParameter("taskId"));

				Task task = taskDao.findTask(taskId);

				if (task != null) {
					task.setStatus(value);
					taskDao.updateTask(task);
					out.println("<h2>Task Status Updated Successfully</h2>");
				} else {
					out.println("<h2>Task Not Found</h2>");
				}

			} else {
				out.println("<h2>Invalid Update Option</h2>");
			}

		} catch (NumberFormatException e) {
			out.println("<h2>Invalid ID or Phone Number</h2>");
		}
	}
}