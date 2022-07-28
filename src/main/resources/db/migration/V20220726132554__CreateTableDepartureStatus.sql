CREATE TABLE departure_status
(
    id      INT          NOT NULL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    created TIMESTAMP    NOT NULL,
    updated TIMESTAMP
);

ALTER TABLE departure
    ADD CONSTRAINT fk_departure_departure_status FOREIGN KEY (departure_status_id) REFERENCES departure_status (id);