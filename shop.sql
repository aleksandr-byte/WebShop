USE webshop;
DROP TABLE IF exists Smartphone;
DROP TABLE IF exists Computer;
DROP TABLE IF exists Characteristics;
DROP TABLE IF exists roles;
DROP TABLE IF exists product_orders;
DROP TABLE IF exists orders;
DROP TABLE IF exists grade;
DROP TABLE IF exists users;
DROP TABLE IF exists Products;
DROP TABLE IF exists product_additional_info;
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
CREATE TABLE roles(

-- id has the INTEGER type (other name is INT), it is the primary key
                          role_id BIGINT NOT NULL,

-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE)
                          role VARCHAR(5)
);

INSERT INTO roles (role_id, role) VALUES (1, 'ADMIN');
INSERT INTO roles (role_id, role) VALUES (2, 'USER');

CREATE TABLE users(
                      id BIGINT NOT NULL auto_increment PRIMARY KEY,
                      username VARCHAR(20) NOT NULL UNIQUE,
                      email VARCHAR(40) NOT NULL,
                      active BIT NOT NULL,
                      password VARCHAR(100) NOT NULL,
					  role_id BIGINT NOT NULL REFERENCES roles(role_id)
);

INSERT INTO users (username, email, active, password, role_id) VALUES ('admin', 'admin@webshop.ua', 1, 'admin', 1);

CREATE TABLE Categories(
                           categories_id BIGINT NOT NULL auto_increment PRIMARY KEY,
                           category_name VARCHAR(20) NOT NULL
);

CREATE TABLE manufacturers(
                              manufacturers_id BIGINT NOT NULL auto_increment PRIMARY KEY,
                              manufacturer_name VARCHAR(20) NOT NULL
);

CREATE TABLE Products(
                         product_id BIGINT NOT NULL auto_increment PRIMARY KEY,
                         name VARCHAR(20) NOT NULL UNIQUE,
                         price INTEGER NOT NULL,
                         rating INTEGER NOT NULL,
                         description VARCHAR(1000),
                         image_name VARCHAR(100),
                         category_id BIGINT,
                         manufacturer_id BIGINT,
                         FOREIGN KEY (category_id)  REFERENCES Categories (categories_id) ON DELETE restrict ON UPDATE restrict,
                         FOREIGN KEY (manufacturer_id)  REFERENCES manufacturers (manufacturers_id) ON DELETE restrict ON UPDATE restrict
);

CREATE TABLE orders(
                       orders_id VARCHAR(200) NOT NULL PRIMARY KEY,
                       time_of_order_creation DATETIME NOT NULL,
                       status ENUM('accepted', 'confirmed', 'generated', 'sent', 'completed', 'canceled'),
                       status_details VARCHAR (200),
                       address VARCHAR (200) NOT NULL,
                       payment_type VARCHAR (200) NOT NULL,
                       card_number VARCHAR (40) NOT NULL,
                       user_id BIGINT NOT NULL,
                       FOREIGN KEY (user_id)  REFERENCES users (id) ON DELETE CASCADE ON UPDATE cascade
);

CREATE TABLE product_orders(
							   product_orders_id BIGINT NOT NULL auto_increment PRIMARY KEY,
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

CREATE TABLE cpus(
	cpu_id INTEGER NOT NULL PRIMARY KEY,
    cpu_value VARCHAR(50) NOT NULL
);

CREATE TABLE Characteristics(
	product_id BIGINT NOT NULL PRIMARY KEY,
    diagonal_id INTEGER DEFAULT 0,
    resolution_id INTEGER DEFAULT 0,
    flash_memory_size_id INTEGER DEFAULT 0,
    battery_capacity_id INTEGER DEFAULT 0,
    color_id INTEGER DEFAULT 0,
    display_type_id INTEGER DEFAULT 0,
    memory_size_id INTEGER DEFAULT 0,
    cpu_id INTEGER NOT NULL DEFAULT 0,
	FOREIGN KEY (diagonal_id)  REFERENCES diagonals (diagonal_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (resolution_id)  REFERENCES resolutions (resolution_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (flash_memory_size_id)  REFERENCES flash_memory_sizes (flash_memory_size_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (battery_capacity_id)  REFERENCES battery_capacities (battery_capacity_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (color_id)  REFERENCES colors (color_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (display_type_id)  REFERENCES display_types (display_type_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (memory_size_id)  REFERENCES memory_sizes (memory_size_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (cpu_id)  REFERENCES cpus (cpu_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

-- -----------------------------------------------------
-- Table `webshop`.`grade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshop`.`grade` (
  `product_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `grade` DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (`product_id`, `user_id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `grade_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `webshop`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `grade_ibfk_2`
    FOREIGN KEY (`product_id`)
    REFERENCES `webshop`.`products` (`product_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

insert into categories (category_name) values ("Computer");
insert into categories (category_name) values ("Smartwatch");
insert into categories (category_name) values ("Smartphone");

insert into manufacturers (manufacturer_name) values ("Apple");
insert into manufacturers (manufacturer_name) values ("Samsung");
insert into manufacturers (manufacturer_name) values ("Lenovo");

insert into diagonals (diagonal_id, diagonal_value) values (0, "");
insert into diagonals (diagonal_id, diagonal_value) values (1, "5 - 5.9");
insert into diagonals (diagonal_id, diagonal_value) values (2, "6 - 6.4");
insert into diagonals (diagonal_id, diagonal_value) values (3, "6.5 - 6.9");

insert into resolutions (resolution_id, resolution_value) values (0, "");
insert into resolutions (resolution_id, resolution_value) values (1, "3840x2160");
insert into resolutions (resolution_id, resolution_value) values (2, "2160x1080");
insert into resolutions (resolution_id, resolution_value) values (3, "1440x720");

insert into memory_sizes (memory_size_id, memory_size_value) values (0, "");
insert into memory_sizes (memory_size_id, memory_size_value) values (1, "2 Gb");
insert into memory_sizes (memory_size_id, memory_size_value) values (2, "4 Gb");
insert into memory_sizes (memory_size_id, memory_size_value) values (3, "8 Gb");

insert into flash_memory_sizes (flash_memory_size_id, flash_memory_size_value) values (0, "");
insert into flash_memory_sizes (flash_memory_size_id, flash_memory_size_value) values (1, "16 Gb");
insert into flash_memory_sizes (flash_memory_size_id, flash_memory_size_value) values (2, "32 Gb");
insert into flash_memory_sizes (flash_memory_size_id, flash_memory_size_value) values (3, "64 Gb");

insert into battery_capacities (battery_capacity_id, battery_capacity_value) values (0, "");
insert into battery_capacities (battery_capacity_id, battery_capacity_value) values (1, "3000");
insert into battery_capacities (battery_capacity_id, battery_capacity_value) values (2, "4000");
insert into battery_capacities (battery_capacity_id, battery_capacity_value) values (3, "5000");

insert into colors (color_id, color_value) values (0, "");
insert into colors (color_id, color_value) values (1, "Blue");
insert into colors (color_id, color_value) values (2, "Green");
insert into colors (color_id, color_value) values (3, "Black");

insert into display_types (display_type_id, display_type_value) values (0, "");
insert into display_types (display_type_id, display_type_value) values (1, "IPS");
insert into display_types (display_type_id, display_type_value) values (2, "PLS");
insert into display_types (display_type_id, display_type_value) values (3, "AMOLED");

insert into cpus (cpu_id, cpu_value) values (0, "");
insert into cpus (cpu_id, cpu_value) values (1, "AMD ryzen 5");
insert into cpus (cpu_id, cpu_value) values (2, "Intel core i5");
insert into cpus (cpu_id, cpu_value) values (3, "Snapdragon 865");

-- Smartphones
insert into Products (name, price, rating, description, image_name, category_id, manufacturer_id) values ("IPhone 11 Pro Max", 1000, 1,'It`s a revolution!', 'computer4.jpg', 3, 1);
insert into Products (name, price, rating, description, image_name, category_id, manufacturer_id) values ("Samsung s21", 300, 1, 'It`s a revolution!', 'computer4.jpg', 3, 2);
insert into Products (name, price, rating, description, image_name, category_id, manufacturer_id) values ("Samsung s20 Plus ", 400, 1, 'It`s a revolution!', 'computer4.jpg', 3, 2);
 insert into Characteristics (product_id, diagonal_id, resolution_id, flash_memory_size_id, battery_capacity_id, color_id, display_type_id, memory_size_id, cpu_id)
 values (1, 3, 1, 3, 3, 2, 3, 2, 3);
  insert into Characteristics (product_id, diagonal_id, resolution_id, flash_memory_size_id, battery_capacity_id, color_id, display_type_id, memory_size_id, cpu_id)
 values (2, 2, 2, 2, 2, 1, 1, 1, 3);
  insert into Characteristics (product_id, diagonal_id, resolution_id, flash_memory_size_id, battery_capacity_id, color_id, display_type_id, memory_size_id, cpu_id)
 values (3, 1, 3, 1, 1, 3, 1, 1, 3);

-- Computers
insert into Products (name, price, rating, description, image_name, category_id, manufacturer_id) values ("Macbook", 1500, 1, 'It`s a revolution!', 'computer4.jpg', 1, 1);
insert into Products (name, price, rating, description, image_name, category_id, manufacturer_id) values ("IMac Pro 2020", 2000, 1, 'It`s a revolution!', 'computer4.jpg', 1, 1);
insert into Products (name, price, rating, description, image_name, category_id, manufacturer_id) values ("Lenovo Think Pad", 3000, 1, 'It`s a revolution!', 'computer4.jpg', 1, 3);
  insert into Characteristics (product_id, flash_memory_size_id, color_id, memory_size_id, cpu_id)
 values (4, 3, 2, 2, 3);
  insert into Characteristics (product_id, flash_memory_size_id, color_id, memory_size_id, cpu_id)
 values (5, 3, 2, 2, 2);
  insert into Characteristics (product_id, flash_memory_size_id, color_id, memory_size_id, cpu_id)
 values (6, 3, 3, 3, 3);

-- Smart watches
insert into Products (name, price, rating, description, image_name, category_id, manufacturer_id) values ("Apple Watch Series 3", 600, 1, 'It`s a revolution!', 'computer4.jpg', 2, 1);
insert into Products (name, price, rating, description, image_name, category_id, manufacturer_id) values ("Samsung galaxy watch", 1100, 1, 'It`s a revolution!', 'computer4.jpg', 2, 2);
insert into Products (name, price, rating, description, image_name, category_id, manufacturer_id) values ("Samsung active", 1100, 1, 'It`s a revolution!', 'computer4.jpg', 2, 2);
insert into Products (name, price, rating, description, image_name, category_id, manufacturer_id) values ("Samsung gear s3", 500, 1, 'It`s a revolution!', 'computer4.jpg', 2, 2);
 insert into Characteristics (product_id, diagonal_id, resolution_id, flash_memory_size_id, battery_capacity_id, color_id)
 values (7, 3, 1, 3, 3, 2);
  insert into Characteristics (product_id, diagonal_id, resolution_id, flash_memory_size_id, battery_capacity_id, color_id)
 values (8, 2, 2, 2, 2, 1);
  insert into Characteristics (product_id, diagonal_id, resolution_id, flash_memory_size_id, battery_capacity_id, color_id)
 values (9, 1, 3, 1, 1, 3);
   insert into Characteristics (product_id, diagonal_id, resolution_id, flash_memory_size_id, battery_capacity_id, color_id)
 values (10, 2, 3, 1, 1, 3);
