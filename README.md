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
