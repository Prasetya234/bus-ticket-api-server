CREATE TABLE company_employe (
    id INT NOT NULL PRIMARY KEY,
    company_id VARCHAR(255),
    employe_id VARCHAR(255),
    work TINYINT(1),
    latitude VARCHAR(255),
    longitude VARCHAR(255),
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL,
    CONSTRAINT fk_company_employe_company FOREIGN KEY(company_id) REFERENCES company(id),
    CONSTRAINT fk_company_employe_user FOREIGN KEY(employe_id) REFERENCES user(id)
)