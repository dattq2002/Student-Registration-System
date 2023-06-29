<%@page import="DTO.StudentProfile"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Student Page</title>
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
            List<StudentProfile> list = (List<StudentProfile>) request.getAttribute("LIST_ST_SPRING2022");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Student Code</th>
                    <th>Name</th>
                    <th>Birthday</th>
                    <th>Phone Number</th>
                    <th>Gender</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>Major</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (StudentProfile dto : list) {
                %>
                <tr>
                    <td><%=count++%></td>
                        <td>
                            <%= dto.getCode() + dto.getID()%>
                        </td>
                        <td>                     
                            <%= dto.getName()%>
                        </td>
                        <td><%= dto.getBirthday()%></td>
                        <td><%= dto.getPhoneNumber()%></td>
                        <td><%= dto.getGender()%></td>
                        <td><%= dto.getAddress()%></td>
                        <td><%= dto.getCity()%></td>
                        <td><%= dto.getMajor()%></td>
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
