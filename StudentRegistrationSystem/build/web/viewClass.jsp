<%@page import="java.util.List"%>
<%@page import="DTO.Subject"%>
<%@page import="DTO.UserAccountDTO"%>
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
            if (loginUser == null) {
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
