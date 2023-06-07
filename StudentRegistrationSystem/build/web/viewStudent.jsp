<%@page import="DTO.StudentProfile"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Student Management Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("searchStudent");
            if (search == null) {
                search = "";
            }
        %>
        <a href="admin.jsp">Home</a> > <a href="viewStudent.jsp"> View Student</a>
        <!--thanh sidebar
            ...
        -->

        <!--thanh header
            ...
        -->
        <!--body-->
        <form action="MainController">
            Search: <input type="text" name="searchStudent" value="" placeholder="search by name"/>
            <input type="submit" value="SearchStudent" name="action" />
        </form>
        <h3>View Student</h3>

        <%
            List<StudentProfile> list = (List<StudentProfile>) session.getAttribute("LIST_STUDENT");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Birthday</th>
                    <th>Email</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (StudentProfile item : list) {
                %>
            <form action="MainController">
                <tr>
                    <td><%=count++%></td>
                    <td><%= item.getID()%></td>
                    <td>
                        <input type="text" name="code" value="<%= item.getCode()%>" />
                    </td>
                    <td>                     
                        <input type="text" name="Name" value="<%= item.getName()%>" />
                    </td>
                    <td><%= item.getBirthday()%></td>
                    <td><%= item.getEmail()%></td>
                    <td>
                        <button>
                            <a href="MainController?ID=<%= item.getID()%>&action=DeleteStudent&searchStudent=<%=search%> ">Delete</a>
                        </button>
                    </td>
                    <td>
                        <input type="submit" value="UpdateStudent" name="action" />
                        <input type="hidden" name="ID" value="<%= item.getID()%>" />
                        <input type="hidden" name ="searchStudent" value="<%= search%>" />
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
        String message = (String) session.getAttribute("ERROR_DU");
        if (message != null) {
    %>
    <h4 style="color: red"><%= message%></h4>
    <%
        }
    %>
    <button><a href="addStudent.jsp">Create</a></button>
</body>
</html>
