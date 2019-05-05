create database GearBuddyDb;
create user 'gearbuddy'@'%' identified by 'password';
grant all on GearBuddyDb.* to 'gearbuddy'@'%';