-- Insert Countries
INSERT INTO country (id, name, continent, currency) VALUES
   (1, 'United States', 'North America', 'USD'),
   (2, 'Germany', 'Europe', 'EUR'),
   (3, 'Japan', 'Asia', 'YEN'),
   (4, 'France', 'Europe', 'EUR'),
   (5, 'Italy', 'Europe', 'EUR');

-- Insert Hosts
INSERT INTO host (id, name, surname, country_id) VALUES
   (1, 'John', 'Smith', 1),
   (2, 'Anna', 'Müller', 2),
   (3, 'Takeshi', 'Yamamoto', 3),
   (4, 'Marie', 'Dubois', 4),
   (5, 'Giovanni', 'Rossi', 5);

-- Insert Bookings
INSERT INTO booking (name, category, host_id, num_of_rooms, booked, price) VALUES
 ('New York Apartment', 'APARTMENT', 1, 2, false, 150.00),
 ('Berlin Loft', 'FLAT', 2, 1, true, 95.50),
 ('Tokyo Capsule', 'ROOM', 3, 1, false, 45.00),
 ('Paris Studio', 'APARTMENT', 4, 1, true, 120.75),
 ('Rome Villa', 'HOUSE', 5, 4, false, 300.00),
 ('Hamburg Hotel', 'HOTEL', 2, 3, false, 180.25),
 ('Kyoto Ryokan', 'MOTEL', 3, 2, true, 85.00);