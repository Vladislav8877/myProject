/* =====================================================
   РАЗДЕЛ 2. Наполнение таблиц данными
   Буду использовать Eng. раскладку
   Порядок заполнения данных родители -> дети
===================================================== */

-- 2.1 Категории техники
INSERT INTO categories (name)
    VALUES ('Smartphones'),
           ('Laptops'),
           ('Headphones'),
           ('Smart devices'),
           ('Cameras'),
           ('Gaming Consoles'),
           ('Accessories');

-- 2.2 Товары (category_id = 1 это 'Smartphones', 2 это 'Laptops' etc.)
-- Первая строка: created_at = DEFAULT (сегодняшняя дата)
INSERT INTO products (category_id, name, brand, price, stock, created_at)
    VALUES (1,'iPhone 15','Apple',999.90,10,DEFAULT),
           (1,'iPhone 17 Pro','Apple',1299.90,0,'2026-09-20'),
           (2,'MagicBook X14','Honor',1100.00,12,'2026-09-20'),
           (2,'MacBook Pro 14','Apple',1489.99,20,'2026-09-21'),
           (3,'WH-1000XM6','Sony',790.90,14,'2026-08-08'),
           (3,'Tour One M3','JBL',420.00,5,'2026-08-20'),
           (4,'Galaxy Watch','Samsung',120.80,18,'2026-03-15'),
           (4,'Xiaomi Smart Band','Xiaomi',339.99,2,'2026-01-15'),
           (5,'Z8','Nikon',64.70,8,'2025-12-31'),
           (5,'Osmo Pocket 3','DJI',490.00,24,'2026-10-01'),
           (6,'Xbox Series X','Microsoft',650.00,1,'2026-07-07'),
           (6,'Nintendo Switch 2','Nintendo',32.99,34,'2026-07-07');

-- 2.3 Покупатели
-- Jane: email = NULL; Alivia: registered_at = DEFAULT
INSERT INTO customers (first_name, last_name, email, city, registered_at)
    VALUES ('Jane','Qwerty',NULL,'Sidney','2026-10-01'),
           ('Serena','Lopes','icebaby@gmail.com','Sidney','2023-09-18'),
           ('Jim','Jameson','po_like_me@yandex.ru','LA','2025-05-15'),
           ('Sofia','Drake','drakes@gmail.com','New York','2026-06-09'),
           ('Clark','Deme','demon123@outlook.com','Washington','2022-02-02'),
           ('Alivia','Rose','flowers@yandex.ru','Vegas',DEFAULT),
           ('Oleg','Tinkoff','tb@bank.ru','Moscow','2023-11-27');

-- 2.4 Заказы
-- Первая строка: order_date и status = DEFAULT
INSERT INTO orders (customer_id, order_date, status)
    VALUES (7,DEFAULT,DEFAULT),
           (1,'2026-09-18','shipped'),
           (1,'2026-08-18','paid'),
           (1,'2026-07-18','shipped'),
           (2,'2026-08-01','delivered'),
           (3,'2025-09-19','cancelled'),
           (4,'2026-07-09','delivered'),
           (5,'2026-10-01','new'),
           (5,'2026-09-30','shipped');

-- 2.5 Позиции заказа
-- Nintendo Switch 2 (product_id = 12) нигде не заказан, это нужно для проверок
INSERT INTO order_items (order_id, product_id, quantity, unit_price)
    VALUES (1,3,1,1100.00),
           (1,1,1,880.00),
           (1,4,2,1600.00),
           (2,4,1,1489.99),
           (3,2,1,1299.90),
           (4,5,2,790.90),
           (4,6,1,420.00),
           (5,7,3,120.80),
           (6,8,1,450.60),
           (7,9,1,64.70),
           (7,10,1,490.00),
           (9,11,3,450.00);