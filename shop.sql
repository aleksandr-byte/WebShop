USE webshop;
DROP TABLE IF exists user_role;
DROP TABLE IF exists product_orders;
DROP TABLE IF exists orders;
DROP TABLE IF exists users;
DROP TABLE IF exists products;
DROP TABLE IF exists manufacturers;
DROP TABLE IF exists categories;
DROP TABLE IF exists diagonals;
DROP TABLE IF exists resolutions;
DROP TABLE IF exists memory_sizes;
DROP TABLE IF exists flash_memory_sizes;
DROP TABLE IF exists battery_capacities;
DROP TABLE IF exists colors;
DROP TABLE IF exists display_types;
DROP TABLE IF exists capacities;
DROP TABLE IF exists cpus;
-- --------------------------------------------------------------
-- ROLES
-- users roles
-- --------------------------------------------------------------
CREATE TABLE user_role(

-- id has the INTEGER type (other name is INT), it is the primary key
                          role_id BIGINT NOT NULL,

-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE)
                          roles VARCHAR(255)
);

CREATE TABLE users(

                      id BIGINT NOT NULL auto_increment PRIMARY KEY,
                      username VARCHAR(20) NOT NULL UNIQUE,
                      email VARCHAR(40) NOT NULL,
                      active BIT NOT NULL,
-- not null string columns
                      password VARCHAR(100) NOT NULL
);

CREATE TABLE categories(
                           categories_id BIGINT NOT NULL auto_increment PRIMARY KEY,
                           category_name VARCHAR(20) NOT NULL
);

CREATE TABLE manufacturers(
                              manufacturers_id BIGINT NOT NULL auto_increment PRIMARY KEY,
                              manufacturer_name VARCHAR(20) NOT NULL
);

CREATE TABLE products(
                         product_id BIGINT NOT NULL auto_increment PRIMARY KEY,
                         name VARCHAR(20) NOT NULL UNIQUE,
                         price INTEGER NOT NULL,
                         description VARCHAR(1000),
                         image_name VARCHAR(100),
                         category_id BIGINT,
                         manufacturer_id BIGINT,
                         FOREIGN KEY (category_id)  REFERENCES categories (categories_id) ON DELETE CASCADE ON UPDATE cascade,
                         FOREIGN KEY (manufacturer_id)  REFERENCES manufacturers (manufacturers_id) ON DELETE CASCADE ON UPDATE cascade
);

CREATE TABLE orders(
                       orders_id VARCHAR(200) NOT NULL PRIMARY KEY,
                       time_of_order_creation DATETIME NOT NULL,
                       status ENUM('accepted', 'confirmed', 'generated', 'sent', 'completed', 'canceled'),
                       status_details VARCHAR (200),
                       address VARCHAR (200) NOT NULL,
                       payment_type VARCHAR (200) NOT NULL,
                       cart_number VARCHAR (40) NOT NULL,
                       user_id BIGINT NOT NULL,
                       FOREIGN KEY (user_id)  REFERENCES users (id) ON DELETE CASCADE ON UPDATE cascade
);

CREATE TABLE product_orders(
                               orders_id VARCHAR(200) NOT NULL,
                               product_id BIGINT NOT NULL,
                               total_price DECIMAL NOT NULL,
                               quantity INTEGER NOT NULL,
                               FOREIGN KEY (orders_id)  REFERENCES orders (orders_id) ON DELETE CASCADE ON UPDATE cascade,
                               FOREIGN KEY (product_id)  REFERENCES products (product_id) ON DELETE CASCADE ON UPDATE cascade
);

CREATE TABLE diagonals(
	diagonal_id INTEGER NOT NULL PRIMARY KEY,
    diagonal_value VARCHAR(9) NOT NULL
);

CREATE TABLE resolutions(
	resolution_id INTEGER NOT NULL PRIMARY KEY,
    resolution_value VARCHAR(9) NOT NULL
);

CREATE TABLE memory_sizes(
	memory_size_id INTEGER NOT NULL PRIMARY KEY,
    memory_size_value VARCHAR(10) NOT NULL
);

CREATE TABLE flash_memory_sizes(
	flash_memory_size_id INTEGER NOT NULL PRIMARY KEY,
    flash_memory_size_value VARCHAR(10) NOT NULL
);

CREATE TABLE battery_capacities(
	battery_capacity_id INTEGER NOT NULL PRIMARY KEY,
    battery_capacity_value VARCHAR(10) NOT NULL
);

CREATE TABLE colors(
	color_id INTEGER NOT NULL PRIMARY KEY,
    color_value VARCHAR(20) NOT NULL
);

CREATE TABLE display_types(
	display_type_id INTEGER NOT NULL PRIMARY KEY,
    display_type_value VARCHAR(20) NOT NULL
);

CREATE TABLE capacities(
	capacity_id INTEGER NOT NULL PRIMARY KEY,
    capacity_value VARCHAR(20) NOT NULL
);

CREATE TABLE cpus(
	cpu_id INTEGER NOT NULL PRIMARY KEY,
    cpu_value VARCHAR(50) NOT NULL
);

insert into categories (category_name) values ("computers");
insert into categories (category_name) values ("smartwatches");
insert into categories (category_name) values ("smartphones");

insert into manufacturers (manufacturer_name) values ("Apple");
insert into manufacturers (manufacturer_name) values ("Samsung");
insert into manufacturers (manufacturer_name) values ("Lenovo");

insert into diagonals (diagonal_id, diagonal_value) values (1, "5 - 5.9");
insert into diagonals (diagonal_id, diagonal_value) values (2, "6 - 6.4");
insert into diagonals (diagonal_id, diagonal_value) values (3, "6.5 - 6.9");

insert into resolutions (resolution_id, resolution_value) values (1, "3840x2160");
insert into resolutions (resolution_id, resolution_value) values (2, "2160x1080");
insert into resolutions (resolution_id, resolution_value) values (3, "1440x720");

insert into memory_sizes (memory_size_id, memory_size_value) values (1, "2 Gb");
insert into memory_sizes (memory_size_id, memory_size_value) values (2, "3 Gb");
insert into memory_sizes (memory_size_id, memory_size_value) values (3, "4 Gb");

insert into flash_memory_sizes (flash_memory_size_id, flash_memory_size_value) values (1, "16 Gb");
insert into flash_memory_sizes (flash_memory_size_id, flash_memory_size_value) values (2, "32 Gb");
insert into flash_memory_sizes (flash_memory_size_id, flash_memory_size_value) values (3, "64 Gb");

insert into battery_capacities (battery_capacity_id, battery_capacity_value) values (1, "3000");
insert into battery_capacities (battery_capacity_id, battery_capacity_value) values (2, "4000");
insert into battery_capacities (battery_capacity_id, battery_capacity_value) values (3, "5000");

insert into colors (color_id, color_value) values (1, "Blue");
insert into colors (color_id, color_value) values (2, "Green");
insert into colors (color_id, color_value) values (3, "Black");

insert into display_types (display_type_id, display_type_value) values (1, "IPS");
insert into display_types (display_type_id, display_type_value) values (2, "PLS");
insert into display_types (display_type_id, display_type_value) values (3, "AMOLED");

insert into capacities (capacity_id, capacity_value) values (1, "128");
insert into capacities (capacity_id, capacity_value) values (2, "256");
insert into capacities (capacity_id, capacity_value) values (3, "512");

insert into cpus (cpu_id, cpu_value) values (1, "AMD ryzen 5");
insert into cpus (cpu_id, cpu_value) values (2, "Intel core i5");
insert into cpus (cpu_id, cpu_value) values (3, "Snapdragon 865");

insert into products (name, price, description, image_name, category_id, manufacturer_id) values ("Macbook", 1500, 'It`s a revolution!', 'computer4.jpg', 1, 1);
insert into products (name, price, description, image_name, category_id, manufacturer_id) values ("Apple Watch", 600, 'It`s a revolution!', 'computer4.jpg', 2, 1);
insert into products (name, price, description, image_name, category_id, manufacturer_id) values ("IPhone", 1000, 'It`s a revolution!', 'computer4.jpg', 3, 1);

insert into products (name, price, description, image_name, category_id, manufacturer_id) values ("Samsung S20", 1100, 'It`s a revolution!', 'computer4.jpg', 2, 2);
insert into products (name, price, description, image_name, category_id, manufacturer_id) values ("Lenovo g400", 300, 'It`s a revolution!', 'computer4.jpg', 3, 3);
insert into products (name, price, description, image_name, category_id, manufacturer_id) values ("Samsung S20 Plus", 1100, 'It`s a revolution!', 'computer4.jpg', 2, 2);
insert into products (name, price, description, image_name, category_id, manufacturer_id) values ("One Plus 10", 400, 'It`s a revolution!', 'computer4.jpg', 3, 3);
insert into products (name, price, description, image_name, category_id, manufacturer_id) values ("Samsung Note 20", 500, 'It`s a revolution!', 'computer4.jpg', 2, 2);
insert into products (name, price, description, image_name, category_id, manufacturer_id) values ("IMac Pro 2020", 2000, 'It`s a revolution!', 'computer4.jpg', 1, 3);
insert into products (name, price, description, image_name, category_id, manufacturer_id) values ("IMac Pro 2021", 3000, 'It`s a revolution!', 'computer4.jpg', 1, 3);