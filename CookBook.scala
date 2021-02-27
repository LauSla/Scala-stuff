import scala.io.Source
import java.io.FileWriter

object CookBook extends App {

  //TODO maybe get txt from online link

  val filename = "./src/13177-8.txt"
  val bufferedSource = Source.fromFile(filename, "UTF-8") //re-saved the txt file as UTF-8 before importing
  val lines = bufferedSource.getLines.toArray
  bufferedSource.close
  //val lines now has the cookbook

  // FileWriter
  val outputfile = "./src/recipes-v3.txt"
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

  //recipes with ingredient lists are between these lines in the book, so we check only between
  val start = lines.indexOf("A FEW SUGGESTIONS IN REGARD TO CHOCOLATE")
  val end = lines.indexOf("WALTER BAKER & CO., Ltd.")

  val tempRecipes: Seq[String] = lines.slice(start, end).filter(keep).map(_ + "\n")

  var flag:Boolean = false
  for (i <- tempRecipes.indices)
   {
      if (isAllCaps(tempRecipes(i)) && isAllCaps(tempRecipes(i+1))) flag = false //i+1 does NOT go out of range
      else flag = true
      if (flag) {
        if (isAllCaps(tempRecipes(i))){ //if-else bit adds /n before and after each recipe title
          output.write("\n")
          output.write(tempRecipes(i))
          output.write("\n")
        }
        else output.write(tempRecipes(i))
      }
    }

  output.close()   //close the new text file

}