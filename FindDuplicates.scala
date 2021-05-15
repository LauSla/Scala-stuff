import java.io.FileWriter

import scala.io.Source

object FindFrequency extends App {
  val srcPath = "./src/resources/numbers.txt"
  val uniques = "./src/resources/uniques.txt"
  val frequencies = "./src/resources/frequencies.txt"
  println(s"Find unique numbers in $srcPath")
  //TODO find uniques -> save to $uniques
  //TODO save number usage frequencies in the following format
  //0 2
  //1 3
  //4 1
  //9 6 times etc

  def getLinesFromFile(srcPath: String) = {
    val bufferedSource = Source.fromFile(srcPath)
    val lines = bufferedSource.getLines.toArray
    bufferedSource.close
    lines
  }

  val numbers = getLinesFromFile(srcPath)
  val uniqueNumbers = numbers.toSet

  val output = new FileWriter(uniques ,true)
  uniqueNumbers.map(_.concat("\n")).foreach(output.write)
  output.close()

  val groupedNumbers = numbers.groupBy(identity).mapValues(_.size)
  //groupedNumbers.foreach(println)

  println(s"Unique numbers (Set) is ${uniqueNumbers.size}")
  println(s"Grouped numbers (groupBy) is ${groupedNumbers.size}")

  val output2 = new FileWriter(frequencies ,true)

  for ((key, value) <- groupedNumbers) {
    output2.write(s"$key $value" + "\n")
  }
  output2.close()


  //HINTS we can use Map
  //Also groupBy might help
}