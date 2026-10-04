USE bus_route_planner_db;
SELECT * FROM users;
SELECT * FROM routes;
SELECT * FROM buses;
SELECT * FROM bookings;

SELECT b.id AS booking_id,u.name AS passenger_name,u.email,bs.bus_number,r.route_name,r.source,r.destination,b.seat_number,b.travel_date,b.fare,b.status
FROM bookings b JOIN users u ON b.user_id=u.id JOIN buses bs ON b.bus_id=bs.id JOIN routes r ON bs.route_id=r.id;

SELECT bs.id AS bus_id,bs.bus_number,bs.bus_name,r.route_name,r.source,r.destination,r.fare,bs.total_seats,bs.available_seats,bs.current_location,bs.status
FROM buses bs JOIN routes r ON bs.route_id=r.id;

SELECT COUNT(*) AS total_buses FROM buses;
SELECT status,COUNT(*) AS total FROM buses GROUP BY status;
SELECT COUNT(*) AS total_routes FROM routes;
SELECT COUNT(*) AS total_bookings FROM bookings;
SELECT status,COUNT(*) AS total FROM bookings GROUP BY status;
SELECT r.route_name,COUNT(b.id) AS total_bookings FROM bookings b JOIN buses bs ON b.bus_id=bs.id JOIN routes r ON bs.route_id=r.id GROUP BY r.route_name;
