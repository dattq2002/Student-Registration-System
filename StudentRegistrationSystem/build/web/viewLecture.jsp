<%@page import="java.util.List"%>
<%@page import="DTO.LectureProfile"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lecture Management Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("searchLecture");
            if (search == null) {
                search = "";
            }
        %>
        <a href="admin.jsp"> home</a> > <a href="viewLecture.jsp"> View Lecture</a>
        <!--thanh sidebar
            ...
        -->

        <!--thanh header
            ...
        -->
        <!--body-->
        <form action="MainController">
            Search: <input type="text" name="searchLecture" value="<%=search%>" placeholder="search by name"/>
            <input type="submit" value="SearchLecture" name="action" />
        </form>
        <h3>View Lecture</h3>
        <%
            List<LectureProfile> list = (List<LectureProfile>) session.getAttribute("LIST_LECTURE");
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
                    for (LectureProfile item : list) {
                %>
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
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
                            <a href="MainController?ID=<%= item.getID()%>&action=DeleteLecture&searchLecture=<%=search%> ">Delete</a>
                        </button>
                    </td>
                    <td>
                        <input type="submit" value="UpdateLecture" name="action" />
                        <input type="hidden" name="ID" value="<%= item.getID()%>" />
                        <input type="hidden" name ="searchLecture" value="<%= search%>" />
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
        String message = (String) session.getAttribute("MESSAGE");
        if (message != null) {
    %>
    <h4 style="color: red"><%= message%></h4>
    <%
        }
    %>
    <button><a href="addLecture.jsp">Create</a></button>
</body>
</html>
