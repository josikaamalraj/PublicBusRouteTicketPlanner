USE bus_route_planner_db;
INSERT INTO users (name,email,password,phone,role) VALUES
('Arun Kumar','arun@gmail.com','arun123','9876543210','passenger'),
('Priya','priya@gmail.com','priya123','9876543211','passenger'),
('Rahul','rahul@gmail.com','rahul123','9876543212','passenger'),
('Admin','admin@gmail.com','admin123','9876543299','admin');

INSERT INTO routes (route_name,source,destination,fare,estimated_duration) VALUES
('Tambaram-Broadway','Tambaram','Broadway',25.00,'60 minutes'),
('Chromepet-Central','Chromepet','Central',20.00,'50 minutes'),
('Tambaram-T Nagar','Tambaram','T Nagar',18.00,'45 minutes'),
('Velachery-Guindy','Velachery','Guindy',10.00,'25 minutes'),
('Pallavaram-Broadway','Pallavaram','Broadway',22.00,'55 minutes'),
('Thiruvanmiyur-Broadway','Thiruvanmiyur','Broadway',20.00,'45 minutes');

INSERT INTO buses (bus_number,bus_name,route_id,total_seats,available_seats,current_location,status,departure_time,arrival_time) VALUES
('101A','MTC Express',1,40,40,'Tambaram','Available','08:00:00','09:00:00'),
('102A','City Bus',2,40,40,'Chromepet','Running','09:00:00','09:50:00'),
('103A','Metro Connect',3,40,40,'Tambaram','Available','10:00:00','10:45:00'),
('104A','City Express',4,30,30,'Velachery','Available','08:30:00','08:55:00'),
('105A','MTC Express',5,40,40,'Pallavaram','Running','07:30:00','08:25:00'),
('106A','Broadway Connect',6,40,40,'Thiruvanmiyur','Available','11:00:00','11:45:00');

INSERT INTO bookings (user_id,bus_id,seat_number,travel_date,source,destination,fare,status) VALUES
(1,1,5,'2026-10-10','Tambaram','Broadway',25.00,'Confirmed'),
(2,2,10,'2026-10-10','Chromepet','Central',20.00,'Confirmed');
UPDATE buses SET available_seats=39 WHERE id=1;
UPDATE buses SET available_seats=39 WHERE id=2;
