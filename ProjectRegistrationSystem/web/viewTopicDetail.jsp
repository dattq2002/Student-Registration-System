<%@page import="DTO.Topic"%>
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
        <h3>Topic Detail</h3>
        <%
            Topic top = (Topic) request.getAttribute("TOPIC_DETAIL");
            if (top != null) {
        %>
        Topic No Roll: <%=top.getTopicCode() + "-" + top.getTopicID()%><br><br>
        Topic Name: <%=top.getName()%><br><br>
        Short Description: <textarea rows="5" cols="45"><%=top.getShortDescription()%></textarea><br>
        Full Description: <textarea rows="5" cols="45"><%=top.getFullDescription()%></textarea><br>
        <%
            }
        %>
    </body>
</html>
