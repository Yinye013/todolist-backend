# Todo List Backend API

A RESTful API backend service for a todo list application built with Spring Boot, featuring user authentication, JWT tokens, and H2 database integration.

## 🚀 Features

- **User Management**: User registration and authentication
- **Todo Operations**: Create, read, update, and delete todos
- **JWT Authentication**: Secure API endpoints with JSON Web Tokens
- **H2 Database**: In-memory database with file persistence
- **RESTful API**: Clean and intuitive API design
- **Spring Security**: Secure password encoding and authentication

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.4.5**
- **Spring Data JPA** - Data persistence
- **Spring Security** - Authentication and authorization
- **JWT (JSON Web Tokens)** - Token-based authentication
- **H2 Database** - Embedded database
- **Maven** - Dependency management

## 📋 Prerequisites

Before running this application, make sure you have:

- Java 17 or higher installed
- Maven 3.6+ installed
- Git (for cloning the repository)

## 🏃‍♂️ Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd todolist-backend
```

### 2. Build the Project
```bash
./mvnw clean install
```

### 3. Run the Application
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 🗄️ Database Configuration

The application uses H2 database with the following configuration:

- **Database URL**: `jdbc:h2:file:./data/todosdb`
- **Username**: `sa`
- **Password**: (empty)
- **H2 Console**: Available at `http://localhost:8080/h2-console`

The database files are stored in the `./data/` directory for persistence across application restarts.

## 📚 API Endpoints

### Authentication Endpoints
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login user and get JWT token

### Todo Endpoints
- `GET /api/todos` - Get all todos for authenticated user
- `POST /api/todos` - Create a new todo
- `GET /api/todos/{id}` - Get specific todo by ID
- `PUT /api/todos/{id}` - Update a todo
- `DELETE /api/todos/{id}` - Delete a todo

### Test Endpoints
- `GET /hello` - Simple hello world endpoint

## 🔧 Project Structure

```
src/
├── main/
│   ├── java/com/todo/todolist/
│   │   ├── TodolistApplication.java          # Main application class
│   │   ├── controllers/                      # REST controllers
│   │   │   └── HelloController.java
│   │   ├── dto/                             # Data Transfer Objects
│   │   │   ├── TodoRequestDTO.java
│   │   │   └── TodoResponseDTO.java
│   │   ├── models/                          # JPA entities
│   │   │   ├── Todo.java
│   │   │   └── User.java
│   │   ├── repositories/                    # Data access layer
│   │   │   ├── TodoRepository.java
│   │   │   └── UserRepository.java
│   │   └── services/                        # Business logic layer
│   │       ├── AuthService.java
│   │       └── TodoServices.java
│   └── resources/
│       └── application.properties           # Application configuration
└── test/                                   # Test classes
```

## 🔐 Authentication

This application uses JWT (JSON Web Tokens) for authentication:

1. Register a new user or login with existing credentials
2. Receive a JWT token in the response
3. Include the token in the `Authorization` header as `Bearer <token>` for protected endpoints

## 📝 Data Models

### User Entity
- `id` (Long) - Auto-generated primary key
- `username` (String) - Unique username
- `email` (String) - User email address
- `password` (String) - Encrypted password

### Todo Entity
- `id` (Long) - Auto-generated primary key
- `title` (String) - Todo title
- `description` (String) - Todo description
- `completed` (Boolean) - Completion status

## 🧪 Testing

Run the test suite with:
```bash
./mvnw test
```

## 🔧 Configuration

Key configuration properties in `application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:h2:file:./data/todosdb
spring.datasource.username=sa
spring.datasource.password=

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## 🚀 Deployment

### Building for Production
```bash
./mvnw clean package
java -jar target/todolist-0.0.1-SNAPSHOT.jar
```

### Environment Variables
For production deployment, consider setting these environment variables:
- `SPRING_PROFILES_ACTIVE=prod`
- `DATABASE_URL` - Production database URL
- `JWT_SECRET` - JWT signing secret

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🐛 Known Issues

- H2 console requires manual database URL entry
- JWT token expiration time is currently hardcoded

## 📞 Support

If you encounter any issues or have questions, please open an issue on GitHub.

---

**Happy Coding! 🎉**
