CREATE TABLE balance (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    salod float,
    user_id VARCHAR(255),
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL,
    CONSTRAINT fk_balanace_user FOREIGN KEY(useri_id) REFERENCES user(id)
)