CREATE TABLE user_role (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR(255)
);

ALTER TABLE user ADD CONSTRAINT fk_user_user_role FOREIGN KEY(roles) REFERENCES  user_role(id);