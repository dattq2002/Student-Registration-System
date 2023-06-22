<%@page import="DTO.ClassInformation"%>
<%@page import="java.util.List"%>
<%@page import="DTO.Subject"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page import="DTO.Class"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Class Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("searchSubject");
            if (search == null) {
                search = "";
            }
        %>
        <!--sidebar
            ....
        -->
        <!--body-->
        <form action="MainController" method="POST">
            Search: <input type="text" name="searchClass" value="" placeholder="Search Subject Code"/>
            <input type="submit" value="Search Class" name="action" />
        </form>
        <%
            List<ClassInformation> list = (List<ClassInformation>) request.getAttribute("LIST_CLASS");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Subject Code</th>
                    <th>Lecture Name</th>
                    <th>Class Course</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Status</th>
                    <th>Detail</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (ClassInformation dto : list) {
                %>
                <tr>
                    <td><%=dto.getID()%></td>
                    <td><%=dto.getSubjectCode() + dto.getSubjectID()%></td>
                    <td>
                        <%=dto.getLecName()%>
                    </td>
                    <td><%=dto.getCourseName() + dto.getCourseID()%>
                    </td>
                    <td><%=dto.getStartDate()%></td>
                    <td><%=dto.getEndDate()%></td>
                    <td><%=dto.isStatus()%></td>
                    <td>
                        <a href="MainController?action=ViewDetail&courseid=<%=dto.getCourseID()%>&lecname=<%=dto.getLecName()%>">View_Detail</a>
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
