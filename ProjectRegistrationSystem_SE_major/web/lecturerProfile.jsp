<%@page import="system.main.DTO.LectureProfile"%>
<%@page import="java.util.List"%>
<%@page import="system.main.DTO.UserAccountDTO"%>
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
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <a href="manager.jsp">Home Page</a>
        
        <%
            List<LectureProfile> list = (List<LectureProfile>) request.getAttribute("PROFILE");
            if (list != null) {
                if (!list.isEmpty()) {
        %>

        <%
            for (LectureProfile item : list) {
        %>
        <form action="LecturerController">
            <br><img src="<%= item.getImage()%>" alt="Image"><br>

            <div>
                Lecturer ID:<%= item.getCode() + '-' + item.getID() %>
            </div>

            <div>
                Name: <input type="text" name="name" value="<%= item.getName()%>"/>
            </div>

            <div>
                Gender:
                <input type="radio" name="gender" value="male" <% if ("male".equals(item.getGender())) { %> checked <% } %>> Male
                <input type="radio" name="gender" value="female" <% if ("female".equals(item.getGender())) { %> checked <% }%>> Female
            </div>

            <div>
                Birthday: <input type="date" name="birthday" value="<%= item.getBirthday()%>"/>
            </div>

            <div>
                Address: <input type="text" name="address" value="<%= item.getAddress()%>"/>
            </div>

            <div>
                City: <input type="text" name="city" value="<%= item.getCity()%>"/>
            </div>

            <div>
                Phone Number: <input type="text" name="phoneNumber" value="<%= item.getPhoneNumber()%>"/>
            </div>

            <div>
                Email: <%= item.getEmail()%>
            </div>

            <div>
                <input type="submit" value="Update" />
                <input type="hidden" name="action" value="UpdateProfile"/>
                <input type="hidden" name="email" value="<%= item.getEmail()%>"/>        
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
<div><%=message%></div>
<%
    }
%>
</body>
</html>
