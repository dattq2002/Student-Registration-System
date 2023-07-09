<%@page import="DTO.GroupProject"%>
<%@page import="DTO.Group"%>
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
                if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                    response.sendRedirect("login.jsp");
                    return;
                }
            %>
            <h4>Amount Member in Group: <%=(request.getAttribute("AMOUNT") == null)
                ? "0" : request.getAttribute("AMOUNT")%></h4>
            <%
                List<GroupProject> list1 = (List<GroupProject>) request.getAttribute("LIST_PROJECT");
                if (list1 != null) {
                    if (!list1.isEmpty()) {
                        for (GroupProject dto : list1) {
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
            List<Group> list = (List<Group>) request.getAttribute("LIST_MEMBER");
            if (list != null) {
                if (!list.isEmpty()) {
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
                    for (Group item : list) {
                %>
                <tr>
                    <td><%=item.getStudentCode() + "-" + item.getStudentID()%></td>
                    <td><%=item.getStudentName()%></td>
                    <td><%=item.getGroupName()%></td>
                    <td><%=item.getStartDate()%></td>
                    <td><%=item.getMajor()%></td>
                    <td><%=item.getIsLeader()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>        
        <%
            }
        } else {
        %>
        <h1>You don't have a group please Join</h1>
        <button>
            <a href="JoinGroupController?courseID=<%=request.getAttribute("COURSEID")%>&subID=<%=request.getAttribute("SUBID")%>">
                Join Group</a>
        </button>
        <%
            }
        %>

    </body>
</html>
