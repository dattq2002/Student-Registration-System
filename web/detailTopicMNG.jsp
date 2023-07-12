<%-- 
    Document   : detailTopicMNG
    Created on : Jun 11, 2023, 10:08:53 AM
    Author     : Nam An
--%>

<%@page import="java.util.List"%>
<%@page import="DTO.Topic"%>
<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Topic Page</title>
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
        <a href="MainController?action=LecturerListSubjectTopic">Subject</a>
        <a href="MainController?action=LecturerTopic">Create</a>

        <%
            List<Topic> searchTopic = (List<Topic>) request.getAttribute("DETAIL_TOPIC");
            if (searchTopic != null) {
                if (!searchTopic.isEmpty()) {
        %>

        <%
            for (Topic dto : searchTopic) {
        %>
        <form action="MainController">

            <div>
                Topic ID: <%= dto.getTopicID()%>
            </div>

            <div>       
                Topic Code: <input type="text" name="topicCode" value="<%= dto.getTopicCode()%>" />
            </div>

            <div>
                Topic Name: <textarea cols="20" rows="5" name="topicName"><%= dto.getTopicName()%></textarea>
            </div>

            <div>
                Short Description: <textarea cols="40" rows="5" name="shortDescription" ><%= dto.getShortDescription()%></textarea>
            </div>

            <div>
                Full Description: <textarea cols="40" rows="5" name="fullDescription" ><%= dto.getFullDescription()%></textarea>
            </div>


            <input type="submit" name="action" value="UpdateTopic"/>
            <input type="hidden" name="topicID" value="<%= dto.getTopicID()%>"/>
        </form>

        <form action="MainController">
            <input type="submit" name="action" value="DeleteTopic"/>
            <input type="hidden" name="topicID" value="<%= dto.getTopicID()%>"/>    

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

<%
    String message = (String) request.getAttribute("MESSAGE");
    if (message != null) {
%>
<div><%=message%></div>
<%
    }
%>
</body>
</html>
