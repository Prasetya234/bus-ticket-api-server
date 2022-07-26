CREATE TABLE payment_type
(
    id      INT       NOT NULL PRIMARY KEY,
    name    VARCHAR(255),
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL
);

INSERT INTO payment_type
VALUES (1, 'E-WALLET_GOPAY');

