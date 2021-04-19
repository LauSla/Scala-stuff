import java.io.{File, FileWriter}

import scala.io.Source
import scala.util.Try

object DNA extends App{

  //get DNA strings from online database search
  def getDataFromWeb(searchDNA: String) ={

    //get all the html text out of a website
    def getLinesFromFile(url: String): Array[String] = {
      val bufferedSource = Source.fromURL(url)
      val lines = bufferedSource.getLines.toArray
      bufferedSource.close
      lines
    }

    val searchString = "https://www.ncbi.nlm.nih.gov/nuccore/" + searchDNA + "?report=fasta"
    val data = getLinesFromFile(searchString)

    //the database search results do not allow direct access to DNA string
    //it is shown on site, but does NOT appear in page source code
    //DNA string can be accessed through unique id code
    //following code extracts this id code and gets DNA string from database

    val dataRow = data.filter(_.startsWith("  <div id=\"viewercontent1\"")).mkString
    val idCodeTemp = dataRow.slice(dataRow.indexOf(" val="), dataRow.indexOf(" SequenceSize="))
    val idCode = idCodeTemp.slice(6, idCodeTemp.length()-1)
    val searchById = "https://www.ncbi.nlm.nih.gov/sviewer/viewer.cgi?tool=portal&log$=seqview&db=nuccore&report=fasta&id="+ idCode+ "&extrafeat=null&conwithfeat=on&hide-cdd=on"

    val dataDNA = getLinesFromFile(searchById)

    //return two values: the name and the sequence of DNA
    val nameOfDNA = dataDNA.slice(0,1).mkString
    val sequenceOfDNA = dataDNA.slice(1,dataDNA.length).mkString

    (nameOfDNA, sequenceOfDNA)
  }

  //following 4 functions turn a DNA sequence into amino acid string (protein) it generates

  //converts input DNA to RNA
  def transcription(dna: String):String = {
  val rna = for (c <- dna) yield {
    c match {
      case 'A' => 'U'
      case 'G' => 'C'
      case 'C' => 'G'
      case 'T' => 'A'
        //TODO check changes here!
      case '_' => 'N'  //any other unusual inputs turn into N
      }
  }
    rna
  }

  //splits RNA into three character groups based on three frames
  //frames start from 1st, 2nd and 3rd character
  def toFrames(rna: String) = {

    val frame1 = rna.grouped(3).toList
    val frame2 = rna.slice(1,rna.length).grouped(3).toList
    val frame3 = rna.slice(2,rna.length).grouped(3).toList
      //TODO check changes here!
      //TODO You were combining these strings later on, easier to merge them here
      //TODO it's a list of strings, so if frame1 ends with, say, -UC-, it will still stay separate
    frame1 ++ frame2 ++ frame3
      //TODO - what happens, if [START] is in frame1 and [STOP] in frame2???
      //TODO Should we maybe NOT merge the frames, ever?
  }

  //converts three letter RNA segments into amino acids
  def translation(temp:List[String]):List[String] = {

    val protein = for (item <- temp) yield {
      item match {

        //case "AUG" => "[START] Met"

        //TODO check changes here!
        //TODO to simplify regex, got rid of [START], assuming Met's the starting sequence anyways
        case "AUG" => "Met"
        case "UAA" | "UAG" | "UGA" => "STOP"

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
          //TODO check changes here!
        case _ => "Undefined"//catches those "extra" 1 or two letters or any unusual input
    }
    }
    protein
  }

  //TODO check changes here!
  //Regex finds everything between Met (included) and -STOP (not included) sequences
  //Finds all such cases
  //TODO test on a case where several proteins are created!
  //Success(Met-Thr-Ser-Thr-Arg-Asn-Leu-Leu) <-this is output when a string IS found
  //Success() <-this is output, when a string is NOT found
  //we should be able to get rid of the extra stuff

  def realProtein(protein: String): Try[String] ={
    val Pattern = "Met.*?(?=\\b-STOP\\b)".r
    //val Pattern = "Met.*?(?=\\b-PSTOP\\b)".r  //this pattern will for sure make error, use for testing!
    Try(Pattern.findAllIn(protein).toList.mkString("\n"))
  }
  //if there is no real protein in the query, the programme gives error.
  //FIXED!

  //TODO fix the error.when there are multiple proteins made in a row, the start codon disappears, though it is known is there nonetheless.
  //This one, I need an example to get the idea. is it like, [START] hey [STOP] hey hey [STOP] would create TWO proteins, hey and hey hey hey?


//real program starts here
//******************************************

  //val searchDNA = readLine("Input DNA name/id: ")
  //val searchDNA = "MF945608.1"
  //val searchDNA = "NM_001126113.3"
  val searchRequest = "NM_001126113.3, MF945608.1"
  val searchDNAlist = searchRequest.split(", ")
  //TODO make splitting more flexible

  val outputfile = "./src/proteins.txt"
  val output = new FileWriter(new File (outputfile))

  for (searchDNA <- searchDNAlist) {

    val (nameOfDNA, currentDNA) = getDataFromWeb(searchDNA)
    val protein = translation(toFrames(transcription(currentDNA))).mkString("-")

    val proteininfo = nameOfDNA + "\n"+ realProtein(protein)
    //I think this "\n" bit will give separate lines

    //TODO each protein should be in a different line, not all the string together.
    //Test this one, see how it goes in case several proteins exist

    println(proteininfo)

    output.write(proteininfo)
  }

  output.close()

  //TODO this now creates a file and seems to rewrite the old one each time
  //Fix this somehow!



}
