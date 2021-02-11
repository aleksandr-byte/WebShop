USE webshop;
DROP TABLE IF exists user_role;
DROP TABLE IF exists product_orders;
DROP TABLE IF exists orders;
DROP TABLE IF exists users;
DROP TABLE IF exists products;
DROP TABLE IF exists manufacturers;
DROP TABLE IF exists categories;
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

CREATE TABLE diagonal(
	diagonal_id INTEGER NOT NULL PRIMARY KEY,
    diagonal_value VARCHAR(9) NOT NULL
);

insert into categories (category_name) values ("Computer");
insert into categories (category_name) values ("Smartwatches");
insert into categories (category_name) values ("Mobilephone");

insert into manufacturers (manufacturer_name) values ("Apple");
insert into manufacturers (manufacturer_name) values ("Samsung");
insert into manufacturers (manufacturer_name) values ("Lenovo");

insert into diagonal (diagonal_id, diagonal_value) values (1, "5 - 5.9");
insert into diagonal (diagonal_id, diagonal_value) values (2, "6 - 6.4");
insert into diagonal (diagonal_id, diagonal_value) values (3, "6.5 - 6.9");

insert into products (name, price, description, image_name, rating, category_id, manufacturer_id) values ("Macbook", 1500, 'It`s a revolution!', 'computer4.jpg', 2, 1, 1);
insert into products (name, price, description, image_name, rating, category_id, manufacturer_id) values ("Apple Watch", 600, 'It`s a revolution!', 'computer4.jpg', 2, 2, 1);
insert into products (name, price, description, image_name, rating, category_id, manufacturer_id) values ("IPhone", 1000, 'It`s a revolution!', 'computer4.jpg', 2, 3, 1);

insert into products (name, price, description, image_name, rating, category_id, manufacturer_id) values ("Samsung S20", 1100, 'It`s a revolution!', 'computer4.', 2, 2, 2);
insert into products (name, price, description, image_name, rating, category_id, manufacturer_id) values ("Lenovo g400", 300, 'It`s a revolution!', 'computer4.', 2, 3, 3);
insert into products (name, price, description, image_name, rating, category_id, manufacturer_id) values ("Samsung S20 Plus", 1100, 'It`s a revolution!', 'computer4.', 2, 2, 2);
insert into products (name, price, description, image_name, rating, category_id, manufacturer_id) values ("One Plus 10", 400, 'It`s a revolution!', 'computer4.jpg', 2, 3, 3);
insert into products (name, price, description, image_name, rating, category_id, manufacturer_id) values ("Samsung Note 20", 500, 'It`s a revolution!', 'computer4.jpg', 2, 2, 2);
insert into products (name, price, description, image_name, rating, category_id, manufacturer_id) values ("IMac Pro 2020", 2000, 'It`s a revolution!', 'computer4.jpg', 2, 1, 3);
insert into products (name, price, description, image_name, rating, category_id, manufacturer_id) values ("IMac Pro 2021", 3000, 'It`s a revolution!', 'computer4.jpg', 2, 1, 3);