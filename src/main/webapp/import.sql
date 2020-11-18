DROP TABLE students IF EXISTS;
CREATE TABLE IF NOT EXISTS students (id bigserial, name VARCHAR(255), score int, PRIMARY KEY (id));
INSERT INTO students (name, score) VALUES ('Bob1', 70), ('Bob2', 80), ('Bob3', 50), ('Bob4', 60), ('Bob5', 80), ('Bob6', 80), ('Bob7', 60), ('Bob8', 90), ('Bob9', 50), ('Bob10', 60), ('Bob11', 40), ('Bob12', 70), ('Bob13', 80), ('Bob14', 40), ('Bob15', 50), ('Bob16', 70), ('Bob17', 80), ('Bob18', 90), ('Bob19', 90), ('Bob20', 50);

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, name VARCHAR(255), price INTEGER);
INSERT INTO products (name, price) VALUES ('Iphone X', 50000),('Honor P20', 30000),('Samsung Galaxy J5', 13000),('Nokia 3110', 5000),('Xiaomi Redmi', 18000),('Iphone 1', 40000),('Iphone 2', 40000),('Iphone 3', 40000),('Iphone 4', 40000),('Iphone 5', 40000),('Iphone 6', 40000),('Iphone 7', 40000),('Iphone 8', 40000),('Iphone 9', 40000),('Iphone 10', 40000),('Iphone 11', 40000),('Iphone 12', 40000),('Iphone 13', 40000),('Iphone 14', 40000),('Huawei P20 Pro', 35000);