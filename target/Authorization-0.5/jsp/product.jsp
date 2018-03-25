<%--
  Created by IntelliJ IDEA.
  User: Daniil
  Date: 25.03.2018
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Home (Product)</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Products:
    </div>
    <table>
        <tr>
            <th>Product Name</th>
            <th>Product Price</th>
        </tr>
        <c:forEach items="${productFromServer}" var="product">
            <tr>
                <td>${product.product}</td>
                <td>${product.price}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Add product:
    </div>
    <form method="post" action="">

        <label for="product">Product
            <input class="input-field" type="text" id="product" name="product">
        </label>
        <label for="price">Price
            <input class="input-field" type="text" id="price" name="price">
        </label>
        <input type="submit" value="Add">

    </form>
</div>
</body>
</html>
