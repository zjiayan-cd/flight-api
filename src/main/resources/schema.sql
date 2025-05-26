-- 创建机场表
CREATE TABLE IF NOT EXISTS airport (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  code VARCHAR(10) NOT NULL UNIQUE,
  name VARCHAR(100) NOT NULL,
  city VARCHAR(100) NOT NULL,
  country VARCHAR(100) NOT NULL
);

-- 创建用户表
-- 使用 BCryptPasswordEncoder 加密,new BCryptPasswordEncoder().encode("password")
CREATE TABLE IF NOT EXISTS user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL, 
  last_name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  phone VARCHAR(20),
  country VARCHAR(100)
);

-- 创建航班表
CREATE TABLE IF NOT EXISTS flight (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  flight_number VARCHAR(20) NOT NULL UNIQUE,
  departure_airport_id BIGINT NOT NULL,
  destination_airport_id BIGINT NOT NULL,
  departure_time DATETIME NOT NULL,
  arrival_time DATETIME NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  CONSTRAINT fk_dep_airport FOREIGN KEY (departure_airport_id) REFERENCES airport(id),
  CONSTRAINT fk_arr_airport FOREIGN KEY (destination_airport_id) REFERENCES airport(id)
);


-- 预订订单表
CREATE TABLE IF NOT EXISTS booking (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,          -- 下单用户（乘客或代理人）
  flight_id BIGINT NOT NULL,
--  passenger_id BIGINT NOT NULL,     -- 乘客信息（乘客可能和用户不同）
--  seat_id BIGINT NOT NULL,
  booking_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  status VARCHAR(20) DEFAULT 'BOOKED', -- 订单状态: BOOKED, CANCELLED, COMPLETED
  total_price DECIMAL(10,2),
  reference VARCHAR(20),
  CONSTRAINT fk_booking_user FOREIGN KEY (user_id) REFERENCES user(id),
  CONSTRAINT fk_booking_flight FOREIGN KEY (flight_id) REFERENCES flight(id)
--  CONSTRAINT fk_booking_passenger FOREIGN KEY (passenger_id) REFERENCES passenger(id),
--  CONSTRAINT fk_booking_seat FOREIGN KEY (seat_id) REFERENCES seat(id)
);

-- 乘客表
CREATE TABLE IF NOT EXISTS passenger (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  booking_id BIGINT,
--  id_type VARCHAR(50),       -- 证件类型：身份证、护照等
--  id_number VARCHAR(100),    -- 证件号码
--  phone VARCHAR(20),
  email VARCHAR(100),
--  CONSTRAINT fk_passenger_user FOREIGN KEY (user_id) REFERENCES user(id)
  CONSTRAINT fk_booking FOREIGN KEY (booking_id) REFERENCES booking(id)
);


-- 创建座位表
--CREATE TABLE seat (
--  id BIGINT PRIMARY KEY AUTO_INCREMENT,
--  flight_id BIGINT NOT NULL,
--  seat_number VARCHAR(10) NOT NULL,
--  class VARCHAR(20),
--  is_available BOOLEAN DEFAULT TRUE,_
