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
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h1>Semester</h1>
        <a href="LecturerController?action=LecturerProfile">Profile</a>
        <a style="color: gray" href="MainController?action=Spring2022&sesID=11111">Spring 2022</a>
        <a href="MainController?action=Summer2022&sesID=11112">Summer 2022</a>
        <a href="MainController?action=Spring2023&sesID=11113">Spring 2023</a>
        <a href="MainController?action=Summer2023&sesID=11114">Summer 2023</a>
        <a href="LecturerController?action=SourceTopic">Source Topic</a>
        <a href="LecturerController?action=LecturerPresentation">Presentation</a>
        <a href="LecturerController?action=ProcessingApplication">Application</a>
    </body>
</html>
