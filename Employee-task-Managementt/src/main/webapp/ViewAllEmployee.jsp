<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.etm.entity.Employee"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Employee Details</title>

</head>

<body>

    <fieldset>

        <legend>Employee Login</legend>

        <form action="ViewAllEmployee" method="get">

            <label>Email:</label>

            <input type="email"
                   name="email"
                   placeholder="Enter Email"
                   required>

            <br><br>

            <label>Password:</label>

            <input type="password"
                   name="password"
                   placeholder="Enter Password"
                   required>

            <br><br>

            <input type="submit" value="View All">

        </form>

    </fieldset>

    <br>

<%
    String loginError = (String) request.getAttribute("loginError");

    if (loginError != null) {
%>

        <p style="color:red;">
            <%= loginError %>
        </p>

<%
    }

    Boolean loginSuccess =
            (Boolean) request.getAttribute("loginSuccess");

    List<Employee> employees =
            (List<Employee>) request.getAttribute("employees");

    if (Boolean.TRUE.equals(loginSuccess)
            && employees != null
            && !employees.isEmpty()) {

        for (Employee employee : employees) {
%>

            <fieldset>

                <legend>Employee Details</legend>

                <p>
                    <b>Employee ID:</b>
                    <%= employee.getEmployeeId() %>
                </p>

                <p>
                    <b>Name:</b>
                    <%= employee.getEmployeeName() %>
                </p>

                <p>
                    <b>Email:</b>
                    <%= employee.getEmail() %>
                </p>

                <p>
                    <b>Salary:</b>
                    <%= employee.getSalary() %>
                </p>

                <p>
                    <b>Gender:</b>
                    <%= employee.getGender() %>
                </p>

                <p>
                    <b>Phone:</b>
                    <%= employee.getPhoneNumber() %>
                </p>

                <p>
                    <b>Role:</b>
                    <%= employee.getRole() %>
                </p>

            </fieldset>

            <br>

<%
        }

    } else if (Boolean.TRUE.equals(loginSuccess)) {
%>

        <p>No employees found.</p>

<%
    }
%>

</body>

</html>