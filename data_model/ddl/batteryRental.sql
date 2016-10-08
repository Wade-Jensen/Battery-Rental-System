-- Generated by Oracle SQL Developer Data Modeler 4.1.3.901
--   at:        2016-09-28 10:05:31 AEST
--   site:      Oracle Database 12c
--   type:      Oracle Database 12c




CREATE TABLE battery
  (
    id             INTEGER  ,
    machine_id     INTEGER ,
    soc            DOUBLE PRECISION ,
    last_updated   TIMESTAMP ,
    available      BOOLEAN ,
    date_purchased TIMESTAMP ,
    date_retired   TIMESTAMP,
	slot		   INTEGER
  ) ;
ALTER TABLE battery ADD CONSTRAINT battery_PK PRIMARY KEY ( id ) ;


CREATE TABLE battery_user
  (
    id        INTEGER  ,
    firstname VARCHAR ,
    lastname  VARCHAR ,
    credit    DOUBLE PRECISION ,
    cardId    VARCHAR,
	loginId   INTEGER
  ) ;
ALTER TABLE battery_user ADD CONSTRAINT battery_user_PK PRIMARY KEY ( id ) ;


CREATE TABLE machine
  (
    id            INTEGER  ,
    lat           DOUBLE PRECISION ,
    lon           DOUBLE PRECISION ,
    text_location VARCHAR ,
    model_type    VARCHAR,	
  ) ;
ALTER TABLE machine ADD CONSTRAINT machine_PK PRIMARY KEY ( id ) ;


CREATE TABLE rental_log
  (
    id              INTEGER  ,
    battery_user_id INTEGER ,
    machine_id      INTEGER ,
    battery_id      INTEGER ,
    time_rented     TIMESTAMP ,
    time_returned   TIMESTAMP ,
    initial_charge  DOUBLE PRECISION ,
    final_charge    DOUBLE PRECISION
  ) ;
ALTER TABLE rental_log ADD CONSTRAINT rental_log_PK PRIMARY KEY ( id ) ;


ALTER TABLE battery ADD CONSTRAINT battery_machine_FK FOREIGN KEY ( machine_id ) REFERENCES machine ( id ) ;

ALTER TABLE rental_log ADD CONSTRAINT rental_log_battery_FK FOREIGN KEY ( battery_id ) REFERENCES battery ( id ) ;

ALTER TABLE rental_log ADD CONSTRAINT rental_log_battery_user_FK FOREIGN KEY ( battery_user_id ) REFERENCES battery_user ( id ) ;

ALTER TABLE rental_log ADD CONSTRAINT rental_log_machine_FK FOREIGN KEY ( machine_id ) REFERENCES machine ( id ) ;


-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             4
-- CREATE INDEX                             0
-- ALTER TABLE                              8
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- TSDP POLICY                              0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
