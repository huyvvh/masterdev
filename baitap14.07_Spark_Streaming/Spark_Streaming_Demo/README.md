# Spark-Streaming Application
## Structure of folders
**1. Enviroment**
   - Java 8
   - Scala 2.11.12
   - Spark 2.4.0
   - Protobuf version 2.


**2. Requirement**
   - Tạo protobuf message file
   - Build thafnh java file
   - Viết chương trình java đẩy dữ liệu random gen ra 
        Đẩy dữ liệu dạng protobuf vào topic kafka **data_tracking_huyv20**
   - Viết 1 chương trình spark streaming Consumer dữ liệu từ topic bên trên
        Đẩy dữ liệu vào folder **/user/huyvv20/data_tracking/year=?/day=?/hour=?**
        Với year, day, hour parsing từ field timestamp. Dữ liệu write dưới dạng parquet, với thuật toán nén là snappy

**3. Structure of folders**
   - Main file : src/main/java/spark_streaming/StreamingData.java
   - Jar file : target/Spark_Streaming_Demo-1.0-SNAPSHOT.jar
   - Output file : http://172.17.80.27:9870/explorer.html#/user/huyvv20/output
   - Checkpoint file : http://172.17.80.27:9870/explorer.html#/user/huyvv20/checkpoint


**4. Running steps**
   ```
    Step 1: JAR file được lưu trên server 172.17.80.27 user hadoop 
            Path : /home/hadoop/huyvv20/Spark_Streaming_Demo-1.0-SNAPSHOT.jar
    Step 2: Edit command and run 
      - Run command :  spark-submit --deploy-mode cluster 
                            --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.4.0,com.google.protobuf:protobuf-java:3.15.8 
                            --class spark_streaming.StreamingData Spark_Streaming_Demo-1.0-SNAPSHOT.jar
   ```
