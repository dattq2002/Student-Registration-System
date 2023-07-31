<%-- 
    Document   : detailTopic
    Created on : Jul 31, 2023, 5:30:09 PM
    Author     : Nam An
--%>

<%@page import="java.util.List"%>
<%@page import="system.main.DTO.TopicAssign"%>
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
        
        <%
            List<TopicAssign> list = (List<TopicAssign>) request.getAttribute("LIST_TOPIC");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
 
                <%
                    for (TopicAssign item : list) {
                %>
            <form action="LecturerController">
                <div>
                    Topic Code: <%= item.getTopicCode() + "-" + item.getTopicID()%>
                </div>
                
                <div>
                    Topic Name: <textarea cols="60" rows="4" name="topicName" ><%= item.getTopicName() %></textarea>
                </div>
                
                <div>
                    Context: <textarea cols="60" rows="4" name="context" ><%= item.getContext() %></textarea>
                </div>
                
                <div>
                    Actor: <textarea cols="60" rows="4" name="actor" ><%= item.getActor() %></textarea>
                </div>
                
                <div>
                    Function: <textarea cols="60" rows="4" name="function" ><%= item.getFunction() %></textarea>
                </div>
                
                <div>
                    Lecturer Name: <%=item.getLecName()%>
                </div>
                
                <div>
                        <input type="submit" value="Update" />
                        <input type="hidden" name="action" value="UpdateTopic"/>
                        <input type="hidden" name="topicID" value="<%= item.getTopicID()%>"/>
                        <input type="hidden" name="lecID" value="<%= item.getLecID()%>"/>
                </div>
            </form>
            <%
                }
            %>
        </tbody>
    </table>
    <%
        }
    %>

    <%
        }
    %> 
    
    <%
        String message = (String) request.getAttribute("MESSAGE");
        if (message != null) {
    %>
    <a><%=message%></a>
    <%
        }
    %>
    </body>
</html>
