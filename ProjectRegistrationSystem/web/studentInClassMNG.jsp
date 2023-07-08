<%@page import="DTO.ClassInformation"%>
<%@page import="DTO.StudentProfile"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <a href="manager.jsp">Home Page</a>
        <a href="ManagerController?action=LecturerClass">Class</a>
        
        <h1>List student of Class: <%=request.getAttribute("COURSE_CODE") %></h1>
        
        <%
            List<StudentProfile> listStudent = (List<StudentProfile>) request.getAttribute("LIST_STUDENT");
            if (listStudent != null) {
                if (!listStudent.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Student Code</th>
                    <th>Student Name</th>
                    <th>Birthday</th>
                    <th>Phone Number</th>
                    <th>Gender</th>
                    <th>Address</th>
                    <th>City</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (StudentProfile dto : listStudent) {
                %>
            <form action="ManagerController">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getCode() + dto.getID() %> </td>
                    <td> <%= dto.getName()%> </td>
                    <td> <%= dto.getBirthday()%> </td>
                    <td> <%= dto.getPhoneNumber()%> </td>
                    <td> <%= dto.getGender()%> </td>
                    <td> <%= dto.getAddress()%> </td>
                    <td> <%= dto.getCity()%> </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>
    <%
        }
        String error_message = (String) request.getAttribute("ERROR_MESSAGE");
        if (error_message == null) {
            error_message = "";
        }
    %>

    <%
        }
    %> 
</body>
</html>
