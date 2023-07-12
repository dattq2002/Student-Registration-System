<%-- 
    Document   : processedApplicationMNG.jsp
    Created on : Jul 5, 2023, 7:16:23 PM
    Author     : Nam An
--%>

<%@page import="DTO.Application"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <a href="manager.jsp">Home Page</a>
        <a href="MainController?action=LecturerApplication">Application</a>
        <a href="MainController?action=ProcessedApplication">Approved Application</a>


        <%            
            List<Application> listApplication = (List<Application>) request.getAttribute("LIST_APPLICATION");
            if (listApplication != null) {
                if (!listApplication.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Application ID</th>
                    <th>Type</th>
                    <th>Subject ID</th>
                    <th>Course ID</th>
                    <th>Group ID</th>
                    <th>Topic Code</th>
                    <th>Student ID</th>        
                    <th>Student's Reason</th>
                    <th>Lecturer's Note</th>
                    <th>Create Date</th>
                    <th>Process Date</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Application dto : listApplication) {
                %>
            <form action="MainController">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getID()%> </td>
                    <td> <%= dto.getType()%> </td>
                    <td> <%= dto.getSubCode() + "-" + dto.getSubID()%> </td>
                    <td> <%= dto.getCourseName() + "-" + dto.getCourseID()%> </td>
                    <td> <%= dto.getGrName()%> </td>
                    <td> <%= dto.getTopicCode() + "-" + dto.getTopicID() %> </td>
                    <td> <%= dto.getStuCode() + "-" + dto.getStuID()%> </td>  
                    <td> <%= dto.getReason() %> </td>
                    <td> <%= dto.getLecNote() %> </td>
                    <td> <%= dto.getCreateDate() %> </td>
                    <td> <%= dto.getProcessDate() %> </td>
                    <td> <%= dto.getStatus() %> </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>
    <%
        }
    %>

    <%
        }
    %> 
    </body>
</html>
