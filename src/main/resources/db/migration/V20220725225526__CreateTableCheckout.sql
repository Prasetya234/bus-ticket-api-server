CREATE TABLE checkout (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    departure_id VARCHAR(255) NOT NULL,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL,
    CONSTRAINT fk_checkoute_user FOREIGN KEY(useri_id) REFERENCES user(id),
    CONSTRAINT fk_checkoute_departure FOREIGN KEY(departure_id) REFERENCES departure(id),
)