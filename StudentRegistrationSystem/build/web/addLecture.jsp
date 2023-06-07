<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Lecture Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h3>Add Lecture Profile</h3>
        <form action="MainController">
            Lecture ID: <input type="number" name="lectureid" value="" required="" min="0" placeholder="more than 0"/><br>
            Lecture Code: <input type="text" name="lecturecode" value="" required="" minlength="2" placeholder="ex:LTxx"/><br>
            Lecture Name: <input type="text" name="lecturetname" value=""placeholder="ex: abc"/><br>
            BirthDay: <input type="text" name="lecturebirthday" value=""placeholder="ex: 1998-04-29"/><br>
            Email: <input type="text" name="lectureemail" value="" required=""placeholder="ex: abc@fe.edu.vn"/><br>
            <input type="submit" value="CreateLecture" name="action" />
            <input type="reset" value="Reset" />
        </form>
        <%
            String message = (String) session.getAttribute("MESSAGE1");
            if (message != null) {
        %>
        <h4 style="color: red"><%= message%></h4>
        <%
            }
        %>
    </body>
</html>
