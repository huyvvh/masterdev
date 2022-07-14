import jdk.jfr.DataAmount
import org.apache.spark.sql.functions.{col, collect_list, collect_set, lit}
import org.apache.spark.sql.{DataFrame, SparkSession, functions}

object ReadAndWriteParquet {
  val sparkSession: SparkSession = SparkSession.builder().appName("READ_PARQUET_SCALA").getOrCreate()

  def Task1Scala(parquetFile : DataFrame): Unit = {
    val device_model_DF = parquetFile.filter("device_model is not null")
    val distinct_device_model_DF = device_model_DF.select("user_id", "device_model").distinct()
    val result_task1 = distinct_device_model_DF.groupBy("device_model").count().as("count")
    result_task1.repartition(1).write.mode("overwrite").parquet("/user/huyvv20/device_model_num_user")
  }

  def Task2Scala(parquetFile : DataFrame): Unit = {
    val device_model_DF = parquetFile.filter("device_model is not null")
    val distinct_device_model_DF = device_model_DF.select("user_id", "device_model").distinct()
    val result_task2 = distinct_device_model_DF.groupBy("device_model").agg(collect_set("user_id").as("list_user_id"))
    result_task2.repartition(1).write.mode("overwrite").orc("/user/huyvv20/device_model_list_user")
  }

  def Task3Scala(parquetFile : DataFrame): Unit = {
    val button_id = parquetFile.filter("device_model is not null and button_id is not null")
    val merge_collumn = button_id.select(functions.concat(col("user_id"), lit("_"), col("device_model")).as("user_id_device_model"), col("button_id"))
    val result_task3 = merge_collumn.groupBy("user_id_device_model","button_id").count()
    result_task3.repartition(1).write.mode("overwrite").parquet("/user/huyvv20/button_count_by_user_id_device_model")
  }

  def main (arg: Array[String]): Unit = {
    val inputFile = sparkSession.read.parquet("/user/huyvv20/Sample_data/small_file.snappy.parquet")
    Task1Scala(inputFile)
    Task2Scala(inputFile)
    Task3Scala(inputFile)
  }
}
