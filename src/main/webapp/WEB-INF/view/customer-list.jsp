<%--
  Created by IntelliJ IDEA.
  User: zhanybek
  Date: 16/8/21
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>List Customers</title>
    <!-- reference our style sheet -->
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Customer CRUD Application</h2>
    </div>
</div>

<div id="container">
    <div id="content">

        <!--  add our html table here -->
        <button class="add-button" onclick="window.location.href='customerAddForm'; return false;">Add Customer</button>
        <form action="${pageContext.request.contextPath}/customer/search" method="GET">
            Search customer:
            <label>
                <input type="text" name="theSearchName" />
            </label>
            <input type="submit" value="Search" class="add-button" />
        </form>

        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <!-- loop over and print our customers -->
            <core:forEach var="tempCustomer" items="${customers}">

                <core:url var="updateLink" value="/customer/customerUpdateForm">
                    <core:param name="customerId" value="${tempCustomer.id}" />
                </core:url>

                <core:url var="deleteLink" value="/customer/customerDelete">
                    <core:param name="customerId" value="${tempCustomer.id}" />
                </core:url>
                <tr>
                    <td>${tempCustomer.firstName}</td>
                    <td>${tempCustomer.lastName}</td>
                    <td>${tempCustomer.email}</td>
                    <td>
                        <a href="${updateLink}">Update</a> / <a href="${deleteLink}">Delete</a>
                    <%--                        <a href="${pageContext.request.contextPath}/customer/customerUpdateForm?customerId=${tempCustomer.id}">Update</a>--%>
                    </td>
                </tr>
            </core:forEach>

        </table>

        <br>
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="submit" value="Logout" class="add-button">
        </form:form>
    </div>

</div>


</body>
</html>
