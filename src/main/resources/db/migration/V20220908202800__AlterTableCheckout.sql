ALTER TABLE checkout ADD COLUMN isPurchased TINYINT(1) NOT NULL;
ALTER TABLE checkout ADD COLUMN expired_date TIMESTAMP NOT NULL;
ALTER TABLE departure ADD COLUMN driver_bus INT;
ALTER TABLE departure ADD CONSTRAINT fk_departure_company_employee FOREIGN KEY (driver_bus) REFERENCES company_employe(id);