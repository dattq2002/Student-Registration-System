<%@page import="system.main.DTO.StudentProfile"%>
<%@page import="system.main.DTO.UserAccountDTO"%>
<%@page import="java.util.List"%>
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
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h3>Profile, <%=loginUser.getEmail()%></h3>
        <%
            List<StudentProfile> list = (List<StudentProfile>) request.getAttribute("PROFILE");
            if (list != null) {
                if (!list.isEmpty()) {
                    for (StudentProfile item : list) {
        %>
        <form action="MainController" method="POST">
            Student No Roll: <%=item.getCode() + "-" + item.getID()%><br>
            Student Name: <input type="text" name="name" value="<%=item.getName()%>" /><br>
            Student birthday: <input type="date" name="birthday" value="<%=item.getBirthday()%>" /><br>
            Phone Number: <input type="text" name="phone" value="<%=item.getPhoneNumber()%>" /><br>
            Gender: <select name="gender">
                <option value="male" <%=(item.getGender().equals("male")) ? "selected" : ""%>>Male</option>
                <option value="female" <%=(item.getGender().equals("female")) ? "selected" : ""%>>FeMale</option>
            </select><br>
            Address: <input type="text" name="address" value="<%=item.getAddress()%>" /><br>
            City: <input type="text" name="City" value="<%=item.getCity()%>" /><br>
            Major: <%=item.getMajor()%><br>
            Email: <%=item.getEmail()%> 
            <input type="hidden" name="email" value="<%=item.getEmail()%>" /><br>
            <input type="submit" value="Update" />
            <input type="hidden" name="action" value="Update Profile" />
        </form>
        <%
                    }
                }
            }
        %>
        <!----------------------->
        <%
            String message = (String) request.getAttribute("MESS_UPDATE");
            if (message != null) {
        %>
        <h3><%=message%></h3>
        <%
            }
        %>
    </body>
</html>
