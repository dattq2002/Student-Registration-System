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

            String search = loginUser.getEmail();
            if (search == null) {
                search = "";
            }
        %>

        <div>
            <ul>
                <li><a href="manager.jsp">Home Page</a></li>
                </li>
                <li>
                    <form action="ManagerController" method="POST">
                        <input type="submit" name="action" value="Lecturer's Profile"/>
                        <input type="hidden" name="searchE" value="<%=search%>"/>
                    </form>
                </li>
                <li> Lecturer Management
                    <ul>
                        <li> 
                            <form action="ManagerController" method="POST">
                                <input type="submit" name="action" value="Lecturer's Class"/>
                                <input type="hidden" name="searchE" value="<%=search%>"/>
                            </form>
                        </li>
                        <li>
                            <form action="ManagerController" method="POST">
                                <input type="submit" name="action" value="Lecturer's topic"/>
                            </form>
                        </li>
                        <li>
                            <form action="ManagerController" method="POST">
                                <input type="submit" name="action" value="Lecturer's Application"/>
                                <input type="hidden" name="searchE" value="<%=search%>"/>
                            </form>
                        </li>
                        <li>
                            <form action="ManagerController" method="POST">
                                <input type="submit" name="action" value="Lecturer's Presentation"/>
                                <input type="hidden" name="searchE" value="<%=search%>"/>
                            </form>
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
