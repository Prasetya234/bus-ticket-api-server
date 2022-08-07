RENAME TABLE balance TO wallet;
ALTER TABLE wallet MODIFY user_id VARCHAR (255) UNIQUE;
ALTER TABLE history_balance RENAME COLUMN balance_id TO wallet_id;

CREATE TABLE temporary_token
(
    id           VARCHAR(255) NOT NULL PRIMARY KEY,
    token        VARCHAR(255) NOT NULL,
    ip_address   VARCHAR(255) NOT NULL,
    user_id      VARCHAR(255) NOT NULL,
    expired_date TIMESTAMP    NOT NULL
);