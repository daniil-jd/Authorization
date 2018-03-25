<%--
  Created by IntelliJ IDEA.
  User: Daniil
  Date: 25.03.2018
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>SignUp Page</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        SignUp Page
    </div>
    <form method="post" action="">

        <label for="name">Username
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <input type="submit" value="SignUp">

    </form>
</div>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Registered Users:
    </div>
    <table>
        <tr>
            <th>Name</th>
        </tr>
        <c:forEach items="${usersFromServer}" var="product">
            <tr>
                <td>${product.name}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
