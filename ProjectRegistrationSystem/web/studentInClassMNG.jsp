<%@page import="DTO.UserAccountDTO"%>
<%@page import="DTO.StudentMNG"%>
<%@page import="java.util.List"%>
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
            
            String searchE = loginUser.getEmail();
        %>
        
        <a href="manager.jsp">Home Page</a>
        <span> > </span>
        <a href="MainController?action=Lecturer's Class&searchE=<%= searchE %>">Class</a>
        
        
        <%
            List<StudentMNG> listStudent = (List<StudentMNG>) request.getAttribute("LIST_STUDENT");
            if (listStudent != null) {
                if (!listStudent.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Student ID</th>
                    <th>Gender</th>
                    <th>Student Name</th>
                    <th>Birthday</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>Phone Number</th>
                    <th>Email</th>
                    <th>Group Name</th>
                    <th>Member</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (StudentMNG dto : listStudent) {
                %>
            <form action="MainController">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getStudentID() %> </td>
                    <td> <%= dto.getGender() %> </td>
                    <td> <%= dto.getStudentName() %> </td>
                    <td> <%= dto.getBirthday() %> </td>
                    <td> <%= dto.getAddress() %> </td>
                    <td> <%= dto.getCity() %> </td>
                    <td> <%= dto.getPhoneNumber() %> </td>
                    <td> <%= dto.getEmail() %> </td>
                    <td> <%= dto.getGroupName() %> </td>
                    <td> <%= dto.getMember() %> </td>
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
