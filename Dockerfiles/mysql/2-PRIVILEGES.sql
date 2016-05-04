#-----------------------------------
#USER RIGHTS MANAGEMENT
#-----------------------------------
CREATE USER 'admincdb'@'172.18.0.%' IDENTIFIED BY 'qwerty1234';
CREATE USER 'admincdb'@'localhost' IDENTIFIED BY 'qwerty1234';

GRANT ALL PRIVILEGES ON `computer-database-db`.* TO 'admincdb'@'172.18.0.%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON `computer-database-db`.* TO 'admincdb'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;
