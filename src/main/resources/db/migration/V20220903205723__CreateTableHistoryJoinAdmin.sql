CREATE TABLE history_join_admin(
    id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id     VARCHAR(255),
    description VARCHAR(255),
    created     TIMESTAMP,
    updated     TIMESTAMP,
    CONSTRAINT fk_history_join_admin_user FOREIGN KEY (user_id) REFERENCES user (id)
);