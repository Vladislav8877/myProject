/* =====================================================
   РАЗДЕЛ 3. Простые выборки: WHERE, ORDER BY, LIKE, IN, BETWEEN
===================================================== */

-- 3.1 Все товары бренда Apple: название и цена
SELECT name,
       price
  FROM products
 WHERE brand = 'Apple';

-- 3.2 Товары с ценой от 400 до 1000, от дорогих к дешёвым
SELECT name,
       brand,
       price
  FROM products
 WHERE price BETWEEN 400 AND 1000
 ORDER BY price DESC;

-- 3.3 Товары брендов Apple, Sony, Samsung, которые есть в наличии
SELECT name,
       brand
  FROM products
 WHERE brand IN ('Apple','Sony','Samsung')
   AND stock > 0;

-- 3.4 Товары, в названии которых встречается pro в любом регистре
SELECT name,
       brand
  FROM products
 WHERE name ILIKE '%pro%';

-- 3.5 Покупатели без email
SELECT first_name,
       last_name
  FROM customers
 WHERE email IS NULL;

-- 3.6 Покупатели из Sidney или Moscow, отсортированные по дате регистрации, затем по фамилии
SELECT first_name,
       last_name,
       city,
       registered_at
  FROM customers
 WHERE city = 'Sidney' OR city = 'Moscow'
 ORDER BY registered_at, last_name;

-- 3.7 Товары Apple, которых нет в наличии или цена выше 1200
SELECT name,
       brand,
       stock,
       price
  FROM products
 WHERE brand = 'Apple'
   AND (stock = 0 OR price > 1200);

-- 3.8 Покупатели с почтой на yandex.ru
SELECT first_name,
       last_name,
       email
  FROM customers
 WHERE email ~ '@yandex\.ru$';

-- 3.9 Заказы со статусом shipped или delivered, оформленные в 2026 году
SELECT order_id,
       customer_id,
       status,
       order_date
  FROM orders
 WHERE status IN ('shipped','delivered')
   AND order_date BETWEEN '2026-01-01' AND '2026-12-31';

-- 3.10 Прайс со скидкой: название, цена, new_price. Скидка 10%, если остаток больше 10, иначе 5%. Округли до 2 знаков
SELECT name,
       price,
       stock,
       ROUND(
           CASE
               WHEN stock > 10 THEN price * 0.90
               ELSE price * 0.95
           END, 2) AS new_price
  FROM products;

/* =====================================================
   РАЗДЕЛ 4. Агрегатные функции, GROUP BY, HAVING
===================================================== */

-- 4.1 Сколько всего покупателей и у скольких из них указан email. Один запрос, два столбца
SELECT COUNT(*)     AS all_customers,
       COUNT(email) AS with_email
  FROM customers;

-- 4.2 Минимальная, максимальная и средняя (округлить до 2 знаков) цена товаров
SELECT MAX(price)          AS max_price,
       MIN(price)          AS min_price,
       ROUND(AVG(price),2) AS avg_price
  FROM products;

-- 4.3 Сколько товаров у каждого бренда, от большего количества к меньшему
SELECT brand,
       COUNT(*) AS products_count
  FROM products
 GROUP BY brand
 ORDER BY products_count DESC, brand;

-- 4.4 Средняя цена товаров по каждой категории (category_id)
SELECT category_id,
       ROUND(AVG(price),2) AS avg_price_category
  FROM products
 GROUP BY category_id
 ORDER BY avg_price_category DESC;

-- 4.5 Суммарный остаток (stock) по брендам, только бренды с суммой больше 20
SELECT brand,
       SUM(stock) AS all_stock_brand
  FROM products
 GROUP BY brand
HAVING SUM(stock) > 20
 ORDER BY all_stock_brand DESC;

-- 4.6 Количество покупателей в каждом городе, только города, где больше одного покупателя
SELECT city,
       COUNT(*) AS customers_count
  FROM customers
 GROUP BY city
HAVING COUNT(*) > 1;

-- 4.7 Сколько заказов у каждого покупателя (customer_id), только у тех, у кого 2 и больше
SELECT customer_id,
       COUNT(order_id) AS all_orders
  FROM orders
 GROUP BY customer_id
HAVING COUNT(order_id) >= 2;

-- 4.8 Сколько заказов в каждом статусе
SELECT status,
       COUNT(*) AS all_orders
  FROM orders
 GROUP BY status
 ORDER BY all_orders DESC;

-- 4.9 Сумма каждого заказа по таблице order_items, от самой большой
SELECT order_id,
       SUM(quantity * unit_price) AS total_price
  FROM order_items
 GROUP BY order_id
 ORDER BY total_price DESC;

-- 4.10 По товарам, которые есть в наличии (stock > 0): для каждого бренда количество,
-- средняя цена (округлить до 2 знаков), только бренды со средней ценой выше 500, от дорогих к дешёвым
SELECT brand,
       COUNT(*)            AS products_count,
       ROUND(AVG(price),2) AS avg_price
  FROM products
 WHERE stock > 0
 GROUP BY brand
HAVING AVG(price) > 500
 ORDER BY avg_price DESC;

/* =====================================================
   РАЗДЕЛ 5. JOIN: объединение нескольких таблиц
===================================================== */

-- 5.1 Все товары с названием их категории: товар, бренд, категория, цена
SELECT p.name AS product_name,
       p.brand AS produtc_brand,
       c.name AS product_category,
       p.price AS produc_price
  FROM products AS p
  JOIN categories AS c
ON p.category_id = c.category_id;

-- 5.2 Все категории с количеством товаров в каждой, включая категории без товаров
SELECT c.name                AS category_name,
       COUNT(p.product_id)   AS products_count
  FROM categories AS c
  LEFT JOIN products AS p
ON c.category_id = p.category_id
 GROUP BY c.name
 ORDER BY products_count DESC, c.name;

-- 5.3 Все заказы с именем и фамилией покупателя: номер, дата, статус, имя, фамилия, от старых к новым
SELECT o.order_id AS order_number,
       o.order_date,
       o.status,
       c.first_name,
       c.last_name
  FROM orders AS o
  JOIN customers AS c
ON o.customer_id = c.customer_id
 ORDER BY order_date;

-- 5.4 Состав заказов: дата заказа, фамилия покупателя, название товара, количество
SELECT o.order_date,
       c.last_name,
       p.name AS product_name,
       oi.quantity
  FROM orders AS o
  JOIN customers AS c
ON o.customer_id = c.customer_id
  JOIN order_items AS oi
ON o.order_id = oi.order_id
  JOIN products AS p
ON oi.product_id = p.product_id;

-- 5.5 Покупатели, у которых нет заказов
SELECT c.first_name,
       c.last_name,
       o.order_id
  FROM customers AS c
  LEFT JOIN orders AS o
ON c.customer_id = o.customer_id
 WHERE o.order_id IS NULL;

-- 5.6 Товары, которые никто не заказывал
SELECT p.name
  FROM products AS p
  LEFT JOIN order_items AS oi
ON p.product_id = oi.product_id
 WHERE oi.order_id IS NULL;

-- 5.7 Категории, в которых нет товаров
SELECT c.name AS category_name
  FROM categories AS c
  LEFT JOIN products AS p
ON c.category_id = p.category_id
 WHERE p.category_id IS NULL;

-- 5.8 Сумма каждого заказа с фамилией покупателя: номер заказа, фамилия, сумма (INNER JOIN)
SELECT oi.order_id,
       c.last_name,
       SUM(oi.quantity * oi.unit_price) AS total_price
  FROM order_items AS oi
  JOIN orders AS o
ON oi.order_id = o.order_id
  JOIN customers AS c
ON o.customer_id = c.customer_id
 GROUP BY oi.order_id, c.last_name
 ORDER BY oi.order_id;

-- 5.9 То же, что 5.8, но все заказы, включая без позиций (LEFT JOIN)
SELECT o.order_id,
       c.last_name,
       SUM(oi.quantity * oi.unit_price) AS total_price
  FROM orders AS o
  LEFT JOIN order_items AS oi
ON o.order_id = oi.order_id
  JOIN customers AS c
ON o.customer_id = c.customer_id
 GROUP BY o.order_id, c.last_name
 ORDER BY o.order_id;

-- 5.10 Сколько потратил каждый покупатель на неотменённые заказы, только те, кто потратил больше 1000, от большего к меньшему
SELECT c.last_name,
       SUM(oi.quantity * oi.unit_price) AS total_spent
  FROM order_items AS oi
  JOIN orders AS o
ON o.order_id = oi.order_id
  JOIN customers AS c
ON c.customer_id = o.customer_id
 WHERE o.status <> 'cancelled'
 GROUP BY c.customer_id, c.last_name
HAVING SUM(oi.quantity * oi.unit_price) > 1000
 ORDER BY total_spent DESC;

/* =====================================================
   РАЗДЕЛ 6. Вложенные запросы (подзапросы)
===================================================== */

-- 6.1 Товары дороже средней цены всех товаров: название, цена
SELECT p.name  AS product_name,
       p.brand AS product_brand,
       p.price
  FROM products AS p
 WHERE p.price > (SELECT AVG(price)
                    FROM products)
 ORDER BY p.price DESC;

-- 6.2 Покупатели, у которых есть хотя бы один заказ
SELECT c.first_name,
       c.last_name
  FROM customers AS c
 WHERE c.customer_id IN (SELECT customer_id
                           FROM orders);

-- 6.3 Товары, которые никто не заказывал
SELECT p.name
  FROM products AS p
 WHERE p.product_id NOT IN (SELECT product_id
                              FROM order_items); -- NOT IN тут безопасен потому что product_id входит в состав PK

-- 6.4 Категории, в которых нету ни одного товара
SELECT c.name
  FROM categories AS c
 WHERE NOT EXISTS (SELECT 1
                     FROM products AS p
                    WHERE p.category_id = c.category_id);

-- 6.5 Категории, в которых есть хотя бы один товар не в наличии (stock = 0)
SELECT c.name
  FROM categories AS c
 WHERE EXISTS (SELECT 1
                 FROM products AS p
                WHERE p.category_id = c.category_id
                  AND p.stock = 0);

-- 6.6 Товары дороже всех товаров категории Headphones (категорию найди по названию)
SELECT p.name
  FROM products AS p
 WHERE p.price > ALL (SELECT p2.price
                        FROM products AS p2
                       WHERE p2.category_id = (SELECT c.category_id
                                                 FROM categories AS c
                                                WHERE c.name = 'Headphones'));

-- 6.7 Средняя сумма заказа, округлённая до 2 знаков
SELECT ROUND(AVG(t.total_price),2) AS avg_total_price
  FROM (SELECT oi.order_id,
               SUM(oi.quantity * oi.unit_price) AS total_price
          FROM order_items AS oi
         GROUP BY oi.order_id) AS t;

-- 6.8 Для каждого покупателя: фамилия и количество его заказов, включая тех, у кого их нет
SELECT c.last_name,
       (SELECT COUNT(*)
          FROM orders AS o
         WHERE o.customer_id = c.customer_id)  AS orders_count
  FROM customers AS c;

-- 6.9 Заказы, сумма которых выше средней суммы заказа: номер, сумма
SELECT order_id,
       SUM(quantity * unit_price) AS total_unit_price
  FROM order_items
 GROUP BY order_id
HAVING SUM(quantity * unit_price) > (SELECT AVG(t.total_price)
                                       FROM (SELECT SUM(quantity * unit_price) AS total_price
                                               FROM order_items
                                              GROUP BY order_id) AS t);

-- 6.10 Покупатели, чей email не совпадает ни с одним email покупателей из Sidney.
-- Сначала напиши через NOT IN, посмотри результат, объясни его в комментарии, затем перепиши через NOT EXISTS
SELECT c.last_name,
       c.email
  FROM customers AS c
 WHERE NOT EXISTS (SELECT 1
                     FROM customers AS cu
                    WHERE cu.email = c.email
                      AND cu.city = 'Sidney');

SELECT c.last_name,
       c.email
  FROM customers AS c
 WHERE c.email NOT IN (SELECT cu.email
                         FROM customers AS cu
                        WHERE cu.city = 'Sidney');
-- ЭТО ЛОВУШКА ТАК ПИСАТЬ НЕЛЬЗЯ, ЕСЛИ ВЕРНЕТСЯ ХОТЬ ОДИН NULL
-- ТО ВСЯ ЦЕОПЧКА ПРОВЕРКИ NOT NULL/NOT IN БУДЕТ UNKNOWN И СТРОКИ ОТБРОСЯТСЯ
-- НУЖНО ВСЕГДА ИСПОЛЬЗОВАТЬ NOT EXISTS (SELECT 1) ЕСЛИ ЕСТЬ ХОТЬ ОДИН МАЛЕЙШИЙ
-- ШАНС УВИДЕТЬ NULL

/* =====================================================
   РАЗДЕЛ 7. UPDATE и DELETE
===================================================== */

-- 7.1 Поменять цену iPhone 15 на 949.90
-- до
SELECT product_id,
       name,
       price
  FROM products
 WHERE name = 'iPhone 15';

UPDATE products
   SET price = 949.90
 WHERE name = 'iPhone 15';

-- после
SELECT product_id,
       name,
       price
  FROM products
 WHERE name = 'iPhone 15';

-- проверка: цена в старых заказах не изменилась
SELECT order_id,
       product_id,
       unit_price
  FROM order_items
 WHERE product_id = (SELECT product_id
                       FROM products
                      WHERE name = 'iPhone 15');

-- 7.2 Поднять цену всех товаров бренда Samsung на 5%, результат округлить до 2 знаков
-- до
SELECT product_id,
       name,
       brand,
       price
  FROM products
 WHERE brand = 'Samsung';

UPDATE products
   SET price = ROUND(price * 1.05, 2)
 WHERE brand = 'Samsung';

-- после
SELECT product_id,
       name,
       brand,
       price
  FROM products
 WHERE brand = 'Samsung';

-- 7.3 Заказу 8 поставить статус paid
-- до
SELECT order_id,
       status
  FROM orders
 WHERE order_id = 8;

UPDATE orders
   SET status = 'paid'
 WHERE order_id = 8
   AND status = 'new';

-- после
SELECT order_id,
       status
  FROM orders
 WHERE order_id = 8;

-- 7.4 Всем заказам со статусом shipped, оформленным до 2026-09-01, поставить delivered
-- до
SELECT order_id,
       order_date,
       status
  FROM orders
 WHERE status = 'shipped'
   AND order_date < '2026-09-01';

UPDATE orders
   SET status = 'delivered'
 WHERE status = 'shipped'
   AND order_date < '2026-09-01';

-- после
SELECT order_id,
       order_date,
       status
  FROM orders
 WHERE status = 'shipped'
   AND order_date < '2026-09-01';

-- 7.5 Удалить товар Nintendo Switch 2
-- до
SELECT product_id,
       name,
       brand
  FROM products
 WHERE name = 'Nintendo Switch 2'
   AND brand = 'Nintendo';

DELETE FROM products
 WHERE name = 'Nintendo Switch 2'
   AND brand = 'Nintendo';

-- после
SELECT product_id,
       name,
       brand
  FROM products
 WHERE name = 'Nintendo Switch 2'
   AND brand = 'Nintendo';

-- 7.6 Удалить покупателя без заказов (Rose)
-- до
SELECT c.customer_id,
       c.last_name
  FROM customers AS c
 WHERE c.customer_id NOT IN (SELECT o.customer_id
                               FROM orders AS o
                              WHERE o.customer_id = c.customer_id);

DELETE FROM customers AS c
 WHERE c.customer_id NOT IN (SELECT o.customer_id
                               FROM orders AS o
                              WHERE o.customer_id = c.customer_id);
-- после
SELECT c.customer_id,
       c.last_name
  FROM customers AS c
 WHERE c.customer_id NOT IN (SELECT o.customer_id
                               FROM orders AS o
                              WHERE o.customer_id = c.customer_id);

-- 7.7 Удалить категорию Accessories
-- до
SELECT category_id,
       name
  FROM categories
 WHERE name = 'Accessories';

DELETE FROM categories
 WHERE name = 'Accessories';

-- после
SELECT category_id,
       name
  FROM categories
 WHERE name = 'Accessories';

/* =====================================================
   РАЗДЕЛ 8. проверка целостности (попытки нарушить ограничения).
   В данном разделе каждая операция должна завершиться с ошибкой
   нужно выполнять по одному а не скриптом целиком
   8.10 - без ошибок
===================================================== */

-- 8.1 Вставить товар с несуществующим category_id (например, 999)
INSERT INTO products (category_id, name, brand, price, stock, created_at)
    VALUES (999,'Headphones','Sony',300.00,30,DEFAULT);

-- 8.2 Удалить категорию Smartphones, в которой есть товары
DELETE FROM categories
 WHERE name = 'Smartphones';

-- 8.3 Удалить покупателя Qwerty (у него есть заказы)
DELETE FROM customers
 WHERE last_name = 'Qwerty';

-- 8.4 Удалить заказанный товар iPhone 15
DELETE FROM products
 WHERE name = 'iPhone 15';

-- 8.5 Вставить товар с отрицательной ценой
INSERT INTO products (category_id, name, brand, price, stock,created_at)
    VALUES (1,'UG-101','ZTE',-450.00,12,DEFAULT);

-- 8.6 Вставить покупателя с уже существующим email
INSERT INTO customers (first_name, last_name, email, city, registered_at)
    VALUES ('Ivan','Gog','icebaby@gmail.com','Moscow',DEFAULT);

-- 8.7 Вставить заказ со статусом unknown
INSERT INTO orders (customer_id, order_date, status)
    VALUES (2,'2026-10-10','unknown');

-- 8.8 Повторно вставить в order_items пару (order_id, product_id), которая уже есть
INSERT INTO order_items (order_id, product_id, quantity, unit_price)
    VALUES (1,3,1,1100.00);

-- 8.9 Вставить позицию заказа с unit_price = NULL
INSERT INTO order_items (order_id, product_id, quantity, unit_price)
    VALUES (1,5,1,NULL);

-- 8.10 Правильно удалить заказ 6 вместе с позициями: сначала order_items, потом orders
DELETE FROM order_items
 WHERE order_id = 6;

DELETE FROM orders
 WHERE order_id = 6;