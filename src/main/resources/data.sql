-- 插入机场数据
INSERT IGNORE INTO airport (code, name, city, country) VALUES
('PEK', 'Beijing Capital International Airport', 'Beijing', 'China'),
('PVG', 'Shanghai Pudong International Airport', 'Shanghai', 'China'),
('LAX', 'Los Angeles International Airport', 'Los Angeles', 'USA');

-- 插入用户数据（密码为 BCrypt 加密的“password”）
INSERT IGNORE INTO user (first_name, password, last_name, email, phone, country) VALUES
('alice', '$2a$10$7d3F1XSlm2b5v5MxJQhF7uS9Gv8OuZa6XK/wKwMvqHdOQfJr5JZle', 'Zhang', 'alice@example.com', '1234567890', 'China'),
('bob', '$2a$10$VfHh3fWQk9kIQrTg/sS4D.vLMFqMyNq7LYjcWVKaeo0If92rfOJrC', 'Wang', 'bob@example.com', '0987654321', 'USA');

-- 插入航班数据
INSERT IGNORE INTO flight (flight_number, departure_airport_id, destination_airport_id, departure_time, arrival_time, price) VALUES
('CA123', 1, 2, '2025-06-01 08:00:00', '2025-06-01 10:30:00', 980.00),
('MU456', 2, 3, '2025-06-02 09:00:00', '2025-06-02 13:00:00', 1580.00);

-- 插入订单数据（订单总价 = 航班价格 * 乘客数，这里只示例 1 人）
INSERT INTO booking (user_id, flight_id, booking_time, status, total_price, reference) VALUES
(1, 1, '2025-05-01 12:00:00', 'BOOKED', 980.00, 'REF123456'),
(2, 2, '2025-05-02 15:00:00', 'BOOKED', 1580.00, 'REF654321');

-- 插入乘客数据
INSERT IGNORE INTO passenger (first_name, last_name, booking_id, email) VALUES
--('Alice', 'Zhang', 1, 'alice.passenger@example.com'),
('Bob', 'Wang', 2, 'bob.passenger@example.com');

-- 插入座位数据
--INSERT INTO seat (flight_id, seat_number, class, is_available) VALUES
--(1, '1A', 'Business', TRUE),
--(1, '12C', 'Economy', TRUE),
--(2, '2B', 'Business', TRUE),
--(2, '21F', 'Economy', TRUE);
