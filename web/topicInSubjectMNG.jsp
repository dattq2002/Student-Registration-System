<%-- 
    Document   : topicInSubjectMNG
    Created on : Jul 10, 2023, 8:27:40 PM
    Author     : Nam An
--%>

<%@page import="DTO.TopicAssign"%>
<%@page import="DTO.Topic"%>
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

            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>   

        <a href="manager.jsp">Home Page</a>
        <a href="MainController?action=LecturerListSubjectTopic">Subject</a>
        <a href="MainController?action=LecturerTopic">Create</a>

        <%
            List<TopicAssign> listTopic = (List<TopicAssign>) request.getAttribute("LIST_TOPIC");
            if (listTopic != null) {
                if (!listTopic.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Topic ID</th>
                    <th>Topic Code</th>
                    <th>Topic Name</th>
                    <th>Short Description</th>
                    <th>Full Description</th>
                    <th>Lecturer Name</th>
                    <th>Status</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (TopicAssign dto : listTopic) {
                %>
            <form action="MainController">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getTopicID()%> </td>
                    <td> <%= dto.getTopicCode()%> </td>
                    <td> <%= dto.getTopicName()%> </td> 
                    <td> <%= dto.getShortDescription()%> </td> 
                    <td> <%= dto.getFullDescription()%> </td> 
                    <td> <%= dto.getLecturerName()%> </td> 
                    <td> <%= dto.getStatus() %> </td> 
                    <td>
                        <button>
                            <a href="MainController?action=DeleteTopicInSubject&topicID=<%= dto.getTopicID()%>&subjectID=<%= dto.getSubjectID()%>&lecturerID=<%= dto.getLecturerID()%>">Delete</a>
                        </button>
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
