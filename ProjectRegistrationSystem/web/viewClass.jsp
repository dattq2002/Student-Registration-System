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
        <a href="createCourse.jsp">Create Class</a>
        <a href="addStudentToClass.jsp">Add Student Class</a>
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
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (ClassInformation dto : list) {
                %>
            <form action="MainController" method="POST">
                <tr>
                    <td>
                        <%=dto.getID()%>
                    </td>
                    <td>
                        <input type="text" name="subint" value="<%=dto.getSubjectCode() + "-" + dto.getSubjectID()%>" />
                    </td>
                    <td>
                        <input type="text" name="lecName" value="<%=dto.getLecName()%>" />
                    </td>
                    <td>
                        <%=dto.getCourseName() + "-" + dto.getCourseCode()%>
                        <input type="hidden" name="Courseid" 
                               value="<%=dto.getCourseName() + "-" + dto.getCourseID()%>" />
                    </td>
                    <td>
                        <input type="date" name="stdate" value="<%=dto.getStartDate()%>" />
                    </td>
                    <td>
                        <input type="date" name="enddate" value="<%=dto.getEndDate()%>" />
                    </td>
                    <td>
                        <select name="status">
                            <option value="true" <%=(dto.isStatus() == true) ? "selected" : ""%>>Active</option>
                            <option value="false" <%=(dto.isStatus() == false) ? "selected" : ""%>>Deactive</option>
                        </select>
                    </td>
                    <%
                        if (dto.isStatus()) {
                    %>
                    <td>
                        <a href="MainController?action=ViewDetail&courseid=<%=dto.getCourseID()%>&lecname=<%=dto.getLecName()%>">View_Detail</a>
                    </td>
                    <%
                    } else {
                    %>
                    <td>
                        <a style="color: red">Disable</a>
                    </td>
                    <%
                        }
                    %>
                    <td>
                        <input type="submit" value="Update" />
                        <input type="hidden" value="Update Class" name="action"/>
                        <input type="hidden" name="id" value="<%=dto.getID()%>" />
                    </td>
                    <td>
                        <button>
                            <a href="MainController?action=DeleteClass&id=<%=dto.getID()%>">Delete</a>
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
    <h3><%=message%></h3>
    <%
        }
    %>
</body>
</html>
