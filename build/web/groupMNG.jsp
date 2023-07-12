<%-- 
    Document   : groupMNG
    Created on : Jul 7, 2023, 10:46:00 PM
    Author     : Nam An
--%>

<%@page import="java.util.List"%>
<%@page import="DTO.Group"%>
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
        <a href="MainController?action=LecturerClassGroup">Class</a>
        <a href="MainController?action=LecturerGroup">Group</a>
        <a href="MainController?action=LecturerFalseGroup">False Group</a>
        <br>

        <%
            List<Group> listGroup = (List<Group>) request.getAttribute("LIST_GROUP");
            if (listGroup != null) {
                if (!listGroup.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Group Name</th>
                    <th>Course Code</th>
                    <th>Subject Code</th>
                    <th>Detail</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Group dto : listGroup) {
                %>
            <form action="MainController">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getGroupName()%> </td>
                    <td> <%= dto.getCourseName() + "-" + dto.getCourseCode()%> </td>
                    <td> <%= dto.getSubjectCode() + "-" + dto.getSubjectID()%> </td> 
                    <td>
                        <input type="hidden" name="courseID" value="<%= dto.getCourseCode()%>"/>
                        <input type="hidden" name="subjectID" value="<%= dto.getSubjectID()%>"/>
                        <input type="hidden" name="groupName" value="<%= dto.getGroupName()%>"/>
                        <input type="hidden" name="groupID" value="<%= dto.getGroupID()%>"/>
                        <input type="submit" value="Detail" />
                        <input type="hidden" name="action" value="DetailGroup"/>
                    </td>
                    <td>
                        <button>
                            <a href="MainController?action=DeleteGroup&groupID=<%= dto.getGroupID()%>">Delete</a>
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
