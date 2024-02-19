CREATE TABLE IF NOT EXISTS contacts(
    name VARCHAR(50) NOT NULL,
    email VARCHAR(150),
    phone VARCHAR(13) NOT NULL,
    is_favorite BOOLEAN NOT NULL
);

SELECT * FROM contacts;