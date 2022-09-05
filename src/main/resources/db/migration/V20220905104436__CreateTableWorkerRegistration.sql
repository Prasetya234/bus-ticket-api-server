ALTER TABLE history_join_admin MODIFY description TEXT NOT NULL;

CREATE TABLE worker_registration
(
    id                         VARCHAR(255) NOT NULL PRIMARY KEY,
    age                        INT          NOT NULL,
    address                    TEXT         NOT NULL,
    description                TEXT         NOT NULL,
    criminal_record            VARCHAR(255),
    nik                        VARCHAR(255) NOT NULL,
    ktp_photo_url              VARCHAR(255) NOT NULL,
    sim_photo_url              VARCHAR(255) NOT NULL,
    is_married                 TINYINT(1) NOT NULL,
    is_session_creation_policy TINYINT(1) NOT NULL,
    user_id                    VARCHAR(255) NOT NULL,
    company_id                 VARCHAR(255) NOT NULL,
    created                    TIMESTAMP    NOT NULL,
    updated                    TIMESTAMP
);