<%@page import="DTO.ClassInformation"%>
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
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <a href="manager.jsp">Home Page</a>
        <a href="ManagerController?action=LecturerListSubjectTopic">Subject</a>
        <a href="ManagerController?action=LecturerTopic">Create</a>
        <br>

        <%
            List<ClassInformation> listClass = (List<ClassInformation>) request.getAttribute("LIST_SUBJECT");
            if (listClass != null) {
                if (!listClass.isEmpty()) {
                    for (ClassInformation dto : listClass) {
        %>
        <a href="TopicInSubjectMNGController?subjectID=<%=dto.getSubjectID()%>&subjectCode=<%=dto.getSubjectCode()%>"><%=dto.getSubjectCode() + dto.getSubjectID()%></a>
        <%
                }
            }
        %>

        <%
            }
        %> 
    </body>
</html>
