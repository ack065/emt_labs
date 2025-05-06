-- Create enum types first
CREATE TYPE category_enum AS ENUM ('ROOM', 'HOUSE', 'FLAT', 'APARTMENT', 'HOTEL', 'MOTEL');
CREATE TYPE currency_enum AS ENUM ('USD', 'EUR', 'YEN');

-- Create Country table
CREATE TABLE country (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         continent VARCHAR(255) NOT NULL,
                         currency currency_enum NOT NULL
);

-- Create Host table
CREATE TABLE host (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      surname VARCHAR(255) NOT NULL,
                      country_id BIGINT NOT NULL,
                      CONSTRAINT fk_host_country
                          FOREIGN KEY (country_id)
                              REFERENCES country(id)
                              ON DELETE CASCADE
);

-- Create Booking table
CREATE TABLE booking (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         category category_enum NOT NULL,
                         host_id BIGINT NOT NULL,
                         num_of_rooms INTEGER NOT NULL,
                         booked BOOLEAN NOT NULL DEFAULT false,
                         price DECIMAL(10,2) NOT NULL,
                         CONSTRAINT fk_booking_host
                             FOREIGN KEY (host_id)
                                 REFERENCES host(id)
                                 ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX idx_host_country ON host(country_id);
CREATE INDEX idx_booking_host ON booking(host_id);
CREATE INDEX idx_booking_category ON booking(category);