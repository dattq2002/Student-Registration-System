<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Send Application Page</title>
    </head>
    <body><%
        UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
        if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
            response.sendRedirect("login.jsp");
            return;
        }
        %>
        <h1>Send Application</h1>
        <form action="MainController">
            Type: <select name="selectOption">
                <option>Choose Type</option>
                <option value="Report">Report</option>
                <option value="Registration Presentation">Registration Presentation</option>
            </select><br>
            Student ID: <input type="text" min="0" required="" name="id" value="" placeholder="vd: SE16-453123"><br>
            Student Name: <input type="text" name="stname" value="" required="" /><br>
            Lecture Name: <input type="text" required="" name="lecname" value=""><br>
            Course: <input type="text" required="" name="course" value="" placeholder="vd:NJS-1701"><br>
            Reason: <textarea name="reason" rows="5" cols="30" required="" placeholder="Ghi rõ lý do nộp đơn nhằm mục đích gì"></textarea><br>
            <input type="submit" value="Send" name="action" />
        </form>
        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message != null) {
        %>
        <h3><%=message%></h3>
        <%
            }
        %>
    </body>
</html>
