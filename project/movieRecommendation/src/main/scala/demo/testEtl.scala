package demo

import conf.AppConf
import demo.TestData.{deleteOutPutPath, hc}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.sql.hive.HiveContext

/**
 * User: LiDenghui
 * Date: 2021/4/9
 * Time: 13:04
 * */
object testEtl extends AppConf{
  def main(args: Array[String]) {
    val count = hc.sql("select count(*) from trainingDataDesc").first().getLong(0).toInt
    val context=hc.sql("select userId,movieId,rating from trainingDataDesc").first()
    println("输出："+count)
    println("输出："+context.toString())
  }
}
