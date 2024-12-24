# 🕵️‍♂️ **Criminal Management System**

The **Criminal Management System** is a comprehensive application designed to manage and monitor criminal records efficiently. Built using **Java Swing** for the front-end interface and **MySQL** for back-end database management, the system offers role-based access for **Operators** and **Admins** to track and modify criminal data. The system supports functionalities like adding, updating, and retrieving criminal records, while ensuring secure user authentication and password management.

## 📜 **Overview**

The system includes several user interfaces, each serving distinct roles for different users:

- **Login Page**: Secure authentication for both operators and admins.
- **Register Page**: Allows operators to register new users (operators or admins).
- **Home Page**: A welcoming dashboard with navigation options for authenticated users.
- **Operator Page**: Enables operators to manage criminal records, update information, and access specific criminal data.
- **Admin Page**: Provides admins with full control to manage users, cases, and detailed criminal information.

Overall, the system ensures **role-based access control**, giving users the appropriate permissions based on their roles.

## 🚀 **Features**

- **Login Page**: Secure login functionality for both operators and admins.
- **Register Page**: Operators can create new user accounts (either operator or admin).
- **Home Page**: Displays a user-friendly interface for authenticated users to navigate through the system.
- **Operator Page**: Manage criminal records, update criminal data, and access case information.
- **Admin Page**: Admins can manage operator accounts and view detailed criminal reports.

## 📋 **Prerequisites**

Before running the application, ensure that you have the following:

- **Java**: JDK 8 or higher installed.
- **MySQL**: MySQL server running locally (or on a remote host).
- **MySQL Connector/J**: Required to enable Java to connect to MySQL (Download from [**MySQL Downloads**](https://dev.mysql.com/downloads/connector/j/)).

## 🛠 **Setup Instructions**

### 1. **Database Setup**

Import the provided database files located in the [**database folder**](Database) into your MySQL server.

```bash
mysql -u root -p < path_to_database_files
```

Alternatively, you can import them manually via **MySQL Workbench** or **phpMyAdmin**.

### 2. **JDBC Configuration**

Make sure the **MySQL Connector/J JAR** file is available in your project directory. The JDBC URL, username, and password must be configured correctly:

```java
DriverManager.getConnection("jdbc:mysql://localhost:3306/criminal", "root", "your_password")
```

Ensure the path to the **MySQL Connector/J JAR** file is added to your classpath.

### 3. **Running the Application**

To run the application, use the following command in your terminal:

```bash
java -cp "C:\path\to\mysql-connector-j-8.4.0.jar;." src.Home
```

Replace `C:\path\to\` with the actual path to your **MySQL Connector/J JAR** file.

---

## 🔄 **Application Flow**

### 1. **Login Page**

The entry point to the system where users log in with their **username** and **password**. There are two roles:

- **Operator**: Has limited access to manage criminal records.
- **Admin**: Has full control over the system and can manage operator accounts.

### 2. **Register Page**

Allows **new operators** to create accounts. They must provide:

- Username
- Password
- Confirm Password

The system ensures the username is unique, and passwords match before creating the account.

### 3. **Home Page**

Once logged in successfully, users are redirected to the **Home Page**, where they can:

- **Criminal Management** (Operator)
- **Password Change** (Operator/Admin)
- **Admin Controls** (Admin)

### 4. **Operator Page**

Operators can:

- **Add Criminal**: Add new criminal records.
- **Update Criminal**: Update existing criminal records.
- **Retrieve Criminal Record**: View details of a criminal record by ID.
- Change their password.

### 5. **Admin Page**

Admins have the following capabilities:

- **View Criminals**: See all the criminal records.
- **Manage Operators**: Add, remove, and update operator accounts.
- **View Reports**: Access detailed reports of criminal cases, including the number of FIRs associated with each criminal.

---

## 🏗 **Database Structure**

The database schema files are included in the [**database folder**](Database). You can find the **ER diagram** and **relational schema** in the [**ER Diagram folder**](ER Diagram) for a more detailed view.

---

## ⚙ **Troubleshooting**

- **MySQL Connection Issues**: Ensure that MySQL is running on `localhost:3306`. If hosted elsewhere, update the connection string accordingly.
- **Missing JDBC Driver**: Make sure the **MySQL Connector/J JAR** file is added to your classpath.
- **Access Denied**: Verify that your MySQL user has necessary permissions to perform database operations.
- **Password Issues**: Ensure passwords for operators and admins are correctly stored and verified.

---

## 👨‍💻 **Contributors**

- [Shivadarshan](https://github.com/shivadarshan-devadiga)

---

## 📜 **License**

This project is licensed under the [MIT License](LICENSE).

---

## 🛠 **Feedback and Support**

For feedback or support, please open an issue on GitHub:

[Open an Issue](https://github.com/shivadarshan-devadiga/Criminal-Database-Management-System/issues)

---

Thank you for exploring the **Criminal Management System**!