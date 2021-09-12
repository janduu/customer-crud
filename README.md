# customer-crud
## About
This web application is a CRUD-supporting project that I implemented using my knowledge which I got from course. To perform CREATE, UPDATE, DELETE operations you have to be authorized as **'ROLE_ADMIN'**, to READ operation - **'ROLE_USER'**. You can also register a new user, but only with **'ROLE_USER'** authority. While registering a new user, validation is performed. Those validations based on rules:
* Password is in range 6 and 50 length;
* Username is unique;
* Password and password confirmation textfields match.

App also uses Spring AOP for logging.

## Technologies used
**Created using:**
* Spring MVC
* Spring Security
* Hibernate ORM
* Hibernate Validator
* PostgeSQL
* Spring AOP

## DB structure
*I've used postgres for this project. So the syntax of config file for posgres command line and shoud be executed by user allowed to create DBs and users*.

**check file:** https://github.com/zhanybekadiev/customer-crud/blob/main/src/main/resources/dbconf.sql
