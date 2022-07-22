package spark_streaming;

import kafka.DataTrackingOut;
import org.apache.kafka.common.errors.TimeoutException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.streaming.Trigger;
import org.apache.spark.sql.types.DataTypes;
import serializzer.Deserializer;

import static org.apache.spark.sql.functions.*;

public class StreamingData {

    public static void main(String[] args) throws StreamingQueryException, TimeoutException, java.util.concurrent.TimeoutException {
        SparkSession spark = SparkSession
                .builder()
                .appName("Kafka Streaming")
                //.master("local")
                .getOrCreate();

        UserDefinedFunction strLen = udf(
                (byte[] x) -> new Deserializer<>(DataTrackingOut.DataTracking.parser()).deserialize("data_tracking_huyvv20", x).convertToString(),
                DataTypes.StringType);

        spark.udf().register("strLen", strLen);

        Dataset<Row> df = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "172.17.80.26:9092")
                .option("subscribe", "data_tracking_huyvv20")
                .option("group.id", "protobuf-consumer-group1")
                .option("startingOffsets", "earliest")
//                .option("auto.offset.reset", "true")
                .load();

        Dataset<Row> df1 = df.selectExpr("CAST(key as string)", "strLen(value) as value", "CAST(topic as STRING)",
                        "CAST(offset as long)", "CAST(partition as long)")
                .select(col("key"),
                        split(col("value"), ",").getItem(0).as("version"),
                        split(col("value"), ",").getItem(1).as("Name"),
                        split(col("value"), ",").getItem(2).as("year"),
                        split(col("value"), ",").getItem(3).as("month"),
                        split(col("value"), ",").getItem(4).as("day"),
                        split(col("value"), ",").getItem(5).as("hour"),
                        split(col("value"), ",").getItem(6).as("PhoneId"),
                        split(col("value"), ",").getItem(7).as("Lon"),
                        split(col("value"), ",").getItem(8).as("lat"),
                        col("topic"),
                        col("partition"),
                        col("offset"));

        StreamingQuery qr = df1.writeStream()
                .option("partition", 1)
                .outputMode("append")
                .format("parquet")
                .option("compression", "snappy")
                .option("checkpointLocation", "/user/huyvv20/checkpoint")
                .option("path", "/user/huyvv20/output")
                .partitionBy("year", "month", "day", "hour")
                .trigger(Trigger.ProcessingTime(20000))
                .start();
        qr.awaitTermination();
    }
}

