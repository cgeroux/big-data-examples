/* SimpleApp.scala */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SimpleApp {
  def main(args: Array[String]) {
    /*
    val logFile = "sampleArchive.txt" // Should be some file on your system
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(args(0), 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
    */
    val logFile = "sampleArchive.txt" // Should be some file on your system
    val outFile = "wordcount2_out"
    val conf = new SparkConf().setAppName("WordCount2")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile(logFile)
    val counts = textFile.flatMap(line => line.split(" "))
                 .map(word => (word, 1))
                 .reduceByKey(_ + _)
    counts.saveAsTextFile(args(1))
  }
}
/*build:  sbt package*/
/*run: spark-submit --class "SimpleApp" --master local[4] target/scala-2.10/simple-project_2.10-1.0.jar*/
