<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <div>
            <ul>

                <li><a href="manager.jsp">Home Page</a></li>

                <li>                    
                    <a href="ManagerController?action=LecturerProfile">Profile</a>
                </li>
                <li> Lecturer Management
                    <ul>
                        <li> 
                            <a href="ManagerController?action=LecturerClass">Class</a>
                        </li>
                        <li> 
                            <a href="ManagerController?action=LecturerGroup">Group</a>
                        </li>
                        <li>
                            <a href="ManagerController?action=LecturerTopic">Topic</a>
                        </li>
                        <li>
                            <a href="ManagerController?action=LecturerApplication">Application</a>
                        </li>
                        <li>
                            <a href="ManagerController?action=LecturerPresentation">Presentation</a>
                        </li>
                    </ul>                   
                </li>
                <li>
                    <form action="ManagerController">
                        <input type="submit" value="Logout" name="action" />
                    </form>
                </li>

            </ul>
        </div>
    </body>
</html>
