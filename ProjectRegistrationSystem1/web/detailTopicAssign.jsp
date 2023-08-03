<%-- 
    Document   : detailTopicAssign
    Created on : Aug 3, 2023, 9:26:30 AM
    Author     : Nam An
--%>

<%@page import="system.main.DTO.TopicAssign"%>
<%@page import="java.util.List"%>
<%@page import="system.main.DTO.UserAccountDTO"%>
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
        
        <a href="ListTopic?sesID=<%=session.getAttribute("SEMESTER_ID")%>&subID=<%=session.getAttribute("SUBJECT_ID")%>">
            Back
        </a>
        
        <%
            List<TopicAssign> list = (List<TopicAssign>) request.getAttribute("LIST_TOPIC");
            if (list != null) {
                if (!list.isEmpty()) {
        %>

                <%
                    for (TopicAssign item : list) {
                %>
                <div>
                    Topic Code:
                    <%=item.getTopicCode() + item.getTopicID()%>
                </div>
                <div>
                    Topic Name:
                    <%=item.getTopicName()%>
                </div>
                <div>
                    Context:
                    <%=item.getContext() %>
                </div>
                <div>
                    Actor:
                    <%=item.getActor() %>
                </div>
                <div>
                    Function:
                    <%=item.getFunction() %>
                </div>
                <div>
                    Lecturer Name:
                    <%=item.getLecName()%>
                </div>
                <div>
                    Start Date:
                    <%=item.getStartDate() %>
                </div>
                <div>
                    Modify Date:
                    <%=item.getModifyDate() %>
                </div>                 
                <%
                    }
                %>  

        <%
                }
            }
        %>
    </body>
</html>
