---------------README-----------------------

Update process for the ovs.ddl file.

1) In SQL DEVELOPER in the DDL generation dialog, under the "Create Selection" tab
	select only the "Not Assigned To Users" checkbox

MAKE SURE THAT find is case sensitive and set to "WHOLE WORD"

2) If you have added a new table, go to change_owner.ddl and add the table to the list
	(alphabetical order preferred)
"ALTER TABLE public.TABLENAME
  OWNER TO ovs;"

3) Find and replace "CREATE USER pg_catalog IDENTIFIED BY ACCOUNT UNLOCK ;" with "" - NOT NEEDED ON MY MACHINE
4) Find and replace "LOGGING" with "" - NOT NEEDED ON MY MACHINE - YOU CAN DISABLE LOGGING IN SETTINGS
5) Find and replace "CHAR (1)" with "BOOLEAN"
6) Find and replace "NUMERIC" with "DOUBLE PRECISION"
7) Find and replace "NOT NULL" with ""
8) Find and replace "ALTER TABLE build_plan_metal ADD CONSTRAINT build_plan_metal_metal_FK FOREIGN KEY ( metal_metal_id ) REFERENCES metal ( metal_id ) ;" 
               with "ALTER TABLE build_plan_metal ADD CONSTRAINT build_plan_metal_metal_FK FOREIGN KEY ( metal_metal_id ) REFERENCES metal ( metal_id ) ON DELETE SET NULL ;"

