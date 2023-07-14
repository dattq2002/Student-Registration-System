<%@page import="system.main.DTO.UserAccountDTO"%>
<%@page import="java.util.List"%>
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
        <a href="addAccount.jsp">Add Account From File</a>
        <form action="SearchAccountController">
            <input type="text" name="SearchAccount" value="" />
            <input type="submit" value="SearchAccount"/>
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
            <form action="UpdateAccountController" method="POST">
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
                        <input type="submit" value="Update"/>
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
</body>
</html>
