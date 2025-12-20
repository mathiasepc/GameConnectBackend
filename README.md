# GameConnect Backend

GameConnect is a social media platform built specifically for gamers.
It allows users to create profiles, share posts with text and media, interact through likes and comments, and discover content based on games and interests.

The project is implemented as a full-stack web application, with a Spring Boot backend exposing a RESTful API and a JavaScript-based frontend consuming it.

---

## 🚀 Features

- User registration and authentication (JWT-based)
- User profiles with:
    - Bio
    - Profile image
- Create posts containing:
    - Text content
    - Optional image/media URLs
    - Tags (genres or interests)
    - Associated game
- Timeline feed showing posts from users
- Like and unlike posts
- Comment on posts
- Tag-based content discovery
- Game search and metadata via the IGDB API
- Role-based access control (USER / ADMIN)
- Preloaded test data for development and testing

---

## 🛠️ Tech Stack

### Backend
- Java 21+
- Spring Boot
- Spring Security (JWT authentication)
- Spring Data JPA
- Hibernate
- RESTful API
- Jackson (JSON serialization)
- H2 (development) / MySQL (production)

### Frontend
- Vanilla JavaScript (ES6)
- HTML5
- Tailwind CSS
- Fetch API

### External Services
- IGDB API (game search and metadata)

---

## ⚙️ Setup & Installation

### 1. Clone the repository

    git clone https://github.com/your-username/gameconnect.git
    cd gameconnect

---

### 2. Backend Requirements

Make sure the following are installed:

- Java 21 or newer
- Maven
- MySQL (optional – H2 can be used for development)

---

### 3. Environment Variables

The backend relies on environment variables for secrets and external services.

Create a .env file or configure environment variables in your system:

    DB_URL=your-database-url
    DB_USERNAME=your-database-url
    DB_PASSWORD=your-database-password
    JWT_SECRET=your-secret-key
    CLIENT_ID=your-igdb-client-id
    CLIENT_SECRET=your-igdb-access-token


---

### 4. Run the Backend

    mvn clean install
    mvn spring-boot:run

The API will start on:

    http://localhost:8080

---

## 🔐 Authentication

Authentication is handled using JWT tokens:

1. User logs in with credentials
2. Backend issues a JWT
3. Token must be included in requests using the Authorization header:

   Authorization: Bearer <token>

---

## 📁 Project Structure (Backend)

    src/
     ├── controller/
     ├── service/
     ├── repository/
     ├── model/
     ├── dto/
     ├── security/
     └── config/

---

## 📌 Notes

- Media uploads are handled via URLs in the current version
- Video uploads are not supported yet
- The project is structured for easy extension (pagination, notifications, moderation tools, etc.)

---

## 📄 License

This project was developed as part of an educational assignment and is intended for learning and demonstration purposes.
