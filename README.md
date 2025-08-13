# QuickTalk - Multi-person Chat Program

QuickTalk is a multi-person online chat application developed based on Spring Boot and Vue, supporting features such as creating chat rooms, sending text/image/music messages, etc.

## Features

- User registration and login
- Personal information management (avatar, profile modification)
- Chat room creation and management
- Support for multiple message types:
  - Text messages
  - Image messages
  - Music messages
- Real-time chat functionality

## Technology Stack

### Backend
- Spring Boot 2.1.6.RELEASE
- MyBatis-Plus 3.5.3.2 (ORM framework)
- MySQL database
- Hutool utility library

### Frontend
- Vue.js
- Element UI (UI component library)
- Axios (HTTP client)

## Project Structure

```
quicktalk/
├── src/
│   ├── main/
│   │   ├── java/com/
│   │   │   ├── controller/    # Controllers
│   │   │   ├── entity/        # Entity classes
│   │   │   ├── mapper/        # Data access layer
│   │   │   ├── service/       # Business logic layer
│   │   │   └── common/        # Common components
│   │   └── resources/
│   │       ├── static/        # Static resources
│   │       │   ├── css/       # Style sheets
│   │       │   ├── js/        # JavaScript files
│   │       │   ├── upload/    # Uploaded file storage
│   │       │   ├── index.html # Main page
│   │       │   └── register.html # Registration page
│   │       └── application.properties # Configuration file
│   └── test/                  # Test code
├── pom.xml                    # Maven configuration
└── y0698.sql                  # Database script
```

## Database Design

The system contains 3 main tables:

1. `user` - User information table
   - Stores user account, password, avatar, personal profile and other information

2. `room` - Chat room table
   - Stores chat room ID, name and creator account

3. `comment` - Message table
   - Stores message content, sender, associated chat room, message type and sending time

## Quick Start

### Environment Requirements

- JDK 8+
- MySQL 5.7+
- Maven 3.6+

### Deployment Steps

1. Clone the repository
   ```bash
   git clone https://github.com/HongyiHao-SXIT/quicktalk.git
   cd quicktalk
   ```

2. Import the database
   - Execute the `y0698.sql` file to create the database and table structure

3. Configure database connection
   - Modify the database connection information in the `application.properties` file

4. Build the project
   ```bash
   mvn clean package
   ```

5. Run the application
   ```bash
   java -jar target/demo-0.0.1-SNAPSHOT.jar
   ```

6. Access the application
   - Open a browser and visit http://localhost:8080

## Core Function Implementation

### File Upload

The file upload function is handled by `FileController`, supporting the upload of avatars, images and music files, which are stored in the `static/upload` directory.

### Message Management

Message-related operations are handled by `CommentController`, supporting functions such as sending and querying messages. When querying, it will search the sender's avatar information.

### Chat Room Management

Supports the creation, deletion and list display of chat rooms. Users can join different chat rooms to chat.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
