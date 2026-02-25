## Tech Stack

### Database
- MySQL 

### Backend
- Java
- Spring Boot
- Maven
- REST APIs

### Frontend
- HTML
- CSS
- Bootstrap
- JSP
- Node.js (npm – frontend build tool)
 --- 
 ## Application Workflow
 
<img width="1302" height="510" alt="image" src="https://github.com/user-attachments/assets/a52ef8ae-38cb-4e81-be4c-c3826ce6745a" />

---


---

# Launch EC2 Instance (Ubuntu)

Create an EC2 Ubuntu instance from AWS Console.

### Security Group Rules

Add inbound rules:

| Type       | Port | Source    |
| ---------- | ---- | --------- |
| SSH        | 22   | My IP     |
| HTTP       | 80   | 0.0.0.0/0 |
| Custom TCP | 8080 | 0.0.0.0/0 |

---

### Connect to Instance

```bash
ssh -i key.pem ubuntu@<EC2-Public-IP>
```

Update packages:

```bash
sudo apt update
```

---

# Setup MySQL Database (AWS RDS)

Create a MySQL database using AWS RDS.

Go to:

```
AWS Console → RDS → Create Database
```

Copy the RDS endpoint.

Example:

```
studentdb.xxxxx.ap-south-1.rds.amazonaws.com
```

---

### Install MySQL Client on EC2

```bash
sudo apt update
sudo apt install mysql-client -y
```

---

### Login to RDS

```bash
mysql -h <rds-endpoint> -u admin -p
```

Example:

```bash
mysql -h studentdb.xxxxx.ap-south-1.rds.amazonaws.com -u admin -p
```

---

### Create Database

```sql
CREATE DATABASE student_db;
```

```sql
USE student_db;
```

---

### Create Students Table

```sql
CREATE TABLE students (
id bigint NOT NULL AUTO_INCREMENT,
name varchar(255),
email varchar(255),
course varchar(255),
student_class varchar(255),
percentage double,
branch varchar(255),
mobile_number varchar(255),
PRIMARY KEY (id)
);
```

---

### Exit MySQL

```bash
exit
```

---

### Important Note

Make sure port **3306** is allowed in the RDS Security Group.

---

# Setup Backend (Spring Boot)

### Clone Repository

```bash
git clone https://github.com/abhipraydhoble/project-studentapp-aws.git
```

---

### Install Java 17

```bash
sudo apt install openjdk-17-jdk -y
```

Verify:

```bash
java -version
```

---

### Install Maven

```bash
sudo apt install maven -y
```

Verify:

```bash
mvn -version
```

---

### Navigate to Backend Directory

```bash
cd studentapp_updated/backend
```

---

### Configure Database Connection

Edit file:

```bash
vim src/main/resources/application.properties
```

Add:

```properties
server.port=8080

spring.datasource.url=jdbc:mariadb://<rds-endpoint>:3306/student_db

spring.datasource.username=admin

spring.datasource.password=admin123

spring.jpa.hibernate.ddl-auto=update
```

---

### Build Backend Application

```bash
mvn clean package -DskipTests
```

---

### Run Backend Application

```bash
java -jar target/student-registration-backend-0.0.1-SNAPSHOT.jar
```

Test backend:

```
http://EC2-IP:8080
```

---

# Setup Frontend (React)

### Navigate to Frontend Directory

```bash
cd ~/studentapp_updated/Frontend
```

---

### Install Node.js and npm

```bash
sudo apt install nodejs npm -y
```

Verify installation:

```bash
node -v
npm -v
```

---

### Configure Backend API URL

Edit file:

```
src/api/userService.js
```

Example:

```js
const BASE_URL = "http://EC2-IP:8080/api";
```

---

### Install Frontend Dependencies

```bash
npm install
```

---

### Run Frontend Application

```bash
npm start
```

Access frontend:

```
http://EC2-IP:3000
```

---

### Build Frontend Application

```bash
npm run build
```

---

# Host Frontend Using Apache (Optional)

### Install Apache

```bash
sudo apt install apache2 -y
```

---

### Copy Build Files

```bash
sudo cp -r dist/* /var/www/html/
```

---

### Start Apache

```bash
sudo systemctl start apache2
```

---

### Access Website

```
http://EC2-IP
```

---


