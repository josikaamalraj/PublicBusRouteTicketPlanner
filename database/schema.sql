CREATE DATABASE IF NOT EXISTS bus_route_planner_db;
USE bus_route_planner_db;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    role ENUM('passenger', 'admin') NOT NULL DEFAULT 'passenger',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS routes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    route_name VARCHAR(150) NOT NULL,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    fare DECIMAL(10,2) NOT NULL,
    estimated_duration VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS buses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bus_number VARCHAR(50) NOT NULL UNIQUE,
    bus_name VARCHAR(100),
    route_id INT NOT NULL,
    total_seats INT NOT NULL,
    available_seats INT NOT NULL,
    current_location VARCHAR(150),
    status ENUM('Available','Running','Delayed','Completed','Cancelled') DEFAULT 'Available',
    departure_time TIME,
    arrival_time TIME,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bus_route FOREIGN KEY (route_id) REFERENCES routes(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    bus_id INT NOT NULL,
    seat_number INT NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    travel_date DATE NOT NULL,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    fare DECIMAL(10,2) NOT NULL,
    status ENUM('Confirmed','Cancelled','Completed') DEFAULT 'Confirmed',
    CONSTRAINT fk_booking_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_booking_bus FOREIGN KEY (bus_id) REFERENCES buses(id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE KEY uk_booking_seat_date (bus_id, seat_number, travel_date)
);
