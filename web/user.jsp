<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <link rel="stylesheet" href="CSS/style.css">
    </head>
    <body>      
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }           
        %>
        
        <div>
            <ul>
                <li> Home
                <li><a href="manager.jsp">Home Page</a></li>
                </li>

                <li> Lecturer Management
                    <ul>
                        <li> Class
                            <ul>
                                <li>View Student</li>
                                <li>View Group</li>
                            </ul>
                        </li>
                        <li>
                            <form action="MainController">
                                <input type="submit" name="action" value="Student's Topic"/>
                            </form>
                        </li>
                        <li>Application</li>
                        <li>Presentation</li>
                    </ul>                   
                </li>

                <li> More
                    <ul>
                        <li>Profile</li>
                        <li>Notification</li>
                        <li>Setting</li>
                        <li>
                            <form action="MainController">
                                <input type="submit" value="Logout" name="action" />
                            </form>
                        </li>
                    </ul>  
                </li>
            </ul>
        </div>
    </body>
</html>
