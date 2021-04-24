package streaming


import conf.AppConf
import kafka.serializer.StringDecoder
import org.apache.spark.sql._
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel

object SparkDirectStream extends AppConf {
  def main(args: Array[String]) {
    val batchDuration = new Duration(5)
    val ssc = new StreamingContext(sc, batchDuration)
    val validusers = hc.sql("select * from trainingData")
    val userlist = validusers.select("userId")

    val modelpath = "/tmp/bestmodel/0.8215454233270015"
    val broker = "master:9092"
    val topics = "test".split(",").toSet
    val kafkaParams = Map("bootstrap.servers" -> "master:9092")
    
    def exist(u:Int):Boolean = {
      val userlist = List(hc.sql("select distinct(userid) from trainingdata").rdd.map(x => x.getInt(0)))
      userlist.contains(u)
    }
    
    //为没有登录的用户推荐电影有不同的策略：
    //1.推荐观看人数较多的电影，这里采用这种策略
    //2.推荐最新的电影
    def recommendPopularMovies() = {
      hc.sql("select * from top5DF").show
    }

    //创建SparkStreaming接收kafka消息队列数据的2种方式
    //一种是Direct approache,通过SparkStreaming自己主动去Kafka消息队
    //列中查询还没有接受进来的数据，并把他们拿到sparkstreaming中。pull
    val kafkaDirectStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)
    val messages = kafkaDirectStream.foreachRDD { rdd =>
      //      val userrdd = rdd.map(x => x._2.split("|")).map(x => x(1)).map(_.toInt)
      //      rdd.foreach{record =>
      //        val user = record._2.split("|").apply(1).toInt
      //        val model = MatrixFactorizationModel.load(sc,modelpath)
      //        val recresult = model.recommendProducts(user, 5)
      //        println(recresult)
      //      }
      rdd.foreachPartition { partition =>
        val userlist = partition.map(x => x._2.split("|")).map(x => x(1)).map(_.toInt)
        val model = MatrixFactorizationModel.load(sc, modelpath)
        while (userlist.hasNext) {
          val user = userlist.next()
          if (exist(user)) {
            val recresult = model.recommendProducts(user, 5)
            println("below movies are recommended for you :")
            println(recresult)
          } else {
            println("below movies are recommended for you :")
            recommendPopularMovies()
          }
        }
      }
    }
  }
}

