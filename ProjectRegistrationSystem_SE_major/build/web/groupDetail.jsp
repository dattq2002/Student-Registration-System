<%@page import="system.main.DTO.GroupProject"%>
<%@page import="system.main.DTO.Group"%>
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
        <h4>Group Name: <%=request.getAttribute("GROUP_NAME")%></h4>
        
        <%
            List<GroupProject> projectGroup = (List<GroupProject>) request.getAttribute("PROJECT_GROUP");
            if (projectGroup != null) {
                if (!projectGroup.isEmpty()) {
        %>

        <%
            for (GroupProject dto : projectGroup) {
        %>
            <div>
                Topic ID: <%= dto.getTopicID()%><input type="hidden" name="topicID" value="<%= dto.getTopicID()%>" />   
                <input type="hidden" name="projectID" value="<%= dto.getProjectID()%>"/>
            </div>

            <div>
                Topic Name: <%= dto.getTopicName()%> 
            </div>

            <div>
                Context: <%= dto.getContext()%> 
            </div>

            <div>
                Actors: <%= dto.getActors()%>
            </div>

            <div>
                Function Requirements: <%= dto.getFunction()%>
            </div>

            <div>
                Note: <%= dto.getNote()%> 
            </div>
        <%
            }
        %>

        <%
                }
            }
        %>
        
        <%
            List<Group> list = (List<Group>) request.getAttribute("GROUP_DETAIL");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Student code</th>
                    <th>Student Name</th>
                    <th>isLeader</th>
                    <th>Start Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Group item : list) {
                %>
                <tr>
                    <td>
                        <%=count++%>
                        <input type="hidden" name="memID" value="<%=item.getMemberID()%>" />
                    </td>
                    <td><%=item.getStudentCode() + item.getStudentID()%></td>
                    <td><%=item.getStudentName()%></td>
                    <td><%=item.getIsLeader()%></td>
                    <td><%=item.getStartDate()%></td>
                </tr>
                <%
                    }
                %>     
            </tbody>
        </table>

        <%
                }
            }
        %>
    </body>
</html>
