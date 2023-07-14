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
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h2>Input your file to add Student to Class</h2>
        <a>Note in file .xlsx: +column: StudentID,CourseID,StartDate,SubjectID, Note</a><br>
        <a>Note in file .xlsx: +column: int,int,StartDate,int, Note</a><br>
        <a>+ birthday in .xlsx: text("","yyyy-mm-dd")</a><br>
        <a>ex: text("4/10/2020","yyyy-mm-dd")</a><br><br>
        <form action="UploadFileAddStudentToClassController" method="POST" enctype="multipart/form-data">
            <input type="file" name="file" accept=".xlsx"><br>
            <br>
            <input type="submit" value="Upload">
            <input type="hidden" name="courseID" value="<%=session.getAttribute("COURSE_ID") %>" />
            <input type="hidden" name="subID" value="<%=session.getAttribute("SUBJECT_ID") %>" />
        </form>
        <%
            String message = (String) request.getAttribute("SUCCESS");
            if (message != null) {
        %>
        <a style="color: green"><%=message%></a>
        <%
            }
            String message1 = (String) request.getAttribute("FAIL");
            if (message1 != null) {
        %>
        <a style="color: red"><%=message1%></a>
        <%
            }
        %>
    </body>
</html>
