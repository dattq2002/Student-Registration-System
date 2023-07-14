<%-- 
    Document   : sourceTopic
    Created on : Jul 13, 2023, 4:24:04 PM
    Author     : Nam An
--%>

<%@page import="system.main.DTO.TopicAssign"%>
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

        <a href="manager.jsp">Home Page</a>
        
        <%
            List<TopicAssign> list = (List<TopicAssign>) request.getAttribute("LIST_TOPIC");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Topic Code</th>
                    <th>Topic Name</th>
                    <th>Short Description</th>
                    <th>Full Description</th>
                    <th>Lecturer Name</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (TopicAssign item : list) {
                %>
            <form action="LecturerController">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= item.getTopicCode() + "-" + item.getTopicID()%> </td>
                    <td> <textarea cols="20" rows="4" name="topicName"><%= item.getTopicName()%></textarea> </td>
                    <td> <textarea cols="60" rows="4" name="shortDescription" ><%= item.getShortDescription()%></textarea> </td> 
                    <td> <textarea cols="60" rows="4" name="fullDescription" ><%= item.getFullDescription()%></textarea> </td>
                    <td> <%=item.getLecName()%> </td>
                    <td>
                        <input type="submit" value="Update" />
                        <input type="hidden" name="action" value="UpdateTopic"/>
                        <input type="hidden" name="topicID" value="<%= item.getTopicID()%>"/>
                        <input type="hidden" name="lecID" value="<%= item.getLecID()%>"/>
                    </td>
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

    <a href="createTopic.jsp">Create Topic</a>
    
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
