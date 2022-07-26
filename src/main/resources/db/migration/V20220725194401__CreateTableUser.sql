CREATE TABLE user
(
    id            VARCHAR(255) NOT NULL PRIMARY KEY,
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    email         VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    number_phone  VARCHAR(255),
    date_of_birth DATE,
    blocked       TINYINT(1),
    address       TEXT,
    roles         VARCHAR(255),
    created       TIMESTAMP    NOT NULL,
    updated       TIMESTAMP    NOT NULL
);