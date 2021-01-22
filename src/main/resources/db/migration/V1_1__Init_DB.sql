CREATE TABLE customers (
  id SERIAL PRIMARY KEY,
  name VARCHAR(245) NULL);


CREATE TABLE IF NOT EXISTS accounts (
  id SERIAL PRIMARY KEY,
  accountstatus_id INT NULL,
customer_id integer REFERENCES customers(id) ON DELETE CASCADE);

  CREATE TABLE accountstatuses (
    id SERIAL PRIMARY KEY,
  name VARCHAR(45) NULL);

    CREATE TABLE specialties (
  id SERIAL PRIMARY KEY,
  name VARCHAR(245) NOT NULL);

  create TABLE customers_specialties (
   customer_id integer REFERENCES customers(id) ON DELETE CASCADE,
    specialty_id integer REFERENCES specialties(id) ON DELETE CASCADE);