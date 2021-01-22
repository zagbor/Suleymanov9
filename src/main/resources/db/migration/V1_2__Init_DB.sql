insert into accountstatuses values(1, 'ACTIVE');
insert into accountstatuses values(2, 'BANNED');
insert into accountstatuses values(3, 'DELETED');

insert into specialties (name) values ('Архитектор');
insert into specialties (name) values ('Поэт');
insert into specialties (name) values ('Писатель');
insert into specialties (name) values ('Строитель');
insert into specialties (name) values ('Программист');
insert into specialties (name) values ('Спортсмен');

insert into customers (name) values ('Лермонтов');
insert into accounts (customer_id,accountstatus_id) values (1,1);
insert into customers_specialties(customer_id, specialty_id) values(1, 1);

insert into customers (name) values ('Пушкин');
insert into accounts (customer_id, accountstatus_id) values (2,2);
insert into customers_specialties(customer_id, specialty_id) values(2, 1);
insert into customers_specialties(customer_id, specialty_id) values(2, 2);


insert into customers (name) values ('Борис');
insert into accounts (customer_id, accountstatus_id) values (3, 1);
insert into customers_specialties(customer_id, specialty_id) values(3, 4);