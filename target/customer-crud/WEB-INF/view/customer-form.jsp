<%--
  Created by IntelliJ IDEA.
  User: zhanybek
  Date: 18/8/21
  Time: 01:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add New Customer</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css" />
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Customer CRUD Application</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <form:form action="saveCustomer" modelAttribute="customer" method="post">
            <form:hidden path="id"/>
            <table>
                <tbody>
                <tr>
                    <td><label>First name:</label></td>
                    <td><form:input path="firstName"/></td>
                </tr>
                <tr>
                    <td><label>Last name:</label></td>
                    <td><form:input path="lastName"/></td>
                </tr>
                <tr>
                    <td><label>Email:</label></td>
                    <td><form:input path="email"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Save" class="save" /></td>
                </tr>
                </tbody>
            </table>
        </form:form>

        <p>
            <a href="${pageContext.request.contextPath}/customer/list">Cancel</a>
        </p>
    </div>
</div>
</body>
</html>
