DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;

/* =====================================================
   РАЗДЕЛ 1. Создание таблиц
   Тут происходит создание таблиц где я определяю каким набором правил
   должна пользоваться СУБД перед тем как добавить данные в таблицу
   для избежания логического мусора
===================================================== */

-- 1.1 категории техники
CREATE TABLE categories (
    PRIMARY KEY (category_id),
    category_id SERIAL       NOT NULL,
    name        VARCHAR(30)  UNIQUE NOT NULL
);

-- 1.2 товары
CREATE TABLE products (
    PRIMARY KEY (product_id),
    product_id  SERIAL        NOT NULL,
    category_id INT           NOT NULL REFERENCES categories (category_id),
    name        VARCHAR(100)  NOT NULL,
    brand       VARCHAR(50)   NOT NULL,
    price       NUMERIC(10,2) NOT NULL CHECK (price >= 0),
    stock       INT           NOT NULL CHECK (stock >= 0),
    created_at  DATE          NOT NULL DEFAULT CURRENT_DATE
);

-- 1.3 покупатели
CREATE TABLE customers (
    PRIMARY KEY (customer_id),
    customer_id   SERIAL      NOT NULL,
    first_name    VARCHAR(30) NOT NULL,
    last_name     VARCHAR(30) NOT NULL,
    email         TEXT        UNIQUE,
    city          VARCHAR(30),
    registered_at DATE        NOT NULL DEFAULT CURRENT_DATE
);

-- 1.4 заказы
CREATE TABLE orders (
    PRIMARY KEY (order_id),
    order_id    SERIAL      NOT NULL,
    customer_id INT         NOT NULL REFERENCES customers (customer_id),
    order_date  DATE        NOT NULL DEFAULT CURRENT_DATE,
    status      VARCHAR(20) NOT NULL DEFAULT 'new'
                            CHECK (status IN ('new', 'paid', 'shipped', 'delivered', 'cancelled'))
);

-- 1.5 позиции заказа (связка M:N)
CREATE TABLE order_items (
    PRIMARY KEY (order_id, product_id),
    order_id   INT            NOT NULL REFERENCES orders (order_id),
    product_id INT            NOT NULL REFERENCES products (product_id),
    quantity   INT            NOT NULL CHECK (quantity > 0),
    unit_price NUMERIC(10,2)  NOT NULL CHECK (unit_price >= 0)
);