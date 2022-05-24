# Notes

- Hadoop marreduce local disk answer search local in find [local](https://www.guru99.com/introduction-to-mapreduce.html)
- Apache Spark [spark](https://www.tutorialspoint.com/apache_spark/apache_spark_introduction.htm)
- DAG & RDD [Architecture](https://www.javatpoint.com/apache-spark-architecture)
- Spark Streaming [link](https://spark.apache.org/docs/latest/streaming-programming-guide.html)
- Spark Tutorial With Scala [link](https://supergloo.com/spark-tutorial/spark-tutorials-scala/)
- Pig Installation [link](https://hiberstack.com/install-apache-pig-in-ubuntu/)

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

# Hive 
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
### Notes shared by sir
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

### After partioning and bucketing to know your work is done quit hive and move to normal shell
### Location to check
```
hadoop fs -cat /user/hive/warehouse/name_table/000000_0 
hadoop fs -cat /user/hive/warehouse/name_table/000001_0 
so on
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


# Apache Hbase [refer](https://www.tutorialspoint.com/hbase/hbase_create_table.htm)

### Here we use nosql database which is non-tabular database because now a days data originate in multiple format

**HBase is a column-oriented non-relational database management system that runs on top of Hadoop Distributed File System (HDFS). HBase provides a fault-tolerant way of storing sparse data sets, which are common in many big data use cases.**

### NOSQL
**NoSQL is used for Big data and real-time web apps. For example, companies like Twitter, Facebook and Google collect terabytes of user data every single day. NoSQL database stands for “Not Only SQL” or “Not SQL.” Though a better term would be “NoREL”, NoSQL caught on. Carl Strozz introduced the NoSQL concept in 1998**

### Command to start
```
>> cd $HBASE_HOME/bin/
>> start-hbase.sh
>> hbase shell
```

### Table_help
```
hbase(main):008:0> table_help
Help for table-reference commands.

You can either create a table via 'create' and then manipulate the table via commands like 'put', 'get', etc.
See the standard help information for how to use each of these commands.

However, as of 0.96, you can also get a reference to a table, on which you can invoke commands.
For instance, you can get create a table and keep around a reference to it via:

   hbase> t = create 't', 'cf'

Or, if you have already created the table, you can get a reference to it:

   hbase> t = get_table 't'

You can do things like call 'put' on the table:

  hbase> t.put 'r', 'cf:q', 'v'

which puts a row 'r' with column family 'cf', qualifier 'q' and value 'v' into table t.

To read the data out, you can scan the table:

  hbase> t.scan

which will read all the rows in table 't'.

Essentially, any command that takes a table name can also be done via table reference.
Other commands include things like: get, delete, deleteall,
get_all_columns, get_counter, count, incr. These functions, along with
the standard JRuby object methods are also available via tab completion.

For more information on how to use each of these commands, you can also just type:

   hbase> t.help 'scan'

which will output more information on how to use that command.

You can also do general admin actions directly on a table; things like enable, disable,
flush and drop just by typing:

   hbase> t.enable
   hbase> t.flush
   hbase> t.disable
   hbase> t.drop

Note that after dropping a table, your reference to it becomes useless and further usage
is undefined (and not recommended).

```
### Command to test
```
>> status
>> version
>> whoami
>> table_help
```

### list to get the list of table numbers
```
>> list
```
### Create table sc-tb1 is table name while tb1 is the table 1 and cf1 means column family
```
>> create 'sc-tb1' , 'tb1-cf1'
```

### if you have already created the table, you can get a reference to it
```
>> get_table 'sc-tb1'
```

# Apache Kafka [referal](https://kafka.apache.org/quickstart)
```
>> cd $KAFKA_HOME
>> follow the referal
```

# Spark [Socket Streaming](https://spark.apache.org/docs/latest/streaming-programming-guide.html)

### File Stream
```
>>  nano cdb.py
import pyspark
from pyspark import SparkConf, SparkContext
con = SparkConf().setAppname("Read File")
sc = SparkContext.getOrCreate()
demo = sc.parallelize([1,2,3,4,5,6])
print(demo.collect())
# to run the code
>>  spark-submit cdb.py

```

### Command to run wordcount streaming 
```
from pyspark import SparkContext
from pyspark.streaming import StreamingContext

## Create a local StreamingContext with two working thread and batch interval of 1 seco$
sc = SparkContext()
ssc = StreamingContext(sc, 10)
# Create a DStream that will connect to hostname:port, like localhost:9999
lines = ssc.socketTextStream("localhost", 9999)
# Split each line into words
words = lines.flatMap(lambda line: line.split(" "))
# Count each word in each batch
pairs = words.map(lambda word: (word, 1))
wordCounts = pairs.reduceByKey(lambda x, y: x + y)

# Print the first ten elements of each RDD generated in this DStream to the console
wordCounts.pprint()
ssc.start()             # Start the computation
ssc.awaitTermination()  # Wait for the computation to terminate

```
### To run port on different terminal 

```
>> nc -lk 9999
write any message here and press enter
```

### Socket Streaming
```
# Databricks notebook source
# Picking up live data
import pyspark
from pyspark import SparkConf, SparkContext
from pyspark.streaming import StreamingContext

# COMMAND ----------

conf = SparkConf().setAppName("File streaming")
sc = SparkContext.getOrCreate(conf=conf)

# COMMAND ----------

# 10 sec streaming gap
ssc = StreamingContext(sc,10)

# COMMAND ----------

rdd = ssc.textFileStream("/FileStore/tables/")

# COMMAND ----------

rdd = rdd.map(lambda x: (x,1))
rdd = rdd.reduceByKey(lambda x,y: x+y)
rdd.pprint()

# COMMAND ----------

# 100000 second till
ssc.start()
ssc.awaitTerminationOrTimeout(100000)

```

# Pig

### Download

```
>>  mkdir pig
>>  cd pig/
>>  wget https://downloads.apache.org/pig/pig-0.17.0/pig-0.17.0.tar.gz
>>  tar -xvf pig-0.17.0.tar.gz
>>  cd
>>  nano .bashrc

Add this in bottom of bachrc
____________
#JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
#Apache Pig Environment Variables
export PIG_HOME=/home/hiberstack/pig/pig-0.17.0
export PATH=$PATH:/home/hiberstack/pig/pig-0.17.0/bin
export PIG_CLASSPATH=$HADOOP_HOME/conf

export PIG_HOME=/home/ubh01/pig/pig-0.17.0
export PATH=$PATH:/home/ubh01/pig/pig-0.17.0/bin
export PIG_CLASSPATH=$HADOOP_HOME/conf


#JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
____________
# save ctrl + x and than Y

>>  source .bashrc

# to check
>>  pig -version
```

### Start Apache Pig

When we start Apache Pig, it opens a grunt shell. We can start Apache Pig in 2 execution modes as below

**(a) Local Mode:** In Local mode, the execution of Pig commands will be performed on the local file system. Files will be read and written from and into the local file system only rather than HDFS. We can start Pig in Local Mode with the below command
```
pig -x local
```
**(b) MapReduce Mode:** In this mode, the Pig commands will be executed on the files present on HDFS. The file will be read from and written into HDFS. This is the default mode of Pig. We can start Pig in MapReduce Mode with the below commands
```
pig
```
**OR**
```
pig -x mapreduce
```


### Pig command
```
student = load '/home/ubh01/Desktop/StudentData.csv' using PigStorage(',') as (age: int, gender:chararray, name:chararray, course:chararray, roll:chararray, marks:int, email:chararray);
```

### Display the output
```
dump student
```
### for each
```
details = foreach student generate roll,name,age,gender;
```
### Filter
```
female = filter details by gender == 'Female';
```
### with more than one condution
```
female = filter details by gender == 'Female' and age == 28;
```

### order
```
    rollorder = order details by roll asc;
    rollorder = order details by roll desc;
    rollorder = order details by roll;
```

### Pig
```
    store rollorder into '/home/ubh01/Desktop/rollorderkarthick' using PigStorage(',');

    store rollorder into '/home/ubh01/Desktop/rollorderkarthick' using PigStorage(':');

    store rollorder into '/home/ubh01/Desktop/rollorderkarthick' using PigStorage(',','-schema');

    karthick = load '/home/ubh01/Desktop/rollorderkarthick/part-r-00000' using PigStorage(',');
 ```

