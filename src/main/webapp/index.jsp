<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <style>
        p {
            font-size: 30px;
        }
    </style>
    <title>Main Page</title>
</head>
<body>
<h2>Main Page</h2>

<p>
    It's a simple CRUD web application using spring mvc, security.
    It uses hibernate ORM to work with DB and hibernate-validator to validate fields.
    For more info check: <a href="https://github.com/zhanybekadiev/customer-crud">here</a>
</p>
<p>
    To see list of students - <a href="${pageContext.request.contextPath}/customer/list">click here</a>
</p>
</body>
</html>
