# Vehicle Rental Management System (VRMS)

A full-stack starter based on the uploaded project diagram:
- Frontend: React + TypeScript + Vite
- Backend: Java 17 + Spring Boot 3 + Spring Data JPA + Spring Security + JWT
- Database: H2 by default for easy local startup; PostgreSQL configuration included
- Features: customer registration/login, vehicle browsing/search, date-based availability, booking creation, admin vehicle CRUD, booking management, dashboard metrics, invoice/confirmation screen, Stripe integration placeholder.

## Quick start

### Requirements
- Node.js 20+ and npm
- Java 17+
- Maven 3.9+ (or use your IDE's Maven support)

### 1) Start backend
```bash
cd backend
mvn spring-boot:run
```
Backend runs at `http://localhost:8080`. H2 console: `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:vrmsdb`, user `sa`, blank password).

Seed admin:
- Email: `admin@vrms.local`
- Password: `Admin123!`

Seed demo customer:
- Email: `customer@vrms.local`
- Password: `Customer123!`

### 2) Start frontend in another terminal
```bash
cd frontend
npm install
npm run dev
```
Open the URL Vite prints, usually `http://localhost:5173`.

## API overview
- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/vehicles?search=&category=`
- `GET /api/vehicles/{id}`
- `GET /api/vehicles/{id}/availability?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD`
- `POST /api/bookings` (authenticated)
- `GET /api/bookings/my` (authenticated)
- `GET /api/admin/dashboard` (admin)
- `POST /api/admin/vehicles` (admin)
- `PUT /api/admin/vehicles/{id}` (admin)
- `DELETE /api/admin/vehicles/{id}` (admin)
- `GET /api/admin/bookings` (admin)
- `PATCH /api/admin/bookings/{id}/status?status=CONFIRMED` (admin)

## Notes before real deployment
This is a runnable starter, not a production-ready payment or deployment setup. Stripe checkout is intentionally a placeholder; do not collect or store raw card details. Configure Stripe Checkout on the server with secret keys in environment variables before taking payments. Replace the development JWT secret, use HTTPS, configure a production database, add email delivery and tests, and review security settings before public deployment.
