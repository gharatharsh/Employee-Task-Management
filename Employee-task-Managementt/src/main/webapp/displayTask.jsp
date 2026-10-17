<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.etm.entity.Task" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Display All Tasks</title>
</head>
<body>

<h2>All Tasks</h2>
<form>

</form>

<table border="1">
    <tr>
        <th>Task ID</th>
        <th>Task Name</th>
        <th>Duration</th>
        <th>Status</th>
    </tr>

    <%
        List<Task> taskList = (List<Task>) request.getAttribute("taskList");

        if (taskList != null && !taskList.isEmpty()) {
            for (Task task : taskList) {
    %>
    <tr>
        <td><%= task.getTaskId() %></td>
        <td><%= task.getTaskName() %></td>
        <td><%= task.getDuration() %></td>
        <td><%= task.getStatus() %></td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="4">No tasks found.</td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>