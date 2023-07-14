<%@page import="system.main.DTO.StudentProfile"%>
<%@page import="java.util.List"%>
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
        
        <%
            List<StudentProfile> list = (List<StudentProfile>) request.getAttribute("LIST_STUDENT_CLASS");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <h3>Lecture Name: <%=request.getAttribute("LECTURE_NAME")%></h3>
        <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Student Code</th>
                        <th>Name</th>
                        <th>Birthday</th>
                        <th>Phone Number</th>
                        <th>Gender</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        for (StudentProfile dto : list) {
                    %>
                <form action="MainController" method="POST">

                    <tr>
                        <td><%=count++%></td>
                        <td>
                            <%= dto.getCode() + dto.getID()%>
                            <input type="hidden" name="id" value="<%= dto.getID()%>" />
                            <input type="hidden" name="code" value="<%= dto.getCode()%>" />
                        </td>
                        <td>                     
                            <input type="text" name="Name" value="<%= dto.getName()%>" />
                        </td>
                        <td><%= dto.getBirthday()%></td>
                        <td><%= dto.getPhoneNumber()%></td>
                        <td><%= dto.getGender()%></td>
                        <td><%= dto.getAddress()%></td>
                        <td><%= dto.getCity()%></td>
                        <td><%= dto.getEmail()%></td>
                    </tr>
                </form>

                <%
                    }
                %>

                </tbody>
            </table>
        <%
                }
            }
        %>
        
        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message != null) {
        %>
        <a><%=message%></a>
        <%
            }
        %>
    </body>
</html>
