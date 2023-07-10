<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/style.css">
        <title>Login Page</title>
    </head>
    <body>
        <div class="container">
            <input type="checkbox" id="flip">
            <div class="cover">
                <div class="front">
                    <img src="images/truong-dai-hoc-fpt-tp-hcm-(7).jpg" alt="">
                    <div class="text">
                        <!-- <span class="text-1">Every new friend is a <br> new adventure</span>
                        <span class="text-2">Let's get connected</span> -->
                    </div>
                </div>

            </div>
            <div class="forms">
                <div class="form-content">
                    <div class="login-form">
                        <div class="title">Login</div>
                        <form action="MainController" method="POST">
                            <div class="input-boxes">
                                <div class="inputBox">

                                    <input type="text" name="email" value="" required="required" />
                                    <small>Enter your email</small>
                                    <i></i>
                                </div>
                                <div class="inputBox">
                                    <input type="password" name="password" value="" required="" />
                                    <small>Enter your password</small>
                                    <i></i>
                                </div>
                                <div class="button input-box">
                                    <input type="submit" value="Login" name="action" class="submit-button" />
                                </div>
                                <div class="text sign-up-text">Don't have an account? <label for="flip">Forgot password?</label></div>
                            </div>
                        </form>
                    </div>
                    <div class="signup-form">
                        <div class="title">Forgot password?</div>
                        <form action="ForgotController" method="POST">
                            <div class="input-boxes">
                                <div class="inputBox">
                                    <input type="text" name="email" value="" required="required"/>
                                    <input type="hidden" name="email" 
                                           value="<%=session.getAttribute("EXSITED_EMAIL") %>" required="required"/>
                                    <small>Enter your email</small>
                                    <i></i>
                                    
                                </div>
                                <div class="button input-box">
                                    <input type="submit" value="Sumbit" name="action">
                                </div>
                                <div class="text sign-up-text">Already have an account? <label for="flip">Login now</label></div>
                            </div>
                        </form>
                        <%
                            String message1 = (String) request.getAttribute("ERROR_EMAIL");
                            if (message1 != null) {
                        %>
                        <h5 style="color: red"><%= message1%></h5>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
            <div>

            </div>
            <%
                String message = (String) session.getAttribute("LOGIN");
                if (message != null) {
            %>
            <h4 style="color: red"><%= message%></h4>
            <%
                }
            %>
        </div>

    </body>
</html>
