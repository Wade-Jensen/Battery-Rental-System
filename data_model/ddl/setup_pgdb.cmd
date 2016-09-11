@echo off

SET PGPASSWORD=admin

Rem Setup OVS database and create schema
Rem Run this as the postgres user
Rem Assumes required SQL scripts are in the same directory as this script

dropdb --username=postgres --if-exists ovs

Rem Set the user OVS
psql --username=postgres -a -f create_user.sql

Rem Create database and setup required plsql language support
createdb --username=postgres --owner=ovs ovs

Rem Install the language extension
createlang --username=postgres -d ovs plpgsql

Rem apply hibernate sequence
psql --username=postgres -a -d ovs -f hibernate_sequence.sql

Rem generate schema
psql --username=postgres -a -d ovs -f ovs.sql

psql --username=postgres -a -d ovs -f batteryRental.sql

Rem change ownership from postgres to ovs
psql --username=postgres -a -d ovs -f change_owner.sql

SET PGPASSWORD=


