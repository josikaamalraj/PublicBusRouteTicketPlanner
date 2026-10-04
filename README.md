# Public Bus Route & Ticket Planner

College mini project using **HTML5 + CSS3 + Vanilla JavaScript + Fetch API + Java 17 + Spring Boot 3.x + Spring Data JPA/Hibernate + MySQL 8.x**.

## Architecture

```text
HTML/CSS/JavaScript (localhost:5500)
          ↓ HTTP / REST / JSON
Spring Boot REST API (localhost:8080)
          ↓ Spring Data JPA / Hibernate
MySQL (localhost:3306)
```

## Required software

- JDK 17 or newer (project is compiled with Java 17 compatibility)
- Maven 3.9+ OR use the Maven wrapper included in the backend
- MySQL 8.x
- VS Code with Live Server (recommended for the frontend)
- Postman (optional, for API testing)
- Git/GitHub (optional)

## Database setup

1. Start MySQL.
2. Open MySQL Workbench or the MySQL command line.
3. Run `database/schema.sql`.
4. Run `database/sample-data.sql`.
5. Verify with the queries in `database/queries.sql`.

The schema creates `bus_route_planner_db`, `users`, `routes`, `buses`, and `bookings`. The backend uses `spring.jpa.hibernate.ddl-auto=none`, so Hibernate will not recreate or rename these tables.

## MySQL configuration

Open:

`backend/src/main/resources/application.properties`

Replace:

```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

with the password for your local MySQL `root` account. Do not put this password in the frontend.

## Backend setup

From the `backend` folder:

```text
mvn spring-boot:run
```

On Windows, if the Maven wrapper is present:

```text
mvnw.cmd spring-boot:run
```

or:

```text
.\mvnw.cmd spring-boot:run
```

Backend URL: `http://localhost:8080`

## Frontend setup

Open the project in VS Code and use Live Server on the `frontend` folder. The expected URL is:

`http://localhost:5500/frontend/index.html`

The JavaScript API base is:

```javascript
const API_BASE_URL = "http://localhost:8080/api";
```

If Live Server uses another port, update the backend CORS allowed origin in `WebConfig.java`.

## Test accounts

Passenger:

- `arun@gmail.com` / `arun123`
- `priya@gmail.com` / `priya123`
- `rahul@gmail.com` / `rahul123`

Admin:

- `admin@gmail.com` / `admin123`

These sample passwords are plain text only for demonstration. A production system should use BCrypt/password hashing and proper authentication/authorization.

## Sample routes

- 101: Tambaram → Broadway, ₹25
- 102: Chromepet → Central, ₹20
- 103: Tambaram → T Nagar, ₹18
- 104: Velachery → Guindy, ₹10
- 105: Pallavaram → Broadway, ₹22
- 106: Thiruvanmiyur → Broadway, ₹20

## Sample buses

101A, 102A, 103A, 104A, 105A, and 106A are inserted by `sample-data.sql`.

## API list

### Authentication

- `POST /api/auth/register`
- `POST /api/auth/login`

### Passenger

- `GET /api/buses`
- `GET /api/buses/search?source=Tambaram&destination=Broadway&travelDate=2026-10-10`
- `GET /api/buses/{id}`
- `GET /api/routes/{id}`
- `GET /api/buses/{id}/booked-seats?travelDate=2026-10-10`
- `POST /api/bookings`
- `GET /api/bookings/user/{userId}`
- `GET /api/bookings/{id}`
- `PUT /api/bookings/{id}/cancel`

### Admin

- `GET /api/admin/dashboard`
- `GET /api/admin/buses`
- `POST /api/admin/buses`
- `PUT /api/admin/buses/{id}`
- `DELETE /api/admin/buses/{id}`
- `PUT /api/admin/buses/{id}/location`
- `PUT /api/admin/buses/{id}/status`
- `GET /api/admin/routes`
- `POST /api/admin/routes`
- `PUT /api/admin/routes/{id}`
- `DELETE /api/admin/routes/{id}`
- `GET /api/admin/bookings`
- `GET /api/admin/bookings/{id}`
- `PUT /api/admin/bookings/{id}/status`

## Postman examples

### 1. Register

`POST http://localhost:8080/api/auth/register`

```json
{
  "name":"Test Passenger",
  "email":"test@example.com",
  "password":"test123",
  "phone":"9876500000"
}
```

### 2. Login

`POST http://localhost:8080/api/auth/login`

```json
{"email":"arun@gmail.com","password":"arun123"}
```

### 3. Search buses

`GET http://localhost:8080/api/buses/search?source=Tambaram&destination=Broadway&travelDate=2026-10-10`

### 4. Get bus details

`GET http://localhost:8080/api/buses/1`

### 5. Get route details

`GET http://localhost:8080/api/routes/1`

### 6. Create booking

`POST http://localhost:8080/api/bookings`

```json
{
  "userId":1,
  "busId":1,
  "seatNumber":7,
  "travelDate":"2026-10-10",
  "source":"Tambaram",
  "destination":"Broadway"
}
```

The backend takes the actual source, destination and fare from the selected bus route rather than trusting the frontend values.

### 7. Passenger bookings

`GET http://localhost:8080/api/bookings/user/1`

### 8. Booking details

`GET http://localhost:8080/api/bookings/1`

### 9. Cancel booking

`PUT http://localhost:8080/api/bookings/1/cancel`

### 10. Admin login

`POST http://localhost:8080/api/auth/login`

```json
{"email":"admin@gmail.com","password":"admin123"}
```

### 11-21. Admin testing

Use the admin URLs listed above with the same JSON shapes as the corresponding DTOs. For bus creation:

```json
{
  "busNumber":"107A",
  "busName":"New City Bus",
  "routeId":1,
  "totalSeats":40,
  "availableSeats":40,
  "currentLocation":"Tambaram",
  "status":"Available",
  "departureTime":"12:00:00",
  "arrivalTime":"13:00:00"
}
```

For location:

```json
{"location":"Guindy"}
```

For status:

```json
{"status":"Running"}
```

For admin booking status:

```json
{"status":"Completed"}
```

## Database testing

Run:

```sql
SELECT * FROM users;
SELECT * FROM routes;
SELECT * FROM buses;
SELECT * FROM bookings;
```

The join, bus/route, and statistics queries are in `database/queries.sql`.

## Seat booking logic

1. Backend verifies the user and bus.
2. Backend rejects cancelled/completed buses.
3. Backend checks the seat number against total seats.
4. Backend checks the seat/date combination against existing non-cancelled bookings.
5. The route fare is read from the database.
6. The booking is saved as `Confirmed`.
7. Available seat count decreases.
8. The database unique constraint `(bus_id, seat_number, travel_date)` provides a second layer of duplicate-seat protection.
9. Cancelling a confirmed booking changes its status to `Cancelled` and restores one available seat.

## Simulated bus tracking

This project does **not** use GPS hardware, maps, WebSockets, or live vehicle telemetry. Admin users manually update `current_location` and `status`. Passengers can then view those database values. This is intentionally simulated tracking for a college mini project.

## Common errors

### Cannot connect to MySQL

- Start MySQL service.
- Check username/password in `application.properties`.
- Confirm database `bus_route_planner_db` exists.

### `YOUR_MYSQL_PASSWORD` error

Replace the placeholder with your local MySQL root password.

### CORS error

Run the frontend through Live Server on port 5500. If Live Server chooses another port, add that origin in `WebConfig.java`.

### 404 Bus/Booking/Route

Check that the ID exists in MySQL and that the backend is running on port 8080.

### Port 8080 already in use

Stop the process using port 8080 or change `server.port`, then update `API_BASE_URL` in `frontend/js/api.js`.

### Port 5500 already in use

Use the port shown by Live Server and update CORS accordingly.

## Security note

This is an educational mini project. It deliberately uses simple login without JWT as requested. Passwords are stored as plain text in the sample database. A production application must hash passwords (for example with BCrypt), use proper authentication/authorization, protect admin endpoints, use HTTPS, validate ownership of passenger resources, and keep credentials outside source control.

## Project structure

```text
PublicBusRouteTicketPlanner/
├── frontend/
├── css/
├── js/
├── assets/
├── backend/
│   ├── pom.xml
│   └── src/main/java/com/bus/planner/
├── database/
└── README.md
```
