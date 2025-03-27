-- BEGIN
DROP TABLE IF EXISTS products;

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title TEXT NOT NULL,
    price INT
);
-- END
