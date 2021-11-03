DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS suppliers CASCADE;

CREATE TABLE products(
id          SERIAL PRIMARY KEY NOT NULL,
category_id INTEGER NOT NULL,
supplier_id INTEGER NOT NULL,
name        VARCHAR(200) NOT NULL,
description VARCHAR(200) NOT NULL,
price       INT NOT NULL,
currency    varchar(5)
);

CREATE TABLE categories(
    id          SERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(25) NOT NULL,
    department  VARCHAR(25) NOT NULL,
    description VARCHAR(200) NOT NULL
);

CREATE TABLE suppliers(
    id          SERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(50),
    description VARCHAR(200)
);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_products_category_id FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_products_supplier_id FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE CASCADE;

