CREATE TABLE departure_status
(
    id      INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    created TIMESTAMP    NOT NULL,
    updated TIMESTAMP
);

ALTER TABLE departure
    ADD CONSTRAINT fk_departure_departure_status FOREIGN KEY (departure_status_id) REFERENCES departure_status (id);;

INSERT INTO departure_status (name, created)
VALUES ('WAITING FOR DEPARTURE SCHEDULE', '2022-09-08 20:38:46'),
       ('ALREADY DEPARTED', '2022-09-08 20:39:54'),
       ('UNTIL THE DESTINATION', '2022-09-08 20:40:48');