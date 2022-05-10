# Sqoop

**host-:** cdb22dw011.c0lf9xyp8cv9.ap-south-1.rds.amazonaws.com
**username-:**    cdb22dw011
**Password-:**  Welcome!12345

### load file from mysql to vm hdfs
-m 1 for primary key if it consist of primary key no need to use primary key provide distributed architecture
m means mapper 1 2 3 is number of mapper
```
sqoop-import --connect jdbc:mysql://cdb22dw011.c0lf9xyp8cv9.ap-south-1.rds.amazonaws.com/cdb21dw011 --username cdb22dw011 -P --table StudentData --target-dir /StudentData1 -m 1
```


### Using select
```
sqoop-import --connect jdbc:mysql://cdb22dw011.c0lf9xyp8cv9.ap-south-1.rds.amazonaws.com/cdb21dw011 --username cdb22dw011 -P --table StudentData --where "gender='Female'" --target-dir /StudentDataFemale -m 1
```

### with primary key without -m 1 will create a distibute system
```
sqoop-import --connect jdbc:mysql://cdb22dw011.c0lf9xyp8cv9.ap-south-1.rds.amazonaws.com/cdb21dw011 --username cdb22dw011 -P --table demo --target-dir /demo123
```
### To access distribute system * for all
```
hadoop fs -cat /demo1/part*
or
hadoop fs -cat /demo1/part-m-0000
or 
hadoop fs -cat /demo1/part-m-0001
```

### Export data from linux to mysql
```
sqoop-export --connect jdbc:mysql://cdb22dw011.c0lf9xyp8cv9.ap-south-1.rds.amazonaws.com/cdb21dw011 --username cdb22dw011 -P --table priyanshu --export-dir /StudentData1/part*
```

### Import hive from mysql 
```
 sqoop-import --connect jdbc:mysql://cdb22dw011.c0lf9xyp8cv9.ap-south-1.rds.amazonaws.com \ --username cdb22dw011 root –P \ --table StudentData1 \ --hive-import -m 1
 ```
 
 ### Mysql
 
```
sqoop-import --connect jdbc:mysql://cdb22dw011.c0lf9xyp8cv9.ap-south-1.rds.amazonaws.com/cdb21dw011 --username cdb22dw011 -P --table
```

### Sqoop import 
```
sqoop-import --connect jdbc:mysql://cdb22dw011.c0lf9xyp8cv9.ap-south-1.rds.amazonaws.com/test --username cdb22dw011 -P --table customer --incremental append --check-column cust_id --last-value 115 --target-dir /customer
```

### Hive related stuffs
```
cp /home/ubh01/apache-hive-2.3.2-bin/lib/hive-common-2.3.2.jar /home/ubh01/sqoop-1.4.7.bin__hadoop-2.6.0/lib/
```
### Remove
```
hdfs dfs -rm -r /user/ubh01/customer
```
 
### hive import
```
sqoop-import --connect jdbc:mysql://cdb22dw011.c0lf9xyp8cv9.ap-south-1.rds.amazonaws.com/test --username cdb22dw011 -P --table customer --hive-import
```

### Some more work
```
hdfs dfs -ls /customer
```
### Get
```
hdfs dfs -get /customer/part* /home/ubh01
```
### Tranfer
```
cat part* > customer.txt
```
###  mkdir
```
hdfs dfs -mkdir /input
```
### put
```
hdfs dfs -put /home/ubh01/customer.txt /input
```
### export
```
sqoop-export --connect jdbc:mysql://cdb22dw011.c0lf9xyp8cv9.ap-south-1.rds.amazonaws.com/test --username cdb22dw011 -P --table customer --update-key cust_id --update-mode allowinsert --export-dir /input/customer.txt
```
### Alter table to remove first col and make datatype non null in hive
```
hive> ALTER TABLE tablename
SET TBLPROPERTIES ("skip.header.line.count"="1");
```
### Creat table internal by default
```
hive>  create table priyanshu(age int, gender string,name string,roll int, email string) row format delimited fields terminated by ',' TBLPROPERTIES ("skip.header.line.count"="1"); 

```
### External table
```
hive>  create external table priyanshu(age int, gender string,name string,roll int, email string) row format delimited fields terminated by ',' TBLPROPERTIES ("skip.header.line.count"="1"); 
````
### load in hive
```
hive> load data inpath '/StudentData1' overwrite into table priyanshu;

```
### count total number of row
```
hive>  select count (*) from tablename;
```
### Notes shared
```
hive> create table priyanshu(
age int,
gender string,
name string,
course string,
roll int,
marks int,
email string)
row format delimited
fields terminated by ','
TBLPROPERTIES ("skip.header.line.count"="1");
=============================================================================
```
### Load from hdfs and local system
```
# Load the data from HDFS Location -> move the file from original location to hive architecture local
#if you drop the data original file is lost
hive> load data inpath '/karthick/StudentData.csv' overwrite into table karthick;
=============================================================================
#Load from the Local File System
#Keep the original file from local file system
hive> load data local inpath '/home/ubh01/Desktop/StudentData.csv' overwrite into table karthick;
============================
```

### Partiton two type static and 
```
insert into table dummy select * from karthick;
```

### Create table by partitioned
```
hive> create table stat_part(
    > age int,
    > gender string,
    > name string, 
    > roll int,
    > marks string,
    > email string
    > )partitioned by (course string);
```
### Static partioned default
```

hive> insert into table stat_part partition(course = 'DB') select age,gender,name,roll, marks,email from karthick where course = 'DB';
```
### Dynamic partition
```

create table dyna_part(
age int,
gender string,
name string,
roll int,
marks string,
email string
)partitioned by (course string);

 

set hive.exec.dynamic.partition.mode=nonstrict;

 

insert into table dyna_part partition(course) select age,gender,name,roll, marks,email,course from karthick where course = 'DB';
# dynmaic partion does not required column name 
```

### Partioning work on only if it has unique data or column while here in bucketing we working with non unique value through hashing 
```
select distinct(age) from priyanshu;


create table priyanshu_buck(
age int,
gender string,
name string,
course string,
roll int,
marks int,
email string) 
clustered by(age) into 2 buckets stored as textfile;

create table priyanshu_buck(
age int,
gender string,
name string,
course string,
roll int,
marks int,
email string) 
clustered by(age) into 2 buckets stored as textfile;

insert into priyanshu_buck select * from priyanshu;

create table priyanshu_buck1(
age int,
gender string,
name string,
course string,
roll int,
marks int,
email string) 
clustered by(course) into 6 buckets stored as textfile;
```
