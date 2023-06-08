<%@page import="DTO.UserAccountDTO"%>
<%@page import="DTO.AccountError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"ADMIN".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.jsp");
                return;
            }
            
            AccountError accountError = (AccountError)request.getAttribute("ACCOUNT_ERROR");
            if(accountError == null) {
                accountError = new AccountError();
            }
        %>
        <h3>Create Account</h3>
        <form action="MainController">
            Email: <input type="text" name="email" value="" required=""/>
            <%= accountError.getEmailError() %> <br>
            Full Name: <input types="text" name="fullName" values="" requirede=""/>
            <%= accountError.getFullNameError() %> <br> 
            Role: <input type="text" name="role" values="" required=""/>
            <%= accountError.getRoleError() %> <br> 
            Password: <input type="text" name="password" values="" required=""/>
            <%= accountError.getPasswordError() %> <br> 
            Confirm: <input type="text" name="confirm" values="" required=""/>
            <%= accountError.getConfirmError() %> <br> 
            <input type="submit" value="CreateAccount" name="action" />
            <input type="reset" value="Reset" />
            <%= accountError.getMessageError() %>
        </form>
    </body>
</html>
