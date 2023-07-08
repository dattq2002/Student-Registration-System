<%@page import="DTO.TopicAssign"%>
<%@page import="java.util.List"%>
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
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <%
            List<TopicAssign> list = (List<TopicAssign>) request.getAttribute("LIST_SUBJECT_TOPIC");
            if (list != null) {
                if (!list.isEmpty()) {
                    for (TopicAssign item : list) {
        %>
        <a href="ListTopic?subID=<%=item.getSubjectID()%>">
            <%=item.getSubjectCode() + item.getSubjectID()%>
        </a>
        <%
                    }
                }
            }
        %>
    </body>
</html>
