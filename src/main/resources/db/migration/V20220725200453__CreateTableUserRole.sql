CREATE TABLE user_role
(
    id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO user_role
VALUES (1, 'ADMIN'),
       (2, 'USER');

ALTER TABLE user
    ADD CONSTRAINT fk_user_user_role FOREIGN KEY (roles) REFERENCES user_role (id);