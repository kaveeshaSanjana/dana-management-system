-- 1. First, insert completely independent tables (no foreign keys)
-- ========================================================

-- Insert Villages (base table - no dependencies)
INSERT INTO villages (id, name, province, district, country, postal_code) VALUES
(1, 'Maligawatta', 'Western', 'Colombo', 'Sri Lanka', '01000'),
(2, 'Dematagoda', 'Western', 'Colombo', 'Sri Lanka', '01000'),
(3, 'Maradana', 'Western', 'Colombo', 'Sri Lanka', '01000'),
(4, 'Grandpass', 'Western', 'Colombo', 'Sri Lanka', '01400'),
(5, 'Mattakkuliya', 'Western', 'Colombo', 'Sri Lanka', '01500');

-- Insert Temples (base table - no dependencies)
INSERT INTO temple (id, name, address, contact_number, email, website) VALUES
(1, 'Sri Vajiraramaya', 'Maligawatta Temple Road', '0112695161', 'vajiraramaya@temple.lk', 'www.vajiraramaya.lk'),
(2, 'Gangaramaya', 'Dematagoda Temple Road', '0112435127', 'gangaramaya@temple.lk', 'www.gangaramaya.lk'),
(3, 'Dipaduttaramaya', 'Maradana Temple Road', '0112691378', 'dipaduttaramaya@temple.lk', 'www.dipaduttaramaya.lk'),
(4, 'Siri Sudassanaramaya', 'Grandpass Road', '0112412567', 'sudassanaramaya@temple.lk', 'www.sudassanaramaya.lk'),
(5, 'Jayasekaramaya', 'Mattakkuliya Road', '0112529876', 'jayasekaramaya@temple.lk', 'www.jayasekaramaya.lk');

-- Insert Dana Types (base table - no dependencies)
INSERT INTO dana (id, name, description, time) VALUES
(1, 'Morning Heel Dana', 'Early morning rice offering', 0),
(2, 'Buddha Pooja', 'Midday alms offering', 1),
(3, 'Evening Gilanpasa', 'Evening refreshments', 2),
(4, 'Katina Cheevara', 'Robe offering ceremony', 1),
(5, 'Sangika Dana', 'Special community alms', 1);

-- 2. Insert tables with single foreign key dependency
-- ========================================================

-- Insert Temple-Village relationships (depends on: temples, villages)
INSERT INTO temple_village (temple_id, village_id) VALUES
(1, 1), (1, 2), -- Vajiraramaya serves Maligawatta and Dematagoda
(2, 2), (2, 3), -- Gangaramaya serves Dematagoda and Maradana
(3, 3), (3, 4), -- Dipaduttaramaya serves Maradana and Grandpass
(4, 4), (4, 5), -- Sudassanaramaya serves Grandpass and Mattakkuliya
(5, 5), (5, 1); -- Jayasekaramaya serves Mattakkuliya and Maligawatta

-- Insert Temple Dana (depends on: temples, dana)
INSERT INTO temple_dana (temple_id, dana_id, min_number_of_families) VALUES
(1, 1, 5), -- Morning Heel Dana at Vajiraramaya
(1, 2, 3), -- Buddha Pooja at Vajiraramaya
(2, 2, 4), -- Buddha Pooja at Gangaramaya
(2, 3, 2), -- Evening Gilanpasa at Gangaramaya
(3, 1, 3), -- Morning Heel Dana at Dipaduttaramaya
(3, 4, 6); -- Katina Cheevara at Dipaduttaramaya

-- Insert Families (depends on: villages)
INSERT INTO family (id, family_name, address, telephone) VALUES
(1, 'Perera Family', 'No 123, Temple Road, Maligawatta', '0112695161'),
(2, 'Silva Family', 'No 456, Lake Road, Dematagoda', '0112435127'),
(3, 'Fernando Family', 'No 789, Station Road, Maradana', '0112691378'),
(4, 'Gunasekara Family', 'No 45, Main Street, Grandpass', '0112412567'),
(5, 'Rajapaksa Family', 'No 67, Church Road, Mattakkuliya', '0112529876');

-- Insert HeadMonks (depends on: temples)
INSERT INTO head_monks (id, monk_name, email, password, phone_number, temple_id) VALUES
(1, 'Ven. Samitha Thero', 'samitha@temple.lk', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '0712345678', 1),
(2, 'Ven. Sudantha Thero', 'sudantha@temple.lk', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '0723456789', 2),
(3, 'Ven. Chandima Thero', 'chandima@temple.lk', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '0734567890', 3);

-- Insert Helpers (depends on: temples)
INSERT INTO helper (id, name, email, password, phone_number, temple_id) VALUES
(1, 'Kumara', 'kumara@helper.lk', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '0761234567', 1),
(2, 'Saman', 'saman@helper.lk', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '0762345678', 2),
(3, 'Nimal', 'nimal@helper.lk', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '0763456789', 3);

-- 3. Insert tables with multiple foreign key dependencies
-- ========================================================

-- Insert Members (depends on: families)
INSERT INTO member (id, name, email, password, phone_number, address, nic, dob) VALUES
(1, 'John Perera', 'john@gmail.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '0771234567', 'No 123, Temple Road', 199012345, '1990-01-01'),
(2, 'Mary Silva', 'mary@gmail.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '0772345678', 'No 456, Lake Road', 199123456, '1991-02-02'),
(3, 'Peter Fernando', 'peter@gmail.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '0773456789', 'No 789, Station Road', 199234567, '1992-03-03');

INSERT INTO temple_villages (temple_id, village_id) VALUES
-- Families connected to Vajiraramaya (Temple 1)
(1, 1), -- Perera family in Maligawatta
(1, 2), -- Silva family in Dematagoda
-- Families connected to Gangaramaya (Temple 2)
(2, 2), -- Silva family in Dematagoda
(2, 3), -- Fernando family in Maradana

-- Families connected to Dipaduttaramaya (Temple 3)
(3, 3), -- Fernando family in Maradana
(3, 4); -- Gunasekara family in Grandpass

-- Insert Village-Family relationships (depends on: temple_village, families)

INSERT INTO village_family (temple_id, village_id, family_id) VALUES
-- Families connected to Vajiraramaya (Temple 1)
(1, 1, 1), -- Perera family in Maligawatta
(1, 2, 2), -- Silva family in Dematagoda
-- Families connected to Gangaramaya (Temple 2)
(2, 2, 2), -- Silva family in Dematagoda
(2, 3, 3), -- Fernando family in Maradana

-- Families connected to Dipaduttaramaya (Temple 3)
(3, 3, 3), -- Fernando family in Maradana
(3, 4, 4); -- Gunasekara family in Grandpass

-- 4. Finally, insert assignment data (depends on all previous relationships)
-- ========================================================

-- Insert Temple Dana Assignments (depends on: temple_dana, members)
INSERT INTO temple_dana_assignments (id, family_id, temple_id, dana_id, date,confirmation_date) VALUES
(1, 1, 1, 1, '2025-06-20', '2025-06-20'), -- John for Morning Heel Dana
(2, 2, 2, 2, '2025-06-21', '2025-06-21'), -- Mary for Buddha Pooja
(3, 3, 3, 1, '2025-06-22', '2025-06-22'); -- Peter for Morning Heel Dana

-- Insert Family Members (after families are created)
INSERT INTO member_family(family_id, member_id) VALUES
-- Perera Family Members
(1, 1), -- John Perera
(1, 2), -- Michael Perera

-- Silva Family Members
(2, 2), -- Mary Silva
(2, 1), -- James Silva
(2, 3), -- Emma Silva

-- Fernando Family Members
(3, 1), -- Peter Fernando
(3, 2), -- William Fernando
(3, 3); -- Robert Rajapaksa
