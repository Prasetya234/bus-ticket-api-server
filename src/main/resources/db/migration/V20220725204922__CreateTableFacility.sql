CREATE TABLE facility
(
    id      INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name    VARCHAR(255),
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP
);

INSERT INTO facility (id, name)
VALUES (1, 'Pillow'),
       (2, 'Central TV'),
       (3, 'Toilet'),
       (4, 'Blankets'),
       (5, 'Charging Point'),
       (6, 'Reading Light'),
       (7, 'Snacks'),
       (8, 'Water Bottle');