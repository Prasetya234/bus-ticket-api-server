CREATE TABLE destination
(
    id      INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    used    INT,
    created TIMESTAMP    NOT NULL,
    updated TIMESTAMP    NOT NULL
);