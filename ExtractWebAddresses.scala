import java.io.FileWriter

object ExtractWebAddresses extends App {

  val poetry_path = "src/resources/poetry_1922.txt"

  val lines = Utilities.getLinesFromFile(poetry_path)

  println(s"We got ${lines.length} to filter through")
  //TODO Extract all lines with web addresses

  val webAddresses = lines.filter(line => (line.contains("www") || line.contains("http")))
  //webAddresses.foreach(println)

  //TODO only get the addresses without the extra text
var justSites: Array[String] = Array("")
  for (line <- webAddresses) {
    var tempLine = line.split(" ").filter(word => word.contains("."))
    //DOES NOT WORK
    justSites.concat(tempLine)
  }
  justSites.foreach(println)
  //TODO save them in a file

  //add new line character to each string for printing purposes

  val outputfile = "./src/websites.txt"
  val output = new FileWriter(outputfile ,true)
  webAddresses.map(_.concat("\n")).foreach(output.write)
  output.close()

  //TODO bonus find also the line index where the web address was found
  //webAddresses.foreach(println(s"${Utilities.findNeedle(lines, _)} _"))
  for (address <- webAddresses) {
        println(s"${Utilities.findNeedle(lines, address)} --> $address")
  }


}