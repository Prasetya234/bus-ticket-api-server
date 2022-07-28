CREATE TABLE reting
(
    id      INT       NOT NULL PRIMARY KEY,
    name    VARCHAR(255),
    color   VARCHAR(255),
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP
);

ALTER TABLE company
    ADD CONSTRAINT fk_company_reting FOREIGN KEY (reting_id) REFERENCES reting (id);