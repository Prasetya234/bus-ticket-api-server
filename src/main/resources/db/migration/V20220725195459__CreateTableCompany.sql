CREATE TABLE company
(
    id              VARCHAR(255) NOT NULL PRIMARY KEY,
    logo            VARCHAR(255),
    name            VARCHAR(255),
    director        VARCHAR(255),
    phone           VARCHAR(255),
    number_of_buses INT,
    total_passenger INT,
    reting_id       INT,
    address         TEXT,
    blocked         TINYINT(1),
    admin_id        VARCHAR(255),
    created         TIMESTAMP    NOT NULL,
    updated         TIMESTAMP    NOT NULL,
    CONSTRAINT fk_company_user FOREIGN KEY (admin_id) REFERENCES user (id)
);