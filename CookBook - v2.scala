import scala.io.Source
import java.io.{FileWriter}

object CookBook extends App {

  //TODO maybe from online link even

  val filename = "./src/13177-8.txt"
  val bufferedSource = Source.fromFile(filename, "UTF-8") //re-saved the txt file as UTF-8 before importing
  val lines = bufferedSource.getLines.toArray
  bufferedSource.close
  //val lines now has the cookbook

  // FileWriter
  val outputfile = "./src/recipes-v2.txt"
  val output = new FileWriter(outputfile ,true)

  //some relevant line flagging happens here:
  def isEmpty(text: String): Boolean = text.length < 2

  def isIndent(text: String): Boolean = text.startsWith("   ")

  def isAllCaps(text: String): Boolean = {
    (text.slice(0, 10) == text.slice(0, 10).toUpperCase()) && !isEmpty(text) && !isIndent(text)
    //exclude also empty lines and AllCaps but indented lines - those are part of ingredient list
  }

  def keep(text: String): Boolean = {
    isAllCaps(text) || isIndent(text)
  }

  //recipes with ingredient lists are between these lines in the book, so we stop checking
  val start = lines.indexOf("A FEW SUGGESTIONS IN REGARD TO CHOCOLATE")
  val end = lines.indexOf("WALTER BAKER & CO., Ltd.")

  lines.slice(start, end).filter(keep).map(_ + "\n").foreach(output.write)

  output.close()   //close the new text file

}