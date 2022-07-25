
CREATE TABLE promo (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    image TEXT,
    code VARCHAR(255) NOT NULL,
    description TEXT,
    expired_date DATE NOT NULL,
    max_use INT DEFAULT 0,
    used INT DEFAULT 0,
    all_company TINYINT(1),
    company_id VARCHAR(255),
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL,
    CONSTRAINT fk_promo_company FOREIGN KEY(company_id) REFERENCES company(id)
);