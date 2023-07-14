<%@page import="java.util.List"%>
<%@page import="system.main.DTO.StudentProfile"%>
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
        
        <div class="head_phu" id="main">
            <div class="header2">
                <h3>View Student</h3>
                <div class="input-wrapper">
                    <button class="icon">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" height="25px" width="25px">
                        <path stroke-linejoin="round" stroke-linecap="round" stroke-width="1.5" stroke="#fff"
                              d="M11.5 21C16.7467 21 21 16.7467 21 11.5C21 6.25329 16.7467 2 11.5 2C6.25329 2 2 6.25329 2 11.5C2 16.7467 6.25329 21 11.5 21Z">
                        </path>
                        <path stroke-linejoin="round" stroke-linecap="round" stroke-width="1.5" stroke="#fff" d="M22 22L20 20">
                        </path>
                        </svg>
                    </button>
                    <input placeholder="search.." class="input" name="text" type="text">
                </div>
            </div>
            <a href="ListStudentProfile">List Student</a>
            <form action="AdminController">
                Search: <input type="text" name="searchStudent" value="" placeholder="search by name"/>
                <input type="submit" value="SearchStudent" name="action" />
            </form>
            <%
                String message = (String) request.getAttribute("ERROR_DU");
                if (message != null) {
            %>
            <h4 style="color: red"><%= message%></h4>
            <%
                }
            %>
            <%
                String message1 = (String) request.getAttribute("MESSAGE_STUDENT");
                if (message1 != null) {
            %>
            <h3 style="color: green"><%=message1%></h3>
            <%
                }
            %>
            <%
                List<StudentProfile> listStudent = (List<StudentProfile>) request.getAttribute("SHOWLIST_STUDENT");
                if (listStudent != null) {
                    if (!listStudent.isEmpty()) {
            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Student Code</th>
                        <th>Name</th>
                        <th>Birthday</th>
                        <th>Phone Number</th>
                        <th>Gender</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>Major</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        for (StudentProfile dto : listStudent) {
                    %>
                <form action="AdminController" method="POST">

                    <tr>
                        <td><%=count++%></td>
                        <td>
                            <%= dto.getCode() + dto.getID()%>
                            <input type="hidden" name="id" value="<%= dto.getID()%>" />
                            <input type="hidden" name="code" value="<%= dto.getCode()%>" />
                        </td>
                        <td>                     
                            <input type="text" name="Name" value="<%= dto.getName()%>" />
                        </td>
                        <td><%= dto.getBirthday()%></td>
                        <td><%= dto.getPhoneNumber()%></td>
                        <td><%= dto.getGender()%></td>
                        <td><%= dto.getAddress()%></td>
                        <td><%= dto.getCity()%></td>
                        <td><%= dto.getMajor()%></td>
                        <td>
                            <input type="submit" value="Update" />
                            <input type="hidden" value="UpdateStudent" name="action"/>
                            <input type="hidden" name="ID" value="<%= dto.getID()%>" />
                        </td>
                    </tr>
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

            <div class="button">
                <a href="addStudent.jsp" class="btn">Add</a>
            </div>
        </div>
    </body>
</html>
