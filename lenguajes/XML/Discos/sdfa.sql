use mysql;
select * from user;

--------------------
alter user root@'%' identified with 'mysql_native_password' by '123';

--------------------
flush PRIVILEGES;