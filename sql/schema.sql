CREATE TABLE IF NOT EXISTS contacts(
    name VARCHAR(50) NOT NULL,
    email VARCHAR(150),
    phone VARCHAR(13) NOT NULL,
    is_favorite BOOLEAN NOT NULL
);

INSERT INTO contacts (name, email, phone, is_favorite) VALUES ('Adrian', 'a@a.com', '12345', 'TRUE')
INSERT INTO contacts (name, email, phone, is_favorite) VALUES ('Jordan', 'j@j.com', '54321', 'TRUE')
