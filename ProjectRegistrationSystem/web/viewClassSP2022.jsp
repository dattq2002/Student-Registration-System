<%@page import="DTO.ClassInformation"%>
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
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <a href="ListStudentInformation?idSemester=<%=request.getAttribute("11111")%>">
            List Student
        </a>
        <a href="ListLectureInformation?idSemester=<%=request.getAttribute("11111")%>">
            List Lecture
        </a>
        <a href="ClassController?idSemester=<%=request.getAttribute("11111")%>">
            List Class
        </a>
        <%
            List<ClassInformation> list = (List<ClassInformation>) request.getAttribute("LIST_CLASS_SPRING2022");
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
                    <td>
                        <%=dto.getID()%>
                    </td>
                    <td>
                        <%=dto.getSubjectCode() + "-" + dto.getSubjectID()%>
                    </td>
                    <td>
                        <%=dto.getLecName()%>
                    </td>
                    <td>
                        <%=dto.getCourseName() + "-" + dto.getCourseCode()%>
                    </td>
                    <td>
                        <%=dto.getStartDate()%>
                    </td>
                    <td>
                        <%=dto.getEndDate()%>
                    </td>
                    <td>
                        <%=dto.isStatus()%>
                    </td>
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
