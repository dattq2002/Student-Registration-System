<%@page import="DTO.StudentProfile"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h3></h3>
        <%
            List<StudentProfile> list = (List<StudentProfile>) request.getAttribute("CLASS_DETAIL");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Student No Roll</th>
                    <th>Student Name</th>
                    <th>Birthday</th>
                    <th>Phone Number</th>
                    <th>Gender</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>Major</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (StudentProfile item : list) {
                %>
                <tr>
                    <td><%=item.getCode() + "-" + item.getID()%></td>
                    <td><%=item.getName()%></td>
                    <td><%=item.getBirthday()%></td>
                    <td><%=item.getPhoneNumber()%></td>
                    <td><%=item.getGender()%></td>
                    <td><%=item.getAddress()%></td>
                    <td><%=item.getCity()%></td>
                    <td><%=item.getMajor()%></td>
                </tr>
                <%
                    }
                %>

            </tbody>
        </table>

        <%
                }
            }
        %>
    </body>
</html>
