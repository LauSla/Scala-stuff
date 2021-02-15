import scala.io.StdIn.readLine

object CubeTable extends App {
  //TODO write a program that takes user input for starting and end values
  //then saves cubes of those values in a Seq data type and then prints the cubes out
  val begVal = readLine("What is the starting value? ").toInt //convert to number
  println(s"Will print cubes starting with cube for $begVal")
  val endVal = readLine("What is the end value? ").toInt //convert to number
  println(s"Will print cubes up until cube for $endVal")

  for (i <- begVal to endVal) {
    println(s"$i cube = ${i*i*i}")
  }
}