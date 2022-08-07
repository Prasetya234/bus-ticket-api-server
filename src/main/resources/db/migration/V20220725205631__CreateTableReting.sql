CREATE TABLE reting
(
    id      INT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255),
    color   VARCHAR(255),
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP
);

INSERT INTO reting (name, color, created, updated)
VALUES ('Buruk', 'red', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Sedang', 'yellow', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Bagus', 'green', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

ALTER TABLE company
    ADD CONSTRAINT fk_company_reting FOREIGN KEY (reting_id) REFERENCES reting (id);

