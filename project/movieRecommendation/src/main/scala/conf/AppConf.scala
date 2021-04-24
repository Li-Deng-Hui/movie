package conf

import org.apache.hadoop.fs.Path
import org.apache.spark._
import org.apache.spark.rdd._
import org.apache.spark.sql.{DataFrame, _}
import org.apache.spark.sql.hive._

import java.util.Properties

trait AppConf {
  val localClusterURL = "local[2]"
  val clusterMasterURL = "spark://master:7077"
  val conf = new SparkConf().setMaster(localClusterURL).setAppName("ETL")
  val sc = new SparkContext(conf)
  val sqlContext = new SQLContext(sc)
  val hc = new HiveContext(sc)

  //jdbc连接
  val jdbcURL = "jdbc:mysql://localhost:3306/movieRecommendation"
  val recResultTable = "t_result"
  val mysqlusername = "root"
  val mysqlpassword = "root"
  val prop = new Properties
  prop.put("driver", "com.mysql.jdbc.Driver")
  prop.put("user", mysqlusername)
  prop.put("password", mysqlpassword)
  def getDFFromMysql(sql: String): DataFrame = {
    sqlContext.read.format("jdbc")
      .option("url", jdbcURL)
      .option("user",mysqlusername)
      .option("password",mysqlpassword)
      .option("driver", "com.mysql.jdbc.Driver")
      .option("query", sql) .load()
  }

  def deleteOutPutPath(outputPath: String){
    val path = new Path(outputPath)
    val hadoopConf = sc.hadoopConfiguration
    val hdfs = org.apache.hadoop.fs.FileSystem.get(hadoopConf)
    if(hdfs.exists(path)){
      hdfs.delete(path,true)
    }
  }
}