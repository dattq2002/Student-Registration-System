<%-- 
    Document   : manager
    Created on : Jun 5, 2023, 11:45:20 AM
    Author     : meryc
--%>

<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Page</title>
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
        <script src="https://kit.fontawesome.com/a817110ff3.js"></script>
        <link rel="stylesheet" href="CSS/homepage_style.css" />
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
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
                            <span class="navlink">Class</span>
                            <i class="bx bx-chevron-right arrow-left"></i>
                        </div>

                        <ul class="menu_items submenu">
                            <a href="viewStudent.jsp" class="nav_link sublink">View Student</a>
                            <a href="viewLecture.jsp" class="nav_link sublink">View Lecture</a>
                        </ul>
                    </li>
                    <!-- end -->

                    <!-- Start -->
                    <li class="item">
                        <a href="#" class="nav_link">
                            <span class="navlink_icon">
                                <i class="bx bx-grid-alt"></i>
                            </span>
                            <span class="navlink">Topic</span>
                        </a>
                    </li>
                    <!-- End -->
                    <!-- Start -->
                    <li class="item">
                        <a href="#" class="nav_link">
                            <span class="navlink_icon">
                                <i class="bx bx-grid-alt"></i>
                            </span>
                            <span class="navlink">Application</span>
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
                        <a href="#" class="nav_link">
                            <span class="navlink_icon">
                                <i class="bx bx-log-out"></i>
                            </span>
                            <span class="navlink">Logout</span>
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

        <!-- body starts  -->
        <div class="head_phu" id="main">
            <!-- slide img start -->
            <section class="wrapper">
                <i class="fa-solid fa-arrow-left button" id="prev"></i>
                <div class="image-container">
                    <div class="carousel">
                        <img src="images/FAP-01.png" alt="" />
                        <img src="images/FAP-01.png" alt="" />
                        <img src="images/FAP-01.png" alt="" />
                        <img src="images/FAP-01.png" alt="" />
                        <img src="images/FAP-01.png" alt="" />
                        <img src="images/FAP-01.png" alt="" />
                    </div>
                    <i class="fa-solid fa-arrow-right button" id="next"></i>
                </div>
            </section>
            <!-- slide img ends -->
            <!-- about section starts  -->
        </div>
        <div class="review_homepage">
            <section class="about" id="about">

                <h1 class="heading"> about us </h1>

                <div class="row">

                    <div class="video">
                        <video src="images/FPT giới thiệu.mp4" loop muted autoplay></video>
                    </div>

                    <div class="content">
                        <h3>Bringing tools to work for you in a comfortable and convenient way</h3>
                        <p>The website helps you manage students through each group and helps them allocate topics and time for presentations</p>
                        <a href="#contact" class="btn">read more</a>
                    </div>

                </div>

            </section>

            <!-- about section ends -->
            <!-- services section starts  -->

            <section class="services" id="services">

                <h1 class="heading"> our services </h1>

                <div class="box-container">

                    <div class="box">
                        <!-- <img src="images/icons8-class-96.png" alt=""> -->
                        <i class="fa-solid fa-chalkboard"></i>
                        <h3>Class</h3>
                        <a href="#contact" class="btn">Detail</a>
                    </div>

                    <div class="box">
                        <!-- <img src="images/icons8-group-64.png" alt=""> -->
                        <i class="fa-solid fa-users-rectangle"></i>
                        <h3>Manager Group</h3>
                        <a href="#contact" class="btn">Detail</a>
                    </div>

                    <div class="box">
                        <!-- <img src="images/icons8-presentations-100.png" alt=""> -->
                        <i class="fa-sharp fa-solid fa-users-viewfinder"></i>
                        <h3>Manager Meeting</h3>
                        <a href="#contact" class="btn">Detail</a>
                    </div>

                    <div class="box">
                        <!-- <img src="images/icons8-application-96.png" alt=""> -->
                        <i class="fa-solid fa-envelope"></i>
                        <h3>Manager Application</h3>
                        <a href="#contact" class="btn">Detail</a>
                    </div>

                    <div class="box">
                        <!-- <img src="images/icons8-list-64.png" alt=""> -->
                        <i class="fa-solid fa-rectangle-list"></i>
                        <h3>Manager Topic</h3>
                        <a href="#contact" class="btn">Detail</a>
                    </div>

                    <div class="box">
                        <!-- <img src="images/icons8-applicant-100.png" alt=""> -->
                        <i class="fa-solid fa-address-card"></i>
                        <h3>Profile</h3>
                        <a href="#contact" class="btn">Detail</a>
                    </div>
                </div>

            </section>

            <!-- services section ends -->

            <!-- contact section starts  -->

            <section class="contact" id="contact">

                <h1 class="heading"> contact us </h1>

                <div class="row">

                    <iframe class="map"
                            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.6099415305207!2d106.80730807592835!3d10.841132857995088!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752731176b07b1%3A0xb752b24b379bae5e!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBGUFQgVFAuIEhDTQ!5e0!3m2!1svi!2s!4v1686047571171!5m2!1svi!2s"
                            allowfullscreen="" loading="lazy"></iframe>

                    <div  class="info_contact">
                        <div class="info_contact_2">
                            <h3>office address</h3>
                            <p>Lô E2a-7, Đường D1, Khu Công nghệ cao, P.Long Thạnh Mỹ, Tp. Thủ Đức, TP.HCM</p>
                        </div>
                        <div class="info_contact_2">
                            <h3>phone number</h3>
                            <p>(028) 7300 5588</p>
                        </div>
                        <div class="info_contact_2">
                            <h3>email address</h3>
                            <p>daihoc.hcm@fpt.edu.vn</p>
                        </div>
                    </div>


                </div>

            </section>

            <!-- contact section ends -->

        </div>
        <!-- body ends  -->
        <!-- footer starts  -->
        <footer>
            <div class="bottom-details">
                <div class="bottom_text">
                    <span class="copyright_text">Copyright © 2023 <a href="#">FPT University</a></span>
                    <span class="policy_terms">
                        <a href="#">Privacy policy</a>
                        <a href="#">Terms & condition</a>
                    </span>
                </div>
            </div>
        </footer>
        <!-- footer ends  -->
        <!-- JavaScript -->
        <script src="JS/homepage_script.js"></script>
        <!--add Student--> 
    </body>
</html>
