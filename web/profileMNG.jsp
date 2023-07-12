<%-- 
    Document   : ProfileMNG
    Created on : Jun 29, 2023, 9:13:58 PM
    Author     : Nam An
--%>

<%@page import="DTO.Profile"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lecturer's Profile Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <a href="manager.jsp">Home Page</a>

        <%
            List<Profile> profile = (List<Profile>) request.getAttribute("PROFILE");
            if (profile != null) {
                if (!profile.isEmpty()) {
        %>

        <%
            for (Profile dto : profile) {
        %>
        <form action="MainController">
            <br><img src="<%= dto.getImage()%>" alt="Image"><br>

            <div>
                Lecturer ID:<%= dto.getLecturerCode() + '-' + dto.getLecturerID()%>
            </div>

            <div>
                Name: <input type="text" name="name" value="<%= dto.getName()%>"/>
            </div>

            <div>
                Gender:
                <input type="radio" name="gender" value="male" <% if ("male".equals(dto.getGender())) { %> checked <% } %>> Male
                <input type="radio" name="gender" value="female" <% if ("female".equals(dto.getGender())) { %> checked <% }%>> Female
            </div>

            <div>
                Birthday: <input type="date" name="birthday" value="<%= dto.getBirthday()%>"/>
            </div>

            <div>
                Address: <input type="text" name="address" value="<%= dto.getAddress()%>"/>
            </div>

            <div>
                City: <input type="text" name="city" value="<%= dto.getCity()%>"/>
            </div>

            <div>
                Phone Number: <input type="text" name="phoneNumber" value="<%= dto.getPhoneNumber()%>"/>
            </div>

            <div>
                Email: <%= dto.getEmail()%>
            </div>

            <div>
                <input type="submit" name="action" value="Update Profile"/>
                <input type="hidden" name="email" value="<%= dto.getEmail()%>"/>        
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
