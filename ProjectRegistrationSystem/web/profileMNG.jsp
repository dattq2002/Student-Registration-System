<%@page import="DTO.UserAccountDTO"%>
<%@page import="DTO.ProfileMNG"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lecturer's Profile Page</title>
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

        <h2>Lecturer's Profile</h2>

        <%
            List<ProfileMNG> profile = (List<ProfileMNG>) request.getAttribute("PROFILE");
            if (profile != null) {
                if (!profile.isEmpty()) {
        %>

        <%
            for (ProfileMNG dto : profile) {
        %>
        <form action="ManagerController" method="POST">
            <br><img src="<%= dto.getImage()%>" alt="Image"><br>


            <br>Lecturer ID: <%= dto.getLecturerID()%> <br>
            <br>Name: <input type="text" name="name" value="<%= dto.getName()%>"/> <br>
            <br>Gender:
            <input type="radio" name="gender" value="male" <% if ("male".equals(dto.getGender())) { %> checked <% } %>> Male
            <input type="radio" name="gender" value="female" <% if ("female".equals(dto.getGender())) { %> checked <% }%>> Female
            <br>
            <br>Birthday: <input type="date" name="birthday" value="<%= dto.getBirthday()%>"/> <br>
            <br>Address: <input type="text" name="address" value="<%= dto.getAddress()%>"/> <br>
            <br>City: <input type="text" name="city" value="<%= dto.getCity()%>"/> <br>
            <br>Phone Number: <input type="text" name="phoneNumber" value="<%= dto.getPhoneNumber()%>"/> <br>
            <br>Email: <%= dto.getEmail()%> <br> <br>

            <input type="submit" name="action" value="Update Profile"/>
            <input type="hidden" name="email" value="<%= dto.getEmail()%>"/>
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
<%
    String error_message = (String) request.getAttribute("ERROR_MESSAGE");
    if (error_message != null) {
%>
<p><%=error_message%></p>
<%
    }
%>


</body>
</html>
