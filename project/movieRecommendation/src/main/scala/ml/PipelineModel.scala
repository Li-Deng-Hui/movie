package ml

import conf.AppConf
import org.apache.spark.ml.recommendation._
import org.apache.spark.ml._

object PipelineModel extends AppConf{
  val trainData = hc.sql("select * from trainingData").withColumnRenamed("userid", "user").withColumnRenamed("movieid", "item")
  val testData = hc.sql("select * from testData").withColumnRenamed("userid", "user").withColumnRenamed("movieid", "item")
  val als = new ALS().setMaxIter(20).setRank(1).setRegParam(1.0)
  val p = new Pipeline().setStages(Array(als))
  val model = p.fit(trainData)
  val test = model.transform(testData).select("rating", "prediction")
  val MSE = test.rdd.map(x => math.pow(x.getDouble(0) - x.getFloat(1),2)).mean()
  val RMSE = math.sqrt(MSE)
  model.save("/tmp/ml/ALSmodel")
}