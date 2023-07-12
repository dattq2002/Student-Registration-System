<%-- 
    Document   : detailGroupMNG
    Created on : Jul 8, 2023, 2:13:14 PM
    Author     : Nam An
--%>

<%@page import="DTO.GroupProject"%>
<%@page import="DTO.Group"%>
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
        <a href="MainController?action=LecturerGroup">Group</a>
        <br>

        <%
            List<GroupProject> projectGroup = (List<GroupProject>) request.getAttribute("PROJECT_GROUP");
            if (projectGroup != null) {
                if (!projectGroup.isEmpty()) {
        %>

        <%
            for (GroupProject dto : projectGroup) {
        %>
        <form action="MainController">
            <div>
                Topic ID: <input type="text" name="topicID" value="<%= dto.getTopicID()%>" />
                <input type="submit" value="Update" />
                <input type="hidden" name="action" value="UpdateTopicProject"/>     
                <input type="hidden" name="projectID" value="<%= dto.getProjectID()%>"/>
                <input type="hidden" name="groupID" value="<%= request.getAttribute("GROUP_ID")%>"/>
                <input type="hidden" name="courseID" value="<%= request.getAttribute("COURSE_ID")%>"/>
                <input type="hidden" name="subjectID" value="<%= request.getAttribute("SUBJECT_ID")%>"/>
                <input type="hidden" name="groupName" value="<%= request.getAttribute("GROUP_NAME")%>"/>
            </div>

            <div>
                Topic Name: <%= dto.getTopicName()%> 
            </div>

            <div>
                Context: <%= dto.getContext()%> 
            </div>

            <div>
                Actors: <%= dto.getActor()%>
            </div>

            <div>
                Function Requirements: <%= dto.getFunction()%>
            </div>

            <div>
                Note: <%= dto.getNote()%> 
            </div>
        </form>
        <%
            }
        %>

        <%
                }
            }
        %>

        <%
            List<Group> detailGroup = (List<Group>) request.getAttribute("DETAIL_GROUP");
            if (detailGroup != null) {
                if (!detailGroup.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Student Code</th>
                    <th>Student Name</th>
                    <th>Group Name</th>
                    <th>isLeader</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Group dto : detailGroup) {
                %>
            <form action="MainController">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getStudentCode() + dto.getStudentID()%> </td>
                    <td> <%= dto.getStudentName()%> </td>
                    <td> <%= dto.getGroupName()%> </td>
                    <td>          
                        <select name="isLeader" id="isLeader">
                            <option value="LD" <%= (dto.getIsLeader().trim().equals("LD")) ? "selected" : ""%>>LD</option>
                            <option value="MB" <%= (dto.getIsLeader().trim().equals("MB")) ? "selected" : ""%>>MB</option>
                        </select>

                    </td>
                    <td>
                        <input type="submit" value="Update" />
                        <input type="hidden" name="action" value="UpdateMember"/>
                        <input type="hidden" name="memberID" value="<%= dto.getMemberID()%>"/>
                        <input type="hidden" name="groupID" value="<%= request.getAttribute("GROUP_ID")%>"/>
                        <input type="hidden" name="courseID" value="<%= request.getAttribute("COURSE_ID")%>"/>
                        <input type="hidden" name="subjectID" value="<%= request.getAttribute("SUBJECT_ID")%>"/>
                        <input type="hidden" name="groupName" value="<%= request.getAttribute("GROUP_NAME")%>"/>
                    </td>
                    <td>
                        <button>
                            <a href="MainController?action=DeleteMember&memberID=<%= dto.getMemberID()%>&groupID=<%= request.getAttribute("GROUP_ID")%>&courseID=<%= request.getAttribute("COURSE_ID")%>&subjectID=<%= request.getAttribute("SUBJECT_ID")%>&groupName=<%= request.getAttribute("GROUP_NAME")%>">Delete</a>
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
        }
    %>

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
