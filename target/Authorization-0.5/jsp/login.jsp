<%--
  Created by IntelliJ IDEA.
  User: Daniil
  Date: 25.03.2018
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Login
    </div>
    <form method="post" action="">

        <label for="name">Username
            <input class="input-field" type="text" id="name" name="name" value=${cookie.name.value}>
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password" value=${cookie.password.value}>
        </label>
        <input type="submit" value="Login">

    </form>
</div>

</body>
</html>
