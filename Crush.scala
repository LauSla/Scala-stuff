import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Crush extends App {
  //TODO Solve the following problem
  //https://www.hackerrank.com/challenges/crush/problem
  //TODO read and parse ../resources/crush1.txt
  //TODO read and parse ../resources/crush2.txt
  //TODO read and parse ../resources/crush3.txt
  //TODO read and parse ../resources/crush4.txt


  def getLinesFromFile(srcPath: String) = {
    val bufferedSource = Source.fromFile(srcPath)
    val lines = bufferedSource.getLines.toArray
    bufferedSource.close
    lines
  }

  val crush1 = getLinesFromFile("src/resources/crush1.txt")
  val crush2 = getLinesFromFile("src/resources/crush2.txt")
  val crush3 = getLinesFromFile("src/resources/crush3.txt")
  val crush4 = getLinesFromFile("src/resources/crush4.txt")

  //here choose which input file to work with
  val thisCrush = crush3

  //get n and m out
  val n = thisCrush.slice(0,1).mkString.split(" ")(0).toInt //how many zeros to begin with
  val m = thisCrush.slice(0,1).mkString.split(" ")(1).toInt //how many operations


  //TODO do this brute force solution by loops

  //val seq = (1 to n).map(_ => 0)
  val myArray = ArrayBuffer.fill(n)(0)
  //println(seq)
  for (i <- 1 to m) {
    val currentLine = thisCrush.slice(i,i+1).mkString.split(" ")
    val startFrom = currentLine(0).toInt
    val goTo = currentLine(1).toInt
    val addValue = currentLine(2).toInt
    //extracts data from each subsequent input line
    //println(s"line $i -> $startFrom, $goTo, $addValue")

      for (j <- startFrom-1 to goTo-1) {
        myArray(j) += addValue
      }

      }

  println(myArray)
  println(myArray.max)


  //TODO think if you really need that many loops ...


}