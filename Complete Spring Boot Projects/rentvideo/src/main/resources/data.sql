INSERT INTO users (email, password, first_name, last_name, role) 
VALUES ('admin@example.com', '$2a$10$hashedAdminPassword', 'Admin', 'User', 'ADMIN');

INSERT INTO videos (title, director, genre, available)
VALUES ('Inception', 'Christopher Nolan', 'Sci-Fi', true),
       ('The Matrix', 'Wachowski', 'Sci-Fi', true);
