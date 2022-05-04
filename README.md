# Sqoop

# passwrd
```
Welcome!12345
```
# load file from mysql to vm hdfs
-m 1 for primary key
```
sqoop-import --connect jdbc:mysql://cdb22dw011.c0lf9xyp8cv9.ap-south-1.rds.amazonaws.com/cdb21dw011 --username cdb22dw011 -P --table StudentData --target-dir /StudentData1 -m 1
```


# Using select
```
sqoop-import --connect jdbc:mysql://cdb22dw011.c0lf9xyp8cv9.ap-south-1.rds.amazonaws.com/cdb21dw011 --username cdb22dw011 -P --table StudentData --where "gender='Female'" --target-dir /StudentDataFemale -m 1
```
