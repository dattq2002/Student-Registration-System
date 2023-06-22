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
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h3><%=(String) session.getAttribute("LEC_NAME")%></h3>


        <%
            List<StudentProfile> list = (List<StudentProfile>) request.getAttribute("LIST_DETAIL");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
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
                    <th>Major</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (StudentProfile dto : list) {
                %>
                <tr>
                    <td><%=count++%></td>
                    <td><%=dto.getCode() + dto.getID()%></td>
                    <td><%=dto.getName()%></td>
                    <td><%=dto.getBirthday()%></td>
                    <td><%=dto.getPhoneNumber()%></td>
                    <td><%=dto.getGender()%></td>
                    <td><%=dto.getAddress()%></td>
                    <td><%=dto.getCity()%></td>
                    <td><%=dto.getMajor()%></td>
                    <td><%=dto.getEmail()%></td>
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
