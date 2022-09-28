DROP TABLE IF EXISTS drones;

CREATE TABLE drones
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    serial_number    VARCHAR(100)          NOT NULL,
    model            VARCHAR(255)          NOT NULL,
    weight_limit     BIGINT                NOT NULL,
    battery_capacity INT                   NOT NULL,
    state            VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_drones PRIMARY KEY (id)
);

ALTER TABLE drones
    ADD CONSTRAINT uc_drones_serial_number UNIQUE (serial_number);

INSERT INTO drones (serial_number, model, weight_limit, battery_capacity, state)
VALUES ('LW198G1', 'Lightweight', 100.00, 100, 'IDLE'),
       ('LW234G2', 'Lightweight', 100.00, 100, 'IDLE'),
       ('LW567G3', 'Lightweight', 100.00, 100, 'IDLE'),

       ('MW567G1', 'Middleweight', 100.00, 300, 'IDLE'),
       ('MW678G2', 'Middleweight', 100.00, 300, 'IDLE'),
       ('MW423G3', 'Middleweight', 100.00, 300, 'IDLE'),

       ('CW867G1', 'Cruiserweight', 100.00, 500, 'IDLE'),
       ('CW343G2', 'Cruiserweight', 100.00, 550, 'IDLE'),
       ('CW231G3', 'Cruiserweight', 100.00, 600, 'IDLE'),

       ('HW567G1', 'Heavyweight', 100.00, 900, 'IDLE'),
       ('HW678G2', 'Heavyweight', 100.00, 1200, 'IDLE'),
       ('HW423G3', 'Heavyweight', 100.00, 1500, 'IDLE')
;

DROP TABLE IF EXISTS medication;

CREATE TABLE medication
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
    name   VARCHAR(255)          NOT NULL,
    weight BIGINT                NOT NULL,
    code   VARCHAR(255)          NOT NULL,
    image  BLOB,
    CONSTRAINT pk_medication PRIMARY KEY (id)
);

INSERT INTO medication (name, code, weight)
VALUES ('Aspirin', 'CODE_01', 100),
       ('Analgin', 'CODE_02', 200),
       ('Beradual', 'CODE_03', 300),
       ('Insulin', 'CODE_04', 400)
;

