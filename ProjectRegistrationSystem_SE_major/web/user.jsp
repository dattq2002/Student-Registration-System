<%@page import="system.main.DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Registration System</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h1>Semester</h1>
        <a style="color: gray" href="MainController?action=Spring2022&sesID=11111">Spring 2022</a>
        <a href="MainController?action=Summer2022&sesID=11112">Summer 2022</a>
        <a href="MainController?action=Spring2023&sesID=11113">Spring 2023</a>
        <a style="color: green" href="MainController?action=Summer2023&sesID=11114">Summer 2023</a>
        <a href="ProfileStudentController">View Profile</a>
        <a href="sendApplication.jsp">Send Application</a>
        <a href="ListForm">View Application</a>
        <a href="ListPresentationCapstone">View Presentation</a>
        <a href="LogoutController">Logout</a>
    </body>
</html>
