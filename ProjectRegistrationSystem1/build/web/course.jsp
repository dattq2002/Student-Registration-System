<%@page import="system.main.DTO.Semester"%>
<%@page import="java.util.List"%>
<%@page import="system.main.DTO.UserAccountDTO"%>
<%@page import="system.main.DTO.Class"%>
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
        <%
            List<Semester> listSes = (List<Semester>) request.getAttribute("SEMESTER");
            if (listSes != null) {
                if (!listSes.isEmpty()) {
                    for (Semester item : listSes) {
        %>
        <h4><%=item.getSemesterName() + item.getSemesterYear()%></h4>
        <a>StartDate: <%=item.getStartDate()%></a>
        <a>EndDate: <%=item.getEndDate()%></a>
        <a>Status: <%=item.getStatus()%></a><br>
        <%
                    }
                }
            }
        %>
        <%
            List<Class> listCl = (List<Class>) request.getAttribute("CLASS");
            if (listCl != null) {
                if (!listCl.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Course Code</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Detail</th>
                    <!--<th>Delete</th> để sau--> 
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Class item : listCl) {
                %>
                <tr>
                    <td><%=count++%></td>
                    <td><%=item.getCourseName() + item.getCourseCode()%></td>
                    <td><%=item.getStartDate()%></td>
                    <td><%=item.getEndDate()%></td>
                    <td>
                        <a href="LecturerController?action=listSubject&courseID=<%=item.getCourseID()%>&courseName=<%=item.getCourseName()%>&courseCode=<%=item.getCourseCode()%>&sesID=<%=session.getAttribute("SEMESTER_ID") %>">
                            Detail
                        </a>
                    </td>
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
