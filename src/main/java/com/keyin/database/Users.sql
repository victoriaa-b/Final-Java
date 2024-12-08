CREATE TABLE IF NOT EXISTS  Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    user_password VARCHAR(50) NOT NULL,
    user_role VARCHAR(20) CHECK (User_role IN ('Buyer', 'Admin', 'Seller'))
);
