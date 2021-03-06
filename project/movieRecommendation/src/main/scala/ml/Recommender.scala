package ml


import conf.AppConf
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel

object Recommender extends AppConf {
  def main(args: Array[String]) {
    //从trainingData中取得userid一定存在于模型中。
    val users = hc.sql("select distinct(userId) from trainingData order by userId asc")
    val index = 139
    val uid = users.take(index).last.getInt(0)

    val modelpath = "/tmp/bestmodel/0.8673463694260396"
    val model = MatrixFactorizationModel.load(sc, modelpath)
    val rec = model.recommendProducts(uid, 5)
    val recmoviesid = rec.map(_.product)
    println("我为用户" + uid + "推荐了以下5部电影：")
    for (i <- recmoviesid) {
     // val moviename = hc.sql(s"select title from movies where movieId=$i").first().getString(0)
      println(i)

    }


  }
}