package com.movieRecommendation.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.springframework.stereotype.Component

/**
 * User: LiDenghui
 * Date: 2021/4/1
 * Time: 9:33
 * */
@Component
class WordCount {
  def start() {
    val inputFile =  "src/main/resources/data/spark.txt"
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile(inputFile)
    val wordCount = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)
    wordCount.foreach(println)
  }
}
