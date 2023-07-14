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
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h4>Group Name: <%=session.getAttribute("GROUP_NAME")%></h4>

        <%
            List<GroupProject> projectGroup = (List<GroupProject>) request.getAttribute("PROJECT_GROUP");
            if (projectGroup != null) {
                if (!projectGroup.isEmpty()) {
        %>

        <%
            for (GroupProject dto : projectGroup) {
        %>
        <form action="LecturerController">
            <div>
                Topic Code: <input type="text" name="topic" value="<%= dto.getTopicCode() + "-" + dto.getTopicID()%>" />   
                <input type="submit" value="Update" />
                <input type="hidden" name="action" value="UpdateProjectTopic"/>     
                <input type="hidden" name="projectID" value="<%= dto.getProjectID()%>"/>
                <input type="hidden" name="grID" value="<%= session.getAttribute("GROUP_ID")%>"/>
                <input type="hidden" name="grName" value="<%= session.getAttribute("GROUP_NAME")%>"/>
                <input type="hidden" name="subID" value="<%= session.getAttribute("SUBJECT_ID")%>"/>
                <input type="hidden" name="sesID" value="<%=session.getAttribute("SEMESTER_ID")%>"/>
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
        </form>



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
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Group item : list) {
                %>
            <form action="LecturerController">
                <tr>
                    <td>
                        <%=count++%>              
                    </td>
                    <td><%=item.getStudentCode() + item.getStudentID()%></td>
                    <td><%=item.getStudentName()%></td>
                    <td>
                        <select name="isLeader" id="isLeader">
                            <option value="LD" <%= (item.getIsLeader().trim().equals("LD")) ? "selected" : ""%>>LD</option>
                            <option value="MB" <%= (item.getIsLeader().trim().equals("MB")) ? "selected" : ""%>>MB</option>
                        </select>
                    </td>
                    <td><%=item.getStartDate()%></td>
                    <td>
                        <input type="submit" value="Update" />
                        <input type="hidden" name="action" value="UpdateGroupMember"/>
                        <input type="hidden" name="memID" value="<%=item.getMemberID()%>" />
                        <input type="hidden" name="grID" value="<%= session.getAttribute("GROUP_ID")%>"/>
                        <input type="hidden" name="grName" value="<%= session.getAttribute("GROUP_NAME")%>"/>
                        <input type="hidden" name="subID" value="<%= session.getAttribute("SUBJECT_ID")%>"/>
                    </td>
                    <td>
                        <button>
                            <a href="LecturerController?action=DeleteGroupMember&memID=<%=item.getMemberID()%>&grID=<%= session.getAttribute("GROUP_ID")%>&grName=<%= session.getAttribute("GROUP_NAME")%>&subID=<%= session.getAttribute("SUBJECT_ID")%>&countM=<%= request.getAttribute("COUNT_MEMBER")%>">
                                Delete
                            </a>
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
