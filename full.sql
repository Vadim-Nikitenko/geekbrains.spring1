DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES
('Ivan'),
('Nikita'),
('Konstantin'),
('Igor'),
('Vadim'),
('Nastya'),
('Alena');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, name VARCHAR(255), price INTEGER);
INSERT INTO products (name, price) VALUES
('Iphone X', 50000),
('Honor P20', 30000),
('Samsung Galaxy J5', 13000),
('Nokia 3110', 5000),
('Xiaomi Redmi', 18000),
('Iphone Xs Max', 40000),
('Huawei 30 Pro', 35000);

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders (id bigserial PRIMARY KEY, customer_id bigint, product_id bigint, create_date TIMESTAMP , price INTEGER, FOREIGN KEY (customer_id) REFERENCES customers (id), FOREIGN KEY (product_id) REFERENCES products (id));
INSERT INTO orders (customer_id, product_id, create_date, price) VALUES
(1, 7,current_timestamp,5000),
(2, 6,current_timestamp,6000),
(3, 5,current_timestamp,7000),
(4, 4,current_timestamp,8000),
(5, 3,current_timestamp,9000),
(6, 2,current_timestamp,10000),
(7, 1,current_timestamp,11000),
(1, 2,current_timestamp,12000),
(2, 3,current_timestamp,13000),
(3, 4,current_timestamp,14000),
(4, 5,current_timestamp,15000),
(5, 6,current_timestamp,16000);