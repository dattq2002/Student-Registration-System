<%@page import="java.util.List"%>
<%@page import="DTO.LectureProfile"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lecture Management Page</title>
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
            String search = request.getParameter("searchLecture");
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
        <a href="admin.jsp"> home</a> > <a href="viewLecture.jsp"> View Lecture</a>
        <!--body-->
        <form action="MainController">
            Search: <input type="text" name="searchLecture" value="" placeholder="search by ID"/>
            <input type="submit" value="SearchLecture" name="action" />
        </form>
        <h3>View Lecture</h3>
        <%
                List<LectureProfile> listLecture = (List<LectureProfile>) request.getAttribute("SHOWLIST_LECTURE");
                if (listLecture != null) {
                    if (!listLecture.isEmpty()) {
            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>ID</th>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Birthday</th>
                        <th>Email</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        for (LectureProfile dto : listLecture) {
                    %>
                <form action="MainController">

                    <tr>
                        <td><%=count++%></td>
                        <td><%= dto.getID()%></td>
                        <td>
                            <input type="text" name="code" value="<%= dto.getCode()%>" />
                        </td>
                        <td>                     
                            <input type="text" name="Name" value="<%= dto.getName()%>" />
                        </td>
                        <td><%= dto.getBirthday()%></td>
                        <td><%= dto.getEmail()%></td>
                        <td>
                            <button>
                                <a href="MainController?ID=<%= dto.getID()%>&action=DeleteLecture&searchLecture=<%=search%> ">Delete</a>
                            </button>
                        </td>
                        <td>
                            <form action="UpdateLectureProfile">
                                <input type="submit" value="UpdateLecture" name="action"/>
                                <input type="hidden" name="ID" value="<%= dto.getID()%>" />
                                <input type="hidden" name ="searchLecture" value="<%= search%>" />
                            </form>
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
                <a href="addLecture.jsp" class="btn">Add</a>
            </div>
            
        <%
            List<LectureProfile> list = (List<LectureProfile>) request.getAttribute("LIST_LECTURE");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Birthday</th>
                    <th>Email</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (LectureProfile item : list) {
                %>
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td><%= item.getID()%></td>
                    <td>
                        <input type="text" name="code" value="<%= item.getCode()%>" />
                    </td>
                    <td>
                        <input type="text" name="Name" value="<%= item.getName()%>" />
                    </td>
                    <td><%= item.getBirthday()%></td>
                    <td><%= item.getEmail()%></td>
                    <td>
                        <button>
                            <a href="MainController?ID=<%= item.getID()%>&action=DeleteLecture&searchLecture=<%=search%> ">Delete</a>
                        </button>
                    </td>
                    <td>
                        <input type="submit" value="UpdateLecture" name="action" />
                        <input type="hidden" name="ID" value="<%= item.getID()%>" />
                        <input type="hidden" name ="searchLecture" value="<%= search%>" />
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
    <%
        String message = (String) request.getAttribute("MESSAGE");
        if (message != null) {
    %>
    <h4 style="color: red"><%= message%></h4>
    <%
        }
    %>
    </div>
</body>
</html>
