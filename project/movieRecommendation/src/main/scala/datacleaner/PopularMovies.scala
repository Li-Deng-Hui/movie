package datacleaner

import conf.AppConf
import org.apache.spark._
import org.apache.spark.rdd._
import org.apache.spark.sql._
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.hive._

object PopularMovies extends AppConf {
  val pop = hc.sql("select count(*) as c ,movieId from trainingData group by movieId order by c desc")
  //  val pop5 = pop.select("movieId").map(x=>x.getInt(0)).take(5)
  //      for (i <- pop5) {
  //      val moviename = hc.sql(s"select title from movies where movieId=$i").first().getString(0)
  //      println(moviename)
  //    }
  val pop5 = pop.select("movieId").limit(5)
  pop5.registerTempTable("pop5")
  val pop5result = hc.sql("select a.movieId,a.title from movies a join pop5 b where a.movieId=b.movieId")
  pop5result.write.mode(SaveMode.Overwrite).parquet("/tmp/pop5result")
  hc.sql("drop table if exists pop5result")
  hc.sql("create table if not exists pop5result(movieId int,title string) stored as parquet")
  hc.sql("load data inpath '/tmp/pop5result' overwrite into table pop5result")

}