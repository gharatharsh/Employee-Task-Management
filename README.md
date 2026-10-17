<div align="center">

# 🗂️ Employee Task Management System

![Java](https://img.shields.io/badge/Java-17-blue?logo=openjdk&logoColor=white)
![Servlets](https://img.shields.io/badge/Java%20Servlets-5.0-orange)
![JSP](https://img.shields.io/badge/JSP-2.3-yellow)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-green?logo=hibernate)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?logo=postgresql&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Apache%20Tomcat-9-red?logo=apachetomcat&logoColor=white)

A web-based **Employee Task Management System** built with **Java Servlets, JSP, JDBC, Hibernate, and PostgreSQL**.

Managers can log in to register, create and assign tasks, and manage developers. Developers can log in, update their details, and view their assigned tasks. The application uses **session-based authentication**.

[![GitHub](https://img.shields.io/badge/GitHub-gharatharsh-black?logo=github)](https://github.com/gharatharsh)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Harsh%20Gharat-blue?logo=linkedin)](https://www.linkedin.com/in/harsh-gharat)

</div>

---

## 📑 Table of Contents

- [✨ Features](#-features)
- [🛠️ Tech Stack](#️-tech-stack)
- [📁 Project Structure](#-project-structure)
- [🚀 Getting Started](#-getting-started)
- [🗄️ Database Setup](#️-database-setup)
- [⚙️ Hibernate Configuration](#️-hibernate-configuration)
- [🔄 How It Works](#-how-it-works)
- [👥 User Roles](#-user-roles)
- [📚 What I Learned](#-what-i-learned)
- [🔮 Future Improvements](#-future-improvements)
- [👨‍💻 Author](#-author)
- [📄 License](#-license)

---

## ✨ Features

- 🔐 Manager registration and login using session-based authentication  
- 👨‍💻 Developer registration and login using session-based authentication  
- 📝 Managers can create, assign, update, and delete tasks  
- 👥 Managers can view and manage developer records  
- 🧑 Developers can update their own details  
- 📋 Developers can view tasks assigned to them  
- 🔄 Task status tracking: `Pending`, `In Progress`, `Completed`  
- 🗄️ Persistent storage using PostgreSQL  
- 🔗 Database access using Hibernate, JPA, and JDBC  
- ⚙️ Server-side request handling using Java Servlets  
- 🎨 Dynamic frontend using JSP, HTML, CSS, and JSTL  

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| **Backend** | Java, Java Servlets |
| **Frontend** | JSP, HTML, CSS, JSTL |
| **ORM / Persistence** | Hibernate, JPA |
| **Database Access** | JDBC |
| **Database** | PostgreSQL |
| **Authentication** | HttpSession |
| **Server** | Apache Tomcat |
| **IDE** | Eclipse / IntelliJ IDEA |

---

## 📁 Project Structure

```text
EmployeeTaskManagementSystem/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── etms/
│       │           ├── controller/
│       │           │   ├── LoginServlet.java
│       │           │   ├── LogoutServlet.java
│       │           │   ├── RegisterServlet.java
│       │           │   ├── ManagerServlet.java
│       │           │   ├── DeveloperServlet.java
│       │           │   ├── TaskServlet.java
│       │           │   └── ProfileServlet.java
│       │           │
│       │           ├── dao/
│       │           │   ├── UserDAO.java
│       │           │   ├── TaskDAO.java
│       │           │   └── DBConnection.java
│       │           │
│       │           ├── model/
│       │           │   ├── User.java
│       │           │   ├── Manager.java
│       │           │   ├── Developer.java
│       │           │   └── Task.java
│       │           │
│       │           └── util/
│       │               ├── HibernateUtil.java
│       │               └── SessionUtil.java
│       │
│       ├── resources/
│       │   └── META-INF/
│       │       └── persistence.xml
│       │
│       └── webapp/
│           ├── index.jsp
│           ├── login.jsp
│           ├── register.jsp
│           ├── manager-dashboard.jsp
│           ├── developer-dashboard.jsp
│           ├── create-task.jsp
│           ├── assign-task.jsp
│           ├── view-tasks.jsp
│           ├── update-profile.jsp
│           ├── css/
│           │   └── style.css
│           └── WEB-INF/
│               ├── web.xml
│               └── lib/
│                   ├── postgresql-42.x.x.jar
│                   ├── hibernate-core.jar
│                   └── other-required-jars
│
├── pom.xml
└── README.md
```

> 💡 **Note:** The `persistence.xml` file must be placed inside the `META-INF` folder in the Java resources/classpath so Hibernate can detect it.

---

## 🚀 Getting Started

### 📌 Prerequisites

- ☕ JDK 11 or higher  
- 🐱 Apache Tomcat 9 or higher  
- 🐘 PostgreSQL  
- 🔌 PostgreSQL JDBC Driver  
- ⚙️ Hibernate and JPA dependencies  
- 🧠 Eclipse or IntelliJ IDEA  
- 📦 Maven, if using Maven  

### 🛠️ Installation

1. **Clone the repository**

```bash
git clone [https://github.com/gharatharsh/EmployeeTaskManagementSystem.git](https://github.com/gharatharsh/EmployeeTaskManagementSystem.git)
```

2. **Import the project** into Eclipse or IntelliJ IDEA.

3. **Add dependencies**  
   Add the required JAR files to `WEB-INF/lib`, or add dependencies in `pom.xml`.

4. **Create the PostgreSQL database**

5. **Update database credentials** in:

```text
src/main/resources/META-INF/persistence.xml
```

6. **Deploy the project** to Apache Tomcat.

7. **Start Tomcat** and open the application:

```text
http://localhost:8080/EmployeeTaskManagementSystem/
```

---

## 🗄️ Database Setup

Create the PostgreSQL database:

```sql
CREATE DATABASE employee_task_db;
```

Since Hibernate is configured with:

```text
hibernate.hbm2ddl.auto = update
```

tables will be created automatically from entity classes.

You can also create the tables manually:

```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    phone VARCHAR(15),
    designation VARCHAR(100)
);

CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    status VARCHAR(30) DEFAULT 'PENDING',
    due_date DATE,
    assigned_to INT REFERENCES users(id),
    created_by INT REFERENCES users(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## ⚙️ Hibernate Configuration

Create this file:

```text
src/main/resources/META-INF/persistence.xml
```

Add the following configuration:

```xml
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
                                 http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
             version="2.1">

    <persistence-unit name="dbInfo" transaction-type="RESOURCE_LOCAL">

        <properties>
            <property name="javax.persistence.jdbc.driver"
                      value="org.postgresql.Driver"/>
            <!-- DB Driver -->

            <property name="javax.persistence.jdbc.url"
                      value="jdbc:postgresql://localhost:port-number/project-name"/>
            <!-- DB Name -->

            <property name="javax.persistence.jdbc.user"
                      value="Database-name"/>
            <!-- DB User -->

            <property name="javax.persistence.jdbc.password"
                      value="password"/>
            <!-- DB Password -->

            <property name="hibernate.dialect"
                      value="org.hibernate.dialect.PostgreSQLDialect"/>
            <!-- DB Dialect -->

            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <!-- create / create-drop / update -->

            <property name="hibernate.show_sql" value="true"/>
            <!-- Show SQL in console -->

            <property name="hibernate.format_sql" value="true"/>
            <!-- Show SQL formatted -->
        </properties>

    </persistence-unit>

</persistence>
```

### 🔧 Replace These Values

| Property | Example |
|---|---|
| **Port number** | `5432` |
| **Database name** | `employee_task_db` |
| **Database user** | `postgres` |
| **Database password** | `your_password` |

### ✅ Example Configuration

```xml
<property name="javax.persistence.jdbc.url"
          value="jdbc:postgresql://localhost:5432/employee_task_db"/>

<property name="javax.persistence.jdbc.user"
          value="postgres"/>

<property name="javax.persistence.jdbc.password"
          value="root"/>
```

---

## 🔄 How It Works

```text
🌐 Browser / JSP Form
        |
        v
⚙️ Java Servlet
        |
        v
🔐 Session-based Authentication
        |
        v
📦 DAO / Hibernate / JPA
        |
        v
🔌 JDBC Driver
        |
        v
🐘 PostgreSQL Database
        |
        v
📤 Servlet sends response to JSP
        |
        v
🌐 Browser displays result
```

### 👨‍💼 Manager Flow

1. Manager registers or logs in.  
2. Manager details are stored in `HttpSession`.  
3. Manager creates a task.  
4. Manager assigns the task to a developer.  
5. Manager can view, update, or delete tasks.  
6. Manager can manage developer records.  

### 👨‍💻 Developer Flow

1. Developer registers or logs in.  
2. Developer details are stored in `HttpSession`.  
3. Developer views assigned tasks.  
4. Developer updates their profile details.  

---

## 👥 User Roles

| Role | Permissions |
|---|---|
| 👨‍💼 **Manager** | Register, login, create tasks, assign tasks, update/delete tasks, manage developers |
| 👨‍💻 **Developer** | Register, login, view assigned tasks, update personal details |

---

## 📚 What I Learned

- ☕ Java Servlet lifecycle and request/response handling  
- 🖥️ Creating dynamic pages using JSP  
- 🔐 Session-based login and logout  
- 👥 Role-based access for managers and developers  
- 🗄️ Connecting Java web applications to PostgreSQL  
- ⚙️ Using Hibernate and JPA for database operations  
- 📄 Configuring `persistence.xml`  
- 🔄 Performing CRUD operations  
- 🚀 Deploying applications on Apache Tomcat  

---

## 🔮 Future Improvements

- 🔒 Password hashing using BCrypt  
- ✅ Input validation and better error messages  
- 🔍 Task search, sorting, and filtering  
- 📧 Task deadline reminders using email notifications  
- 📊 Admin dashboard with reports  
- 🌐 REST API support  
- 🍃 Migration to Spring Boot and Spring Security  
- ⚡ Database connection pooling  

---

## 👨‍💻 Author

Created with ❤️ for educational and learning purposes.

<div align="center" >

[![GitHub](https://img.shields.io/badge/GitHub-gharatharsh-black?logo=github)](https://github.com/gharatharsh)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Harsh%20Gharat-blue?logo=linkedin)](https://www.linkedin.com/in/harsh-gharat)

</div>

---

## 📄 License

This project was created for **educational and learning purposes**.
