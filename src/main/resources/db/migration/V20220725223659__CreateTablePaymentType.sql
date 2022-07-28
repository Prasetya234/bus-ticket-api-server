CREATE TABLE payment_type
(
    id      INT       NOT NULL PRIMARY KEY,
    name    VARCHAR(255),
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP
);

INSERT INTO payment_type (id, name)
VALUES (1, 'E-WALLET_GOPAY');

