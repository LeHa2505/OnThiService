# High School Entrance Exam Preparation System - Backend

## Overview

The High School Entrance Exam Preparation System is a comprehensive web application designed to help students prepare for high school entrance examinations. This project provides effective learning, practice, and knowledge assessment features to enhance student performance.

## Technology Stack

- **Backend**: Java Spring Boot
- **Database**: MySQL
- **ORM**: Hibernate + JOOQ
- **Security**: Spring Security + JWT
- **Real-time Communication**: WebSocket
- **Build Tool**: Maven
- **File Storage**: Cloudinary, Google Cloud Storage

## Key Features

### User Management
- User registration and authentication
- Role-based authorization (student, teacher, admin)
- User profile management
- JWT-based security

### Course Management
- Create and manage courses by subject
- Lesson and document management
- Content categorization by level and curriculum
- Course progress tracking

### Exercise and Assessment System
- Multiple-choice quiz creation and management
- Automated grading system
- Learning progress monitoring
- Performance analytics and reporting

### Interactive Communication
- Real-time chat between students and teachers
- Notification system
- Discussion forums
- WebSocket-based messaging

### Analytics and Reporting
- Student progress reports
- Score statistics and rankings
- Subject-wise strength/weakness analysis
- Performance dashboards

## Installation and Setup

### System Requirements
- Java 17 or higher
- MySQL 8.0+
- Maven 3.6+

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/Kiengabby/high-school-entrance-exam-prep-BE.git
   cd high-school-entrance-exam-prep-BE
   ```

2. **Database Configuration**
   - Create a MySQL database
   - Update database connection details in `src/main/resources/application.yml`

3. **Install Dependencies**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

   Or using Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access the Application**
   - API Base URL: `http://localhost:8080`
   - API Documentation: `http://localhost:8080/swagger-ui.html`

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── cfm/onthi/
│   │       ├── OnThiServiceApplication.java
│   │       ├── configurations/          # Spring Configuration
│   │       ├── controllers/            # REST Controllers
│   │       ├── dtos/                   # Data Transfer Objects
│   │       ├── entities/               # JPA Entities
│   │       ├── repositories/           # Data Access Layer
│   │       ├── services/               # Business Logic Layer
│   │       ├── security/               # Security Configuration
│   │       └── utils/                  # Utility Classes
│   └── resources/
│       └── application.yml             # Application Configuration
└── test/                               # Unit and Integration Tests
```

## Configuration

### Database Configuration
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_database_name
    username: your_username
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    database-platform: org.hibernate.dialect.MySQL8Dialect
    show-sql: true
```

### Security Configuration
- JWT-based Authentication
- Role-based Authorization (STUDENT, TEACHER, ADMIN)
- CORS Configuration for frontend integration
- Password encryption using BCrypt

### File Upload Configuration
- Cloudinary integration for image storage
- Google Cloud Storage for document files
- Maximum file size: 200MB

## Testing

Run unit tests:
```bash
mvn test
```

Run with coverage:
```bash
mvn test jacoco:report
```

## API Documentation

The API documentation is available at: `http://localhost:8080/swagger-ui.html`

### Main API Endpoints:
- `/api/auth/**` - Authentication & Authorization
- `/api/users/**` - User Management
- `/api/courses/**` - Course Management
- `/api/exercises/**` - Exercise and Quiz Management
- `/api/chat/**` - Real-time Chat System
- `/api/notifications/**` - Notification Management
- `/api/files/**` - File Upload and Management

### Authentication
All protected endpoints require a valid JWT token in the Authorization header:
```
Authorization: Bearer <your_jwt_token>
```

## Deployment

### Production Configuration
1. Update `application.yml` for production environment
2. Configure production database connection
3. Set up proper logging configuration
4. Configure CORS for production frontend URL


## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Code Style Guidelines
- Follow Java naming conventions
- Use meaningful variable and method names
- Add comprehensive comments for complex logic
- Write unit tests for new features
- Ensure all tests pass before submitting PR

