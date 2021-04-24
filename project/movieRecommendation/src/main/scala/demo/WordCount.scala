package demo

import org.apache.spark._

/**
 * User: Li Denghui
 * Date: 2021/3/30
 * Time: 22:44
 * */
object WordCount {
  def main(args: Array[String]) {
    val inputFile =  "src/main/resources/data/spark.txt"
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile(inputFile)
    val wordCount = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)
    wordCount.foreach(println)
  }
}