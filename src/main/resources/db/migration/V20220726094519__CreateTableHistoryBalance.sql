CREATE TABLE history_balance
(
    id             VARCHAR(255) NOT NULL PRIMARY KEY,
    admission_fee  FLOAT,
    to_bank        VARCHAR(255),
    description    VARCHAR(255),
    method_payment VARCHAR(255),
    user_id        VARCHAR(255),
    balance_id     VARCHAR(255),
    created        TIMESTAMP    NOT NULL,
    updated        TIMESTAMP    NOT NULL,
    CONSTRAINT fk_history_balance_user FOREIGN KEY (useri_id) REFERENCES user (id),
    CONSTRAINT fk_history_balance_balance FOREIGN KEY (balance_id) REFERENCES balance (id)
);