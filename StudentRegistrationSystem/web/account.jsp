<%@page import="DTO.UserAccountDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"ADMIN".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.jsp");
                return;
            }

            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>

        <a href="admin.jsp">Home Page</a> 
        <span> > </span>
        <a href="ListAccountController">List of Account</a>
        <form action="MainController">
            Search<input type="text" name="search" value="<%= search%>"/>
            <input type="submit" name="action" value="SearchAccount"/>
        </form>
        <%
            List<UserAccountDTO> listAccount = (List<UserAccountDTO>) request.getAttribute("LIST_ACCOUNT");
            if (listAccount != null) {
                if (!listAccount.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Email</th>
                    <th>Full Name</th>
                    <th>Role ID</th>
                    <th>Password</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (UserAccountDTO dto : listAccount) {
                %>
            <form action="MainController">
                <tr>
                    <td>
                        <%= count++%>
                    </td>
                    <td>
                        <%= dto.getEmail()%>
                    </td>
                    <td>
                        <input type="text" name="fullName" value="<%= dto.getFullName()%>"/>                
                    </td>
                    <td>
                        <%= dto.getRoleID()%>
                    </td>
                    <td>
                        <input type="text" name="password" value="<%= dto.getPassword()%>"/>
                    </td>  
                    <td>
                        <a href="MainController?email=<%= dto.getEmail()%>&action=DeleteAccount&SearchAccount=<%= search%>" >Delete</a>
                    </td>
                    <td>
                        <input type="submit" name="action" value="UpdateAccount"/>
                        <input type="hidden" name="email" value="<%= dto.getEmail()%>"/>
                        <input type="hidden" name="SearchAccount" value="<%= search%>"/>             
                    </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>

    <button><a href="createAccount.jsp">Create Account</a></button>


    <%
        }
        String error_message = (String) request.getAttribute("ERROR_MESSAGE");
        if (error_message == null) {
            error_message = "";
        }
    %>

    <%
        }
    %>


    <%
        List<UserAccountDTO> list = (List<UserAccountDTO>) request.getAttribute("SEARCH_ACCOUNT");
        if (list != null) {
            if (!list.isEmpty()) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>Email</th>
                <th>Full Name</th>
                <th>Role ID</th>
                <th>Password</th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 1;
                for (UserAccountDTO dto : list) {
            %>
        <form action="MainController">
            <tr>
                <td><%= count++%></td>
                <td><%= dto.getEmail()%></td>
                <td><%= dto.getFullName()%></td>
                <td><%= dto.getRoleID()%></td>
                <td><%= dto.getPassword()%></td>  
            </tr>
        </form>

        <%
            }
        %>
    </tbody>
</table>

<%
    }
    String error_message = (String) request.getAttribute("ERROR_MESSAGE");
    if (error_message == null) {
        error_message = "";
    }
%>
<h1><%= error_message%></h1>
<%
    }
%>

</body>
</html>
