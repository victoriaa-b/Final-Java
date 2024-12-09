CREATE TABLE IF NOT EXISTS  Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    user_password VARCHAR(300) NOT NULL,
    email VARCHAR(50) NOT NULL,
    user_role VARCHAR(20) CHECK (user_role IN ('buyer', 'admin', 'seller'))
);

