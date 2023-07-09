<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Student Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h2>Add Student</h2>
        <form action="MainController" method="POST">
            Student No Roll:
            <input type="text" name="id" value="" placeholder="ex:SE16-0453" required=""/><br>
            Student Name: 
            <input type="text" name="name" value="" required=""/><br>
            Email:
            <input type="text" name="email" value="" required=""/><br>
            Major:
            <input type="text" name="major" value="" required=""/><br>
            <input type="submit" value="Add" />
            <input type="hidden" value="AddStudent" name="action" />
        </form><br>
        <h2>Input your file to add Student</h2>
        <a>Note in file .xlsx: + column(ID, code, name, birthday,
            phoneNumber,gender,Address,city,major,Email)</a><br>
            <a>column: (int, String, String, String, String,String,String, String, String,String )</a>
            <a>+ birthday in .xlsx: text("","yyyy-mm-dd")</a><br>
            <a>+ PhoneNumber in .xlsx: text("","0-000-000-0000")</a><br>
            <a>ex: text("4/10/2020","yyyy-mm-dd")</a><br><br>
            <form action="UploadFileStudentController" method="POST" enctype="multipart/form-data">
                <input type="file" name="fileStu" accept=".xlsx"><br>
                <br>
                <input type="submit" value="Upload" />
                <input type="hidden" value="UploadStudent" name="action">
            </form>
            <%
                String message = (String) request.getAttribute("MESSAGE_UPLOAD");
                if (message != null) {
            %>
            <a style="color: red"><%=message%></a>
            <%
                }
                String message1 = (String) request.getAttribute("SUCCESS");
                if (message1 != null) {
            %>
            <a style="color: green"><%=message1%></a>
            <%
                }
            %>
    </body>
</html>
