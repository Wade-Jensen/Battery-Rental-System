#!/bin/bash

export PGPASSWORD=admin

# Setup OVS database and create schema
# Run this as the postgres user
# Assumes required SQL scripts are in the same directory as this script

# Create database and setup required plsql language support
sudo -u postgres dropdb --username=postgres --if-exists ovs

# Set the user OVS
sudo -u postgres psql --username=postgres -a -f create_user.sql

# Create database and setup required plsql language support
sudo -u postgres createdb --username=postgres --owner=ovs ovs

# Install the language extension
sudo -u postgres createlang --username=postgres -d ovs plpgsql

# apply hibernate sequence
sudo -u postgres psql --username=postgres -a -d ovs -f hibernate_sequence.sql

# generate schema
sudo -u postgres psql --username=postgres -a -d ovs -f ovs.sql

# change ownership from postgres to ovs
sudo -u postgres psql --username=postgres -a -d ovs -f change_owner.sql

# add currency data
sudo -u postgres psql --username=postgres -a -d ovs -f currency.sql

# add country data
sudo -u postgres psql --username=postgres -a -d ovs -f country.sql

# add metal data
sudo -u postgres psql --username=postgres -a -d ovs -f metal.sql

unset PGPASSWORD