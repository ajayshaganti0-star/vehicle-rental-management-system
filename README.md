# Vehicle Rental Management System (VRMS)

A full-stack vehicle rental application.

- **Frontend:** React, TypeScript, Vite
- **Backend:** Java, Spring Boot, Spring Data JPA, Spring Security, JWT
- **Database:** H2
- **Features:** Customer registration and login, vehicle browsing, availability checking, bookings, admin vehicle management, dashboard metrics, and invoice/confirmation screens.

## Requirements

- Node.js and npm
- Java 21 (or the version configured in your project)
- Maven, or Maven support in your IDE

## Quick Start

### 1. Start the backend

Open a terminal in VS Code and run:

```bash
cd backend
mvn spring-boot:run
```

The backend should run at `http://localhost:8080`.

### 2. Start the frontend

Open a second terminal in VS Code and run:

```bash
cd frontend
npm install
npm run dev
```

Open the local URL printed in the terminal.

## API Endpoints

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/vehicles`
- `POST /api/bookings`
- `GET /api/bookings/my`

## Notes

This project is intended for development and learning. Review security settings, JWT secrets, database configuration, and payment integration before deploying it publicly.
