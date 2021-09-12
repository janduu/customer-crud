-- These scripts are for postgreSQL (some work for other DBMS)

-- 1. Create database 'springmvc':
DROP DATABASE IF EXISTS springmvc;
CREATE DATABASE springmvc;

-- 2. Create database user and grant all privileges to this user:
DROP USER IF EXISTS springmvc;
CREATE USER springmvc WITH PASSWORD 'springmvcpass';

-- Connect to database:
\c springmvc

-- 3. Create tables for app users and their roles:

CREATE TABLE users (
  user_id SERIAL PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL
);

CREATE TABLE role (
  role_id SERIAL PRIMARY KEY,
  authority VARCHAR(50) NOT NULL
);

CREATE TABLE user_role (
    user_id INT NOT NULL REFERENCES users(user_id),
    role_id INT NOT NULL REFERENCES role(role_id),
    PRIMARY KEY(user_id, role_id)
);


-- Insert app users and roles with users: (zhan, kali, ROLE_USER) and (admin, linux, {ROLE_ADMIN, ROLE_USER}) :
INSERT INTO users(username, password) 
VALUES
('zhan', '$2a$10$c8EOwp67770jEL21HOvv4OnHCfQ6ASp17d9kfEdGPKWhpDzaN22.6'), -- password = 'kali'
('admin', '$2a$10$rXGqNFIEhA8kny.cZWYErOIMDj8BDE1Mz4A28Y6.binTV.aLujCIC'); -- password = 'linux'

INSERT INTO role (authority) 
VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO user_role
SELECT user_id, (SELECT role_id FROM role WHERE authority = 'ROLE_USER') AS role_id
FROM users
UNION
SELECT user_id, (SELECT role_id FROM role WHERE authority = 'ROLE_ADMIN') AS role_id
FROM users WHERE username = 'admin';

-- 4. Create table 'customer' for CRUD-operations:

CREATE TABLE customer(
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50)
);

GRANT ALL PRIVILEGES ON DATABASE springmvc TO springmvc;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO springmvc;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO springmvc;

-- to see result:

SELECT username, password, authority
FROM users
JOIN user_role USING(user_id)
JOIN role USING(role_id);
