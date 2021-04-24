package demo

/**
 * User: LiDenghui
 * Date: 2021/4/6
 * Time: 16:59
 * */
object StringTag {
  def main(args: Array[String]) {
    val s="123,223,'123,333',332"
    var output=rebuild(s)
    output.foreach(print)
  }
  private def rebuild(input:String):String = {
    val a = input.split(",")
    val head = a.take(2).mkString(",")
    val tail = a.takeRight(1).mkString
    val b = a.drop(2).dropRight(1).mkString.replace("\"", "")
    val output = head + "--" + b + "--" + tail
    output
  }

}
