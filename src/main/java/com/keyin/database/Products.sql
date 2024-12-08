CREATE TABLE IF NOT EXISTS Products (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    product_description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT DEFAULT 0,
    seller_id INT NOT NULL,
    seller TEXT NOT NULL
);

