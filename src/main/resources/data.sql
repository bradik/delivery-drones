DROP TABLE IF EXISTS  drones;

CREATE TABLE drones
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    serial_number    VARCHAR(100)          NOT NULL,
    battery_capacity DOUBLE,
    CONSTRAINT pk_drones PRIMARY KEY (id)
);

ALTER TABLE drones
    ADD CONSTRAINT uc_drones_serial_number UNIQUE (serial_number);

INSERT INTO drones (id, serial_number, battery_capacity) VALUES (1, 'MQ198GT', 100.00);
INSERT INTO drones (id, serial_number, battery_capacity) VALUES (2, 'LQ129LT', 80.00);

