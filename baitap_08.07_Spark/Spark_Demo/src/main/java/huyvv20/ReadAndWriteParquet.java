package huyvv20;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class ReadAndWriteParquet {
    public static void main(String parquet[]){
        SparkSession sparkSession = SparkSession.builder().appName("READ_PARQUET").master("yarn").getOrCreate();
        Dataset<Row> dataset = sparkSession.read().parquet("/user/huyvv20/Sample_data/small_file.snappy.parquet");
        dataset.printSchema();
        dataset.createOrReplaceTempView("parquetFile");
        // Exercise 2.1
        Dataset<Row> device_model_num_user = sparkSession.sql("select device_model, count(distinct(user_id)) as count " +
                                                    "from parquetFile where device_model is not null " +
                                                    "group by device_model");
        device_model_num_user.repartition(1).write().mode("overwrite").save("/user/huyvv20/device_model_num_user");

        // Exercise 2.2
        Dataset<Row> device_model_list_user = sparkSession.sql("select device_model, collect_set(user_id) as list_user_id " +
                                                            "from parquetFile where device_model is not null " +
                                                            "group by device_model");
        device_model_list_user.repartition(1).write().mode("overwrite").orc("/user/huyvv20/device_model_list_user");

        // Exercise 4.1
        Dataset<Row> user_id_device_model = sparkSession.sql("select concat(user_id, '_', device_model) as user_id_device_model, button_id, count(*) as count " +
                                                                        "from parquetFile where button_id is not null and device_model is not null " +
                                                                        "group by user_id_device_model, button_id " );
        user_id_device_model.repartition(1).write().mode("overwrite").parquet("/user/huyvv20/button_count_by_user_id_device_model");

    }
}
