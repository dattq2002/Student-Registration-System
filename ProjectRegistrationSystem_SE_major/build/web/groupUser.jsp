<%@page import="system.main.DTO.Group"%>
<%@page import="system.main.DTO.GroupProject"%>
<%@page import="java.util.List"%>
<%@page import="system.main.DTO.ClassInformation"%>
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
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <%
            ClassInformation infcl = (ClassInformation) request.getAttribute("INFOR_CLASS");
            if (infcl != null) {
        %>
        <h3>List Group - Subject: <%=infcl.getSubjectCode() + infcl.getSubjectID()%> </h3>
        <h3>Lecture Name: <%=request.getAttribute("LECTURE_NAME")%></h3>
        <%
            }
        %>
        <%
            List<GroupProject> list = (List<GroupProject>) request.getAttribute("LIST_PROJECT");
            if (list != null) {
                if (!list.isEmpty()) {
                    for (GroupProject dto : list) {
        %>
        <form action="UserController" method="POST">
            <div>
                Topic ID: <%= dto.getTopicID()%>
                <input type="hidden" name="topicID" value="<%= dto.getTopicID()%>" />
            </div>

            <div>
                Topic Name: <%= dto.getTopicName()%> 
            </div>

            <div>
                Context:  
                <textarea name="context" rows="5" cols="30"><%= dto.getContext()%></textarea>
            </div>

            <div>
                Actors: 
                <input type="text" name="actor" value="<%= dto.getActors()%>" />
            </div>

            <div>
                Function Requirements: 
                <textarea name="func" rows="5" cols="30"><%= dto.getFunction()%></textarea>
            </div>

            <div>
                Note: 
                <textarea name="noteproject" rows="5" cols="30"><%= dto.getNote()%></textarea>
            </div>
            <input type="submit" value="Update" />
            <input type="hidden" name="action" value="UpdateTopicProject"/>     
            <input type="hidden" name="prjID" value="<%=dto.getProjectID()%>" />
            <input type="hidden" name="CourseID" value="<%=request.getAttribute("COURSEID")%>" />
            <input type="hidden" name="SubID" value="<%=request.getAttribute("SUBID")%>" />
        </form>
        <%
            String messageUpdate = (String) request.getAttribute("MESSAGE_PRJ");
            if (messageUpdate != null) {
        %>
        <a style="color: green"><%=messageUpdate%></a>
        <%
            }
        %>
        <%
                    }
                }
            }
        %>

        <%
            List<Group> list1 = (List<Group>) request.getAttribute("LIST_MEMBER");
            if (list1 != null) {
                if (!list1.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Student No Roll</th>
                    <th>Student Name</th>
                    <th>Group Name</th>
                    <th>Start Date</th>
                    <th>Major</th>
                    <th>isLeader</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Group item : list1) {
                %>
                <tr>
                    <td><%=item.getStudentCode() + "-" + item.getStudentID()%></td>
                    <td><%=item.getStudentName()%></td>
                    <td>
                        <%=item.getGroupName()%>
                        <%session.setAttribute("GROUP_NAME", item.getGroupName()); %>
                    </td>
                    <td><%=item.getStartDate()%></td>
                    <td><%=item.getMajor()%></td>
                    <td><%=item.getIsLeader()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <form action="UserController">
            <input type="submit" value="Switch Group" name="action" />
            <input type="hidden" name="stuID" value="<%=session.getAttribute("STUDENT_ID")%>" />
            <input type="hidden" name="courseID" value="<%=session.getAttribute("COURSE_ID")%>" />
            <input type="hidden" name="subID" value="<%=session.getAttribute("SUBJECT_ID")%>" />
            <input type="hidden" name="grName" value="<%=session.getAttribute("GROUP_NAME")%>" />
        </form>
        <%
            }
        } else {
        %>
        <h1>You don't have a group please Join</h1>
        <button>
            <a href="JoinGroupController?courseID=<%=session.getAttribute("COURSE_ID")%>&subID=<%=session.getAttribute("SUBJECT_ID")%>">
                Join Group</a>
        </button>
        <%
            }
        %>
        <%
            String message = (String) request.getAttribute("MESSAGE");
            if(message != null){
                %>
                <a><%=message %></a>
        <%
            }
        %>
    </body>
</html>
