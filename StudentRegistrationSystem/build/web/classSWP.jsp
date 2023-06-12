<%@page import="DTO.Subject"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Class SWP Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <form action="MainController">
            Search: <input type="text" name="searchClass" value="" />
            <input type="submit" value="SearchClass" name="action" />
        </form>
        <%
            List<Subject> list = (List<Subject>) request.getAttribute("SHOWLIST_SUBJECT");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <ul>
            <%
                for (Subject item : list) {
            %>
            <li><a href="LinkSubject?id=<%=item.getID()%>"><%=item.getCode()+ item.getID() %></a></li>
            <%
                }
            %>
        </ul>
        <%
                }
            }
        %>
        
    </body>
</html>
