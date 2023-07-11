<%@page import="DTO.TopicAssign"%>
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
        <a href="ManagerController?action=LecturerListSubjectTopic">Subject</a>
        <a href="ManagerController?action=LecturerTopic">Create</a>

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
            <form action="ManagerController">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getTopicID()%> </td>
                    <td> <%= dto.getTopicCode()%> </td>
                    <td> <%= dto.getName()%> </td> 
                    <td> <%= dto.getShortDescription()%> </td> 
                    <td> <%= dto.getFullDescription()%> </td> 
                    <td> <%= dto.getLecName()%> </td> 
                    <td> <%= dto.getStatus() %> </td> 
                    <td>
                        <button>
                            <a href="ManagerController?action=DeleteTopicInSubject&topicID=<%= dto.getTopicID()%>&subjectID=<%= dto.getSubjectID()%>&lecturerID=<%= dto.getLecturerID()%>">Delete</a>
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
