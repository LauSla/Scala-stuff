import scala.io.Source

object DNA extends App{

  //DNA to RNA converter
  def transcription(dna: String):String = {
  val rna = for (c <- dna) yield {
    c match {
      case 'A' => 'U'
      case 'G' => 'C'
      case 'C' => 'G'
      case 'T' => 'A'
        //need to deal with other/error cases
        //case '_' => '_'  //will this work? gap stays gap?
      }
  }
    rna
  }

  //converts three letter segments into aminoacids
  def translation(temp:List[String]):List[String] = {
    //val start = "AUG" //do we need to start with start?
    //here, smth like if string does NOT contain AUG - return error message or nothing?

    val protein = for (item <- temp) yield {
      item match {

        case "AUG" => "[START] Met"
        case "UAA" | "UAG" | "UGA" => "[STOP]" //this can be deleted, as it's handled in def usefulBits

        case "UUU" | "UUC" => "Phe"
        case "UUA" | "UUG" | "CUU" | "CUC" | "CUA" | "CUG" => "Leu"
        case "AUU" | "AUC" | "AUA" => "Ile"
        case "GUU" | "GUC" | "GUA" | "GUG" => "Val"
        case "UCU" | "UCC" | "UCA" | "UCG" => "Ser"
        case "CCU" | "CCC" | "CCA" | "CCG" => "Pro"
        case "ACU" | "ACC" | "ACA" | "ACG" => "Thr"
        case "GCU" | "GCC" | "GCA" | "GCG" => "Ala"
        case "UAU" | "UAC" => "Tyr"
        case "CAU" | "CAC" => "His"
        case "CAA" | "CAG" => "Gln"
        case "AAU" | "AAC" => "Asn"
        case "AAA" | "AAG" => "Lys"
        case "GAU" | "GAC" => "Asp"
        case "GAA" | "GAG" => "Glu"
        case "UGU" | "UGC" => "Cys"
        case "UGG" => "Trp"
        case "CGU" | "CGC" | "CGA" | "CGG" | "AGA" | "AGG" => "Arg"
        case "AGU" | "AGC" => "Ser"
        case "GGU" | "GGC" | "GGA" | "GGG" => "Gly"

        case _ => ""//those "extra" 1 or two letters should just stay unmatched
    }
    }
    protein
  }

  //TODO input data from DNA database
  //TODO can not do it directly, but maybe there is a workarround:
  //use regular search, get a secret ID number out of it, get raw data

  //TODO test it!
  //***************************************************************************
  println("HERE WE BUILD SEARCH STRINGS IN 2 STEPS")
  //val searchDNA = readLine("Input DNA name/id: ")
  val searchDNA = "MF945608.1"
  val searchString = "https://www.ncbi.nlm.nih.gov/nuccore/" + searchDNA + "?report=fasta"
  println("THIS WEBPAGE HAS A SECRET ID CODE!")
  println(searchString)

  //reused function that gets all the html text out of a website
  def getLinesFromFile(url: String): Array[String] = {
    val bufferedSource = Source.fromURL(url)
    val lines = bufferedSource.getLines.toArray
    bufferedSource.close
    lines
  }
  val data = getLinesFromFile(searchString)

  //println(data.mkString("\n"))
  val dataRow = data.filter(_.startsWith("  <div id=\"viewercontent1\"")).mkString
  println("THIS IS THE ROW WITH CODE")
  println(dataRow.mkString)
  val idCodeTemp = dataRow.slice(dataRow.indexOf(" val="), dataRow.indexOf(" SequenceSize="))
  val idCode = idCodeTemp.slice(6, idCodeTemp.length()-1)
  println("THIS IS THE FILTERED OUT CODE")
  println(idCode)
  val searchString2 = "https://www.ncbi.nlm.nih.gov/sviewer/viewer.cgi?tool=portal&log$=seqview&db=nuccore&report=fasta&id="+ idCode+ "&extrafeat=null&conwithfeat=on&hide-cdd=on"
  println("THIS NEW HTML STRING HAS DNA SEQUENCE")
  println(searchString2)

  val dataDNA = getLinesFromFile(searchString2)
  println("RAW DATA")
  println(dataDNA.mkString("\n"))
  println("TWO VALUES: NAME AND SEQUENCE")
  val nameOfDNA = dataDNA.slice(0,1).mkString
  println(nameOfDNA)
  val sequenceOfDNA = dataDNA.slice(1,dataDNA.length).mkString
  println(sequenceOfDNA)


  //finds START and END sequences in RNA sequences, exports only bits that need converting
  //TODO make this better! too likely to cause errors
  //this bit NOT IN USE for now! ignore!
  def usefulBits(input: List[String]):List[String] = {
  //we get the bit START to STOP
    var start = -1
    var stop = -1
    //finds the bits where START and STOP are located
    for ((str, idx) <- input.zipWithIndex) {
      if (str == "AUG") start = idx
      if (str == "UAA" | str =="UAG" | str =="UGA") stop = idx
      //assumes only ONE start and stop (will take LAST ones, if several present)
      //TODO deal with case when several starts and stops in one string
      //if there can be several stops and starts, we just keep indices in an arraybuffers
    }
    if (start == -1) stop = -1 //if no start, return empty
    val useful = input.slice(start, stop) //if -1,-1 returns empty - in cases of temp1 and temp2
    useful
  }

  //here we split mrna into three characters starting from 1st, 2nd and 3rd character
  def toFrames(mrna: String) = {   //

    val temp1 = mrna.grouped(3).toList
    val temp2 = mrna.slice(1,mrna.length).grouped(3).toList
    val temp3 = mrna.slice(2,mrna.length).grouped(3).toList

    (temp1, temp2, temp3)
  }


  //here, testing the definitions above
  //*******************************************************************************************
  val testDNA = "AGAGCCT"
  println(transcription(testDNA))

  val testMRNA = "UCAUGAUCUCGUAAGA" //from Khan example

  println("TESTING ON MF945608.1 STRING WE JUST GOT:")
  val (frame1, frame2, frame3) = toFrames(transcription(sequenceOfDNA))

  println(translation(frame1).mkString("-"))
  println(translation(frame2).mkString("-"))
  println(translation(frame3).mkString("-"))

  

}

