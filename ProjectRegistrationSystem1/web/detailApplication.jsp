<%-- 
    Document   : detailApplication
    Created on : Jul 13, 2023, 7:01:38 PM
    Author     : Nam An
--%>

<%@page import="system.main.DTO.Application"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="system.main.DTO.UserAccountDTO"%>
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
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <%
            List<Application> listApplication = (List<Application>) request.getAttribute("DETAIL_APPLICATION");
            if (listApplication != null) {
                if (!listApplication.isEmpty()) {
        %>

        <%
            for (Application dto : listApplication) {
        %>
        <form action="LecturerController">
            <div>
                Application ID: <%= dto.getID()%> 
                <input type="hidden" name="formID" value="<%= dto.getID()%>" />
            </div>
            <div>Type: <%= dto.getType()%> </div>
            <div>Subject Code: <%= dto.getSubCode() + "-" + dto.getSubID()%> </div>
            <div>Course Name: <%= dto.getCourseName() + "-" + dto.getCourseID()%> </div>
            <div>Group Name: <%= dto.getGrName()%> </div>
            <div>Topic Code: <%= dto.getTopicCode() + "-" + dto.getTopicID()%> </div>
            <div>Student Code: <%= dto.getStuCode() + "-" + dto.getStuID()%> </div>     
            <div>Reason: <%= dto.getReason()%> </div>
            <div>
                Lecturer's Note: <textarea cols="40" rows="5" name="note" ><%= dto.getLecNote()%></textarea>
            </div>
            <div>Create Date: <%= dto.getCreateDate()%> </div>
            <div>Process Date: <%= dto.getProcessDate()%> </div>
            <div>
                Status:
                <input type="radio" name="status" value="Processed" <% if ("approve".equals(dto.getStatus())) { %> checked <% } %>> Approve
                <input type="radio" name="status" value="Not Processed" <% if ("refuse".equals(dto.getStatus())) { %> checked <% } %>> Refuse

            </div>

            <input type="submit" value="Submit" />
            <input type="hidden" name="action" value="ApproveApplication" />
        </form>

        <%
            }
        %>

        <%
            }
        %>

        <%
            }
        %> 

        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message != null) {
        %>
        <div><%=message%></div>
        <%
            }
        %>
    </body>
</body>
</html>
