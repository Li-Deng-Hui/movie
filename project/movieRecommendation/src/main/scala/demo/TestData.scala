package demo

import caseclass.Ratings
import conf.AppConf
import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.sql.SaveMode

/**
 * User: LiDenghui
 * Date: 2021/4/9
 * Time: 20:28
 * */
object TestData extends AppConf{
  def main(args: Array[String]){
    val minPartitions = 8
    import sqlContext.implicits._
    val ratings = sc.textFile("src/main/resources/data/ratings.txt", minPartitions).filter { !_.endsWith(",") }
      .map(_.split(",")).map(x => Ratings(x(0).trim().toInt, x(1).trim().toInt, x(2).trim().toDouble, x(3).trim().toInt)).toDF()

    //ratings
    deleteOutPutPath("/tmp/ratings")
    ratings.write.mode(SaveMode.Overwrite).parquet("/tmp/ratings")
    hc.sql("drop table if exists ratings")
    hc.sql("create table if not exists ratings(userId int,movieId int,rating double,timestamp int) stored as parquet")
    hc.sql("load data inpath '/tmp/ratings' overwrite into table ratings")


    val count = hc.sql("select count(*) from ratings").first().getLong(0).toInt
    val context=hc.sql("select * from ratings").first()
    println("输出："+context.toString())
    val percent = 0.6
    val trainingdatacount = (count * percent).toInt
    val testdatacount = (count * (1 - percent)).toInt

    deleteOutPutPath("/tmp/trainingDataAsc")
    val trainingDataAsc = hc.sql(s"select userId,movieId,rating from ratings order by timestamp asc")
    trainingDataAsc.write.mode(SaveMode.Overwrite).parquet("/tmp/trainingDataAsc")
    hc.sql("drop table if exists trainingDataAsc")
    hc.sql("create table if not exists trainingDataAsc(userId int,movieId int,rating double) stored as parquet")
    hc.sql("load data inpath '/tmp/trainingDataAsc' overwrite into table trainingDataAsc")

    deleteOutPutPath("/tmp/trainingDataDesc")
    val trainingDataDesc = hc.sql(s"select userId,movieId,rating from ratings order by timestamp desc")
    trainingDataDesc.write.mode(SaveMode.Overwrite).parquet("/tmp/trainingDataDesc")
    hc.sql("drop table if exists trainingDataDesc")
    hc.sql("create table if not exists trainingDataDesc(userId int,movieId int,rating double) stored as parquet")
    hc.sql("load data inpath '/tmp/trainingDataDesc' overwrite into table trainingDataDesc")

    deleteOutPutPath("/tmp/trainingData")
    val trainingData = hc.sql(s"select * from trainingDataAsc limit $trainingdatacount")
    trainingData.write.mode(SaveMode.Overwrite).parquet("/tmp/trainingData")
    hc.sql("drop table if exists trainingData")
    hc.sql("create table if not exists trainingData(userId int,movieId int,rating double) stored as parquet")
    hc.sql("load data inpath '/tmp/trainingData' overwrite into table trainingData")

    deleteOutPutPath("/tmp/testData")
    val testData = hc.sql(s"select * from trainingDataDesc limit $testdatacount")
    testData.write.mode(SaveMode.Overwrite).parquet("/tmp/testData")
    hc.sql("drop table if exists testData")
    hc.sql("create table if not exists testData(userId int,movieId int,rating double) stored as parquet")
    hc.sql("load data inpath '/tmp/testData' overwrite into table testData")
  }
}
