ALTER TABLE departure
    ADD COLUMN date_of_departure DATE NOT NULL;

CREATE TABLE code_otp
(
    id           VARCHAR(255) NOT NULL PRIMARY KEY,
    code         VARCHAR(255) NOT NULL,
    user_id      VARCHAR(255) NOT NULL,
    expired_date DATETIME,
    created      TIMESTAMP    NOT NULL,
    updated      TIMESTAMP,
    CONSTRAINT fk_code_otp_user FOREIGN KEY (user_id) REFERENCES user(id)
)
