package zxl.datacleaner

import zxl.ModelTrain.ItemCF._
import zxl.ModelTrain.{ItemSimilarity, ItemPref}
import zxl.caseclass.Users
import zxl.conf.AppConf
import org.apache.spark.sql.SaveMode

/**
  * 清洗user表中数据，保存至数据库
  *
  */
object UserETL extends AppConf {

  def main(args: Array[String]) {

    import sqlContext.implicits._

    // 2 读取样本数据
    val data_path = "hdfs://movie1:9000/movie/data/users.txt"
    val data = sc.textFile(data_path, 8)
    val userdata = data.map(_.split(",")).map(f => Users(f(0).toInt,f(1),f(2).toInt,f(3).toInt)).cache()

    val userDF = userdata.toDF()
    // 存储结果至数据库
    userDF.write.mode(SaveMode.Append).jdbc(jdbcURL, userTable, prop)
  }
}
