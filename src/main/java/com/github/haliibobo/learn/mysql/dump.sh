#!/usr/bin/env bash


#1.导出指定表的数据

mysqldump -t database -u username -ppassword --tables table_name1 table_name2 table_name3 >D:\db_script.sql
mysqldump -h 127.0.0.1 -P 3306 -uroot test load_warn_info load_error_info > load.sql

 source xxx.sql;
#2.导出指定表的结构

mysqldump -d database -u username -ppassword --tables table_name1 table_name2 table_name3>D:\db_script.sql

#3.导出表的数据及结构

mysqldump database -u username -ppassword --tables table_name1 table_name2 table_name3>D:\db_script.sql

#4.若 数据中 ，某些表除外，其余表都需导出

mysqldump -h IP -u username -ppassword --default-character-set=utf8 --database database_name --ignore-table=database_name.table_name1

--ignore-table=database_name.table_name2 --ignore-table = database_name.table_name3 >D:\db_script.sql
