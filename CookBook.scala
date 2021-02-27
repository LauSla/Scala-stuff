import scala.io.Source
import java.io.{BufferedWriter, File, FileWriter, PrintWriter}

import scala.sys.process.BasicIO.close

object CookBook extends App {
  //TODO import file through correct path
  //TODO maybe from online link even
  //TODO instead of building array each step, write to file directly

  //val currentDirectory = new java.io.File(".").getCanonicalPath
  //println(currentDirectory)

  val filename = "./src/13177-8.txt"
  val bufferedSource = Source.fromFile(filename, "UTF-8") //re-saved the txt file as UTF-8 before importing
  val lines = bufferedSource.getLines.toArray
  bufferedSource.close
  //val lines now has the cookbook

  // FileWriter
  val outputfile = "./src/recipes.txt"
  val output = new FileWriter(outputfile ,true)


  //var recipes: Array[String] = Array("")
  var counter: Int = 1

  //some relevant line flagging happens here:
  def isEmpty(index: Int): Boolean = {
    lines(index).length < 2
  }

  def isIndent(index: Int): Boolean = {
    lines(index).startsWith("   ")
  }

  def isUncooked(index: Int): Boolean = {
    lines(index).startsWith("(Unco")
  }

  def isAllCaps(index: Int): Boolean = {
    (lines(index).slice(0, 10) == lines(index).slice(0, 10).toUpperCase()) && !isEmpty(index) && !isIndent(index)
    //exclude also empty lines and AllCaps but indented lines - those are part of ingredient list
  }

  //recipes with ingredient lists end after this line in the book, so we stop checking
  for (i <- 0 until lines.indexOf("ESTABLISHED 1780")) {

    //look for 10 capital letters in the beginning of a non-indented non-empty line : COCOA CHARLOTTE (Without Cream)
    if (isAllCaps(i)) {
      //check contents of the line after AllCaps and then check if any indented lines follow
      //isIndent(i+7) lets us include CHOCOLATE ALMONDS, recipe Nr.10.
      if ((isEmpty(i + 1) || isUncooked(i + 1)) && (isIndent(i + 4) || isIndent(i + 5) || isIndent(i + 7))) {
        //recipes = recipes :+ "\n" :+ (s"$counter. " + lines(i))
        output.write("\n")
        output.write(s"$counter. " + lines(i).toString +"\n")
        counter += 1
      }
      //this second if could have been added to the first one through && but would have made the line even longer
    }

    //get all indented lines
    if (isIndent(i)) {
      //recipes = recipes :+ lines(i)
      //we could EXCLUDE all the lines with texts in capital (2nd BATCH, WHITE LAYER etc., but I kinda like them)
      output.write(lines(i).toString +"\n")
    }

    //Now the recipes Array is built, can print or save as needed
    //for (line <- recipes) {
      //println(line)
    }

  output.close()   //close the new text file

}