CREATE TABLE person (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    longboat VARCHAR(100),
    number VARCHAR(10),
    complement VARCHAR(255),
    neighborhood VARCHAR(255),
    zip_code VARCHAR(255),
    city VARCHAR(100),
    estate VARCHAR(100),
    is_active TINYINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO
person (name,longboat,number,complement,neighborhood,zip_code,city,estate,is_active)
values
('Junior','Av.B','624','Condomínio Morada dos Buquês','Jereissate III','61.814-901','Pacatuba','Ceará',1);

INSERT INTO
person (name,longboat,number,complement,neighborhood,zip_code,city,estate,is_active)
values
('Damyres','Av.B','624','Condomínio Morada dos Buquês','Jereissate III','61.814-901','Pacatuba','Ceará',1);

INSERT INTO
person (name,longboat,number,complement,neighborhood,zip_code,city,estate,
is_active)
values
('Nadir','Av.B','311','Edilson Candéa','Jereissate III','61.814-901','Pacatuba','Ceará',1);

INSERT INTO
person (name,longboat,number,complement,neighborhood,zip_code,city,estate,
is_active)
values
('Gleyciane','Av.B','311','Edilson Candéa','Jereissate III','61.814-901','Pacatuba','Ceará',1);

INSERT INTO
person (name,longboat,number,complement,neighborhood,zip_code,city,estate,
is_active)
values
('Miguel','Av.B','624','Condomínio Morada dos Buquês','Jereissate III','61.814-901','Pacatuba','Ceará',1);

INSERT INTO
person (name,longboat,number,complement,neighborhood,zip_code,city,estate,
is_active)
values
('Dayrles','Av.B','666','Rua 71','Jereissate III','61.814-901','Pacatuba','Ceará',1);

INSERT INTO
person (name,longboat,number,complement,neighborhood,zip_code,city,estate,
is_active)
values
('Zigueira','Av.B','777','Na PQP','Jereissate III','61.814-901','Pacatuba','Ceará',0);

INSERT INTO
person (name,longboat,number,complement,neighborhood,zip_code,city,estate,
is_active)
values
('Yuk','Av.B','777','Na PQP','Jereissate III','61.814-901','Pacatuba','Ceará',0);

INSERT INTO
person (name,longboat,number,complement,neighborhood,zip_code,city,estate,
is_active)
values
('Ale','Av.B','777','Na PQP','Jereissate III','61.814-901','Pacatuba','Ceará',0);

INSERT INTO
person (name,longboat,number,complement,neighborhood,zip_code,city,estate,
is_active)
values
('Maravilha','Av.B','777','Na PQP','Jereissate III','61.814-901','Pacatuba','Ceará',0);

INSERT INTO
person (name,longboat,number,complement,neighborhood,zip_code,city,estate,
is_active)
values
('Anarquia','Av.B','777','Na PQP','Jereissate III','61.814-901','Pacatuba','Ceará',0);
