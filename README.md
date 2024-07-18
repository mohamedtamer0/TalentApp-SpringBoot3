# TalentApp 🚀

**TalentApp** is a Job Portal Web Application developed using Spring Boot 3, Java 17, and Oracle Database. It provides a comprehensive solution for job seekers and recruiters to connect and manage job applications.

## Features ✨

- **User Registration & Authentication:** Secure sign-up, login, and profile management 🔐
- **Job Listings:** Browse and search job postings 🔍
- **Application Management:** Apply for jobs and track the status of applications 📋
- **Admin Dashboard:** Manage job listings and user accounts 🛠️

## Technologies Used 🛠️

- **Java 17** ☕
- **Spring Boot 3** 🌟
- **Spring MVC** 🌐
- **Thymeleaf** 🧩
- **Spring Security** 🔒
- **Spring Data JPA** 📊
- **Hibernate/JPA** 🗄️
- **Oracle Database** 🏛️

## Installation 🛠️

### Prerequisites 🚧

- JDK 17
- Oracle Database
- Maven

### Steps 📝

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/mohamedtamer0/TalentApp-SpringBoot3.git
   cd TalentApp-SpringBoot3
   ```

2. **Configure Application Properties:**

   Update the `src/main/resources/application.properties` with your Oracle DB credentials:

   ```properties
   spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```

3. **Build the Project:**

   ```bash
   mvn clean install
   ```

4. **Run the Application:**

   ```bash
   mvn spring-boot:run
   ```

## Usage 🚀

- **Access the Application:** Open your browser and go to `http://localhost:8080` 🌐
- **Register an Account:** Use the registration form to create a new account 📝
- **Login:** Access and manage your profile and job applications 🔑

## Contributing 🤝

Contributions are welcome! Please fork the repository, make your changes, and submit a pull request 🔄
