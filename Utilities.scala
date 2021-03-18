import scala.io.Source

object Utilities {
  //not going to run it as such just use it for storing Utility functions/methods used in other objects / classes
  //I am using Object to store these because I do not need multiple copies
  def saveUrlToFile(url:String, folder:String = "./src/resources/"):Unit = {
    //TODO make a function which loads resource from url and
    import scala.io.Source
    val txtBuffer = Source.fromURL(url)
    // gets the last part of url and uses that to save into folder
    //you can use example from DownloadFiles
    val fName = url.split("/").last.split('.') //if 25880.txt is assumed, final split and next line not needed
    val fileName = fName(0) + "." + fName(1) //.org/ebooks/25880.txt.utf-8 third one is encoding
    //only challenge is to extract last part from the url and add it to folder
    val relative_save_path = "src/resources/" + fileName

    val lines = txtBuffer.getLines.toArray
    Utilities.saveLines(lines, relative_save_path)

  }
