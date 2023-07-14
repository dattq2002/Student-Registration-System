<%@page import="system.main.DTO.ClassInformation"%>
<%@page import="system.main.DTO.TopicAssign"%>
<%@page import="java.util.List"%>
<%@page import="system.main.DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Registration System</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <%
            ClassInformation infcl = (ClassInformation) request.getAttribute("INFOR_CLASS");
            if (infcl != null) {
        %>
        <h3>List Topic - Subject: <%=infcl.getSubjectCode() + infcl.getSubjectID()%> </h3>
        <h3>Lecture Name: <%=request.getAttribute("LECTURE_NAME")%></h3>
        <%
            }
        %>
            <%
                String message = (String) request.getAttribute("MESSGAE_LIST_TOPIC");
                if (message != null) {
            %>
        <h4><%=message%></h4>
        <%
            }
        %>
        <%
            List<TopicAssign> list = (List<TopicAssign>) request.getAttribute("LIST_TOPIC");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <form action="AdminController">
            <input type="submit" value="Add Topic" />
            <input type="hidden" value="AddTopic" name="action"/>
            <input type="hidden" name="courseID" value="<%=session.getAttribute("COURSE_ID")%>" />
            <input type="hidden" name="subID" value="<%=session.getAttribute("SUBJECT_ID")%>" />
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Topic Code</th>
                    <th>Topic Name</th>
                    <th>Short Description</th>
                    <th>Full Description</th>
                    <th>Lecturer Name</th>
                    <th>Start Date</th>
                    <th>Modify Date</th>
                    <th>Delete Topic</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (TopicAssign item : list) {
                %>
                <tr>
                    <td><%=count++%>
                        <input type="hidden" name="topicAss" value="<%=item.getTopicAssignID()%>" />
                    </td>
                    <td><%=item.getTopicCode() + item.getTopicID()%></td>
                    <td><%=item.getTopicName()%></td>
                    <td><%=item.getShortDescription()%></td>
                    <td><%=item.getFullDescription()%></td>
                    <td><%=item.getLecName()%></td>
                    <td><%=item.getStartDate()%></td>
                    <td><%=item.getModifyDate()%></td>
                    <td>
                        <a href="AdminController?action=DeleteTopic&topicID=<%=item.getTopicID()%>&subID=<%=session.getAttribute("SUBJECT_ID")%>&sesID=<%=session.getAttribute("SEMESTER_ID")%>">
                            Delete
                        </a>
                    </td>
                </tr>
                <%
                    }
                %>  
            </tbody>
        </table>

        <%
        } else {
        %>
        <form action="AdminController">
            <input type="submit" value="Add Topic" />
            <input type="hidden" value="AddTopic" name="action"/>
            <input type="hidden" name="courseID" value="<%=session.getAttribute("COURSE_ID")%>" />
            <input type="hidden" name="subID" value="<%=session.getAttribute("SUBJECT_ID")%>" />
        </form>
        <%
                }
            }
        %>

        <%
            String message1 = (String) request.getAttribute("MESSAGE_DELETE_TOPIC");
            if (message1 != null) {
        %>
        <h4><%=message1%></h4>
        <%
            }
        %>
    </body>
</html>
