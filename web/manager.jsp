<%-- 
    Document   : manager
    Created on : Jun 5, 2023, 11:45:20 AM
    Author     : meryc
--%>

<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <div>
            <ul>

                <li><a href="manager.jsp">Home Page</a></li>

                <li>                    
                    <a href="MainController?action=LecturerProfile">Profile</a>
                </li>
                <li> Lecturer Management
                    <ul>
                        <li> 
                            <a href="MainController?action=LecturerClass">Class</a>
                        </li>
                        <li> 
                            <a href="MainController?action=LecturerClassGroup">Group</a>
                        </li>
                        <li>
                            <a href="MainController?action=LecturerListSubjectTopic">Topic</a>
                        </li>
                        <li>
                            <a href="MainController?action=LecturerApplication">Application</a>
                        </li>
                        <li>
                            <a href="MainController?action=LecturerPresentation">Presentation</a>
                        </li>
                    </ul>                   
                </li>
                <li>
                    <form action="MainController">
                        <input type="submit" value="Logout" name="action" />
                    </form>
                </li>

            </ul>
        </div>
    </body>
</html>
