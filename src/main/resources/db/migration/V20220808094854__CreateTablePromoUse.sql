CREATE TABLE promo_use_user
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255),
    created TIMESTAMP,
    updated TIMESTAMP,
    CONSTRAINT fk_promo_use_user FOREIGN KEY (user_id) REFERENCES user (id)
)