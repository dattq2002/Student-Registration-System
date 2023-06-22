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
            String search = request.getParameter("SearchAccount");
            if (search == null) {
                search = "";
            }
        %>
        <a href="ListAccountController">List of Account</a>
        <form action="MainController">
            <input type="text" name="SearchAccount" value="" />
            <input type="submit" value="SearchAccount" name="action" />
        </form>
        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message != null) {
        %>
        <h3 style="color: red"><%=message%></h3>
        <%
            }
            List<UserAccountDTO> listAccount = (List<UserAccountDTO>) request.getAttribute("LIST_ACCOUNT");
            if (listAccount != null) {
                if (!listAccount.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Account Code</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Full Name</th>
                    <th>Status</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (UserAccountDTO dto : listAccount) {
                %>
            <form action="MainController" method="POST">
                <tr>
                    <td>
                        <%= count++%>
                    </td>
                    <td>
                        <%= dto.getCode() + dto.getID()%>
                        <input type="hidden" name="code" value="<%=dto.getCode()%>" />
                        <input type="hidden" name="id" value="<%=dto.getID()%>" />
                    </td>
                    <td>
                        <%= dto.getEmail()%>
                        <input type="hidden" name="email" value="<%= dto.getEmail()%>" />
                    </td>
                    <td>
                        <select name="role">
                            <option value="ADMIN" <%=(dto.getRoleID().equals("ADMIN")) ? "selected" : ""%>>Admin</option>
                            <option value="MNG" <%=(dto.getRoleID().equals("MNG")) ? "selected" : ""%>>Manager</option>
                            <option value="USER" <%=(dto.getRoleID().equals("USER")) ? "selected" : ""%>>User</option>
                        </select>
                    </td>
                    <td>
                        <input type="text" name="name" value="<%= dto.getFullName()%>"/>
                    </td>
                    <td>
                        <select name="status">
                            <option value="Active" <%=(dto.getStatus().equals("Active")) ? "selected" : ""%>>Active</option>
                            <option value="Deactive" <%=(dto.getStatus().equals("Deactive")) ? "selected" : ""%>>Deactive</option>
                        </select>
                    </td>
                    <td>
                        <input type="submit" value="UpdateAccount" name="action" />
                        <input type="hidden" name="id" value="<%=dto.getID()%>" />
                        <input type="hidden" name="SearchAccount" value="<%=search%>" />
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
        List<UserAccountDTO> listSearch = (List<UserAccountDTO>) request.getAttribute("SEARCH_ACCOUNT");
        if (listSearch != null) {
            if (!listSearch.isEmpty()) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>Account Code</th>
                <th>Email</th>
                <th>Role</th>
                <th>Name</th>
                <th>Status</th>
                <th>Update</th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 1;
                for (UserAccountDTO dto2 : listSearch) {
            %>
        <form action="MainController" method="POST">
            <tr>
                <td>
                    <%= count++%>
                </td>
                <td>
                    <%= dto2.getCode() + dto2.getID()%>
                    <input type="hidden" name="code" value="<%=dto2.getCode()%>" />
                    <input type="hidden" name="id" value="<%=dto2.getID()%>" />
                </td>
                <td>
                    <%= dto2.getEmail()%>
                </td>
                <td>
                    <select name="role">
                        <option value="ADMIN" <%=(dto2.getRoleID().equals("ADMIN")) ? "selected" : ""%>>Admin</option>
                        <option value="MNG" <%=(dto2.getRoleID().equals("MNG")) ? "selected" : ""%>>Manager</option>
                        <option value="USER" <%=(dto2.getRoleID().equals("USER")) ? "selected" : ""%>>User</option>
                    </select>
                </td>
                <td>
                    <input type="text" name="name" value="<%= dto2.getFullName()%>"/>
                </td>
                <td>
                    <select name="status">
                        <option value="Active" <%=(dto2.getStatus().equals("Active")) ? "selected" : ""%>>Active</option>
                        <option value="Deactive" <%=(dto2.getStatus().equals("Deactive")) ? "selected" : ""%>>Deactive</option>
                    </select>
                </td>
                <td>
                    <input type="submit" value="UpdateAccount" name="action" />
                    <input type="hidden" name="id" value="<%=dto2.getID()%>" />
                    <input type="hidden" name="SearchAccount" value="<%=search%>" />
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

</body>
</html>
