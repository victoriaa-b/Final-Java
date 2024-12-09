CREATE TABLE IF NOT EXISTS Products (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    product_description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    seller_id INT REFERENCES Users(id) ON DELETE CASCADE
);


