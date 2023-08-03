<%-- 
    Document   : listStudentInGroup
    Created on : Aug 1, 2023, 3:22:59 PM
    Author     : Nam An
--%>

<%@page import="system.main.DTO.StudentProfile"%>
<%@page import="java.util.List"%>
<%@page import="system.main.DTO.UserAccountDTO"%>
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
        %>

        <a href="LecturerController?action=ViewGroupDetail&grID=<%=session.getAttribute("GROUP_ID")%>&grName=<%=session.getAttribute("GROUP_NAME")%>">
            Back
        </a>

        <%
            List<StudentProfile> list = (List<StudentProfile>) request.getAttribute("LIST_STUDENT_CLASS");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <h3>Lecture Name: <%=request.getAttribute("LECTURE_NAME")%></h3>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Student Code</th>
                    <th>Name</th>
                    <th>Add</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (StudentProfile dto : list) {
                %>
            <form action="LecturerController">

                <tr>
                    <td><%=count++%></td>
                    <td>
                        <%= dto.getCode() + dto.getID()%>
                    </td>
                    <td><%= dto.getName()%></td>
                    <td>
                        <input type="submit" value="Add" />
                        <input type="hidden" name="action" value="AddStudentInGroup"/>     
                        <input type="hidden" name="studentID" value="<%=dto.getID()%>"/>
                        <input type="hidden" name="grID" value="<%= session.getAttribute("GROUP_ID")%>"/>
                        <input type="hidden" name="subID" value="<%= session.getAttribute("SUBJECT_ID")%>"/>
                        <input type="hidden" name="courseID" value="<%=session.getAttribute("COURSE_ID")%>"/>
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
