<%@page import="DTO.StudentProfile"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Student Management Page</title>
        <!-- Boxicons CSS -->
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
        <link rel="stylesheet" href="CSS/viewtable_style.css" />
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("searchStudent");
            if (search == null) {
                search = "";
            }
        %>
        <!-- navbar -->
        <header class="navbar">
            <div class="logo_item">
                <i class="bx bx-menu" id="sidebarOpen"></i>
                <img src="images/logo_fpt.png" alt=""></i>FPT University
            </div>

            <!-- <div class="search_bar">
                <input type="text" placeholder="Search" />
              </div> -->

            <div class="navbar_content">
                <i class="bi bi-grid"></i>
                <i class='bx bx-sun' id="darkLight"></i>
                <i class='bx bx-bell'></i>
                <img src="images/profile.jpg" alt="" class="profile" />
            </div>
        </header>

        <!-- sidebar -->
        <nav class="sidebar" id="mySidenav">
            <div class="menu_content">
                <ul class="menu_items">
                    <div class="menu_title menu_editor"></div>
                    <!-- duplicate these li tag if you want to add or remove navlink only -->
                    <!-- Start -->
                    <li class="item">
                        <a href="admin.jsp" class="nav_link">
                            <span class="navlink_icon">
                                <i class="bx bx-home-alt"></i>
                            </span>
                            <span class="navlink">Home Page</span>
                        </a>
                    </li>
                    <!-- End -->
                </ul>
                <ul class="menu_items">
                    <div class="menu_title menu_dahsboard"></div>
                    <!-- duplicate or remove this li tag if you want to add or remove navlink with submenu -->
                    <!-- start -->
                    <li class="item">
                        <div href="#" class="nav_link submenu_item">
                            <span class="navlink_icon">
                                <i class="bx bx-grid-alt"></i>
                            </span>
                            <span class="navlink">Account</span>
                        </div>
                    </li>
                    <!-- end -->

                    <!-- Start -->
                    <li class="item">
                        <a href="#" class="nav_link">
                            <span class="navlink_icon">
                                <i class="bx bx-grid-alt"></i>
                            </span>
                            <span class="navlink">Class</span>
                        </a>
                    </li>
                    <!-- End -->
                    <!-- Start -->
                    <li class="item">
                        <a href="viewStudent.jsp" class="nav_link">
                            <span class="navlink_icon">
                                <i class="bx bx-grid-alt"></i>
                            </span>
                            <span class="navlink">Student</span>
                        </a>
                    </li>
                    <!-- End -->
                    <!-- Start -->
                    <li class="item">
                        <a href="viewLecture.jsp" class="nav_link">
                            <span class="navlink_icon">
                                <i class="bx bx-grid-alt"></i>
                            </span>
                            <span class="navlink">Lecture</span>
                        </a>
                    </li>
                    <!-- End -->
                </ul>
                <ul class="menu_items">
                    <div class="menu_title menu_setting"></div>
                    <li class="item">
                        <a href="#" class="nav_link">
                            <span class="navlink_icon">
                                <i class="bx bx-flag"></i>
                            </span>
                            <span class="navlink">Profile</span>
                        </a>
                    </li>
                    <li class="item">
                        <a href="#" class="nav_link">
                            <span class="navlink_icon">
                                <!-- <i class="bx bx-medal"></i> -->
                                <i class='bx bx-bell'></i>
                            </span>
                            <span class="navlink">Notification</span>
                        </a>
                    </li>
                    <li class="item">
                        <a href="#" class="nav_link">
                            <span class="navlink_icon">
                                <i class="bx bx-cog"></i>
                            </span>
                            <span class="navlink">Setting</span>
                        </a>
                    </li>
                    <li class="item">
                        <a href="MainController?action=Logout" class="nav_link">
                            <span class="navlink_icon">
                                <i class="bx bx-log-out"></i>
                            </span>
                            <span class="navlink">Log Out</span>
                        </a>
                    </li>
                </ul>

                <!-- Sidebar Open / Close -->
                <!-- <div class="bottom_content">
                  <div class="bottom expand_sidebar">
                    <span> Expand</span>
                    <i class='bx bx-log-in'></i>
                  </div>
                  <div class="bottom collapse_sidebar">
                    <span> Collapse</span>
                    <i class='bx bx-log-out'></i>
                  </div>
                </div> -->
            </div>
        </nav>
        <!--body-->
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
            <form action="MainController">
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
                if(message1 != null){
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
                <form action="MainController" method="POST">

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
                            <input type="hidden" name ="searchStudent" value="<%= search%>" />
                        </td>
                    </tr>
                </form>

                <%
                    }
                %>

                </tbody>
                <%
                        }
                    }
                %>
            </table>
            <div class="button">
                <a href="addStudent.jsp" class="btn">Add</a>
            </div>
        </div>
    </body>
</html>
