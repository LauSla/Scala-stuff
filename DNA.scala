object DNA extends App{

  //DNA to RNA converter
  def toRNA(dna: String):String = {
  val rna = for (c <- dna) yield {
    c match {
      case 'A' => 'U'
      case 'G' => 'C'
      case 'C' => 'G'
      case 'U' => 'A'
        //need to deal with other/error cases
      }
  }
    rna
  }

  //bad name for function :) converts three letter segments into aminoacids
  def threeToProt(temp:List[String]):List[String] = {
    //val start = "AUG" //do we need to start with start?
    //here, smth like if string does NOT contain AUG - return error message or nothing?

    val protein = for (item <- temp) yield {
      item match {

        case "AUG" => "Met"

        case "UUU" | "UUC" => "Phe"
        case "UUA" | "UUG" | "CUU" | "CUC" | "CUA" | "CUG" => "Leu"
        case "AUU" | "AUC" | "AUA" => "Ile"
        case "GUU" | "GUC" | "GUA" | "GUG" => "Val"

        case _ => "Unfinished" //when all is defined, those "extra" 1 or two letters should just stay unmatched
    }
    }
    protein
  }

  //mRNA to protein converter
  def toProtein(mrna: String):List[String] = {   //

    //here we split mrna into three characters starting from 1st, 2nd and 3rd character

    val temp1 = mrna.grouped(3).toList
    val temp2 = mrna.slice(1,mrna.length).grouped(3).toList
    val temp3 = mrna.slice(2,mrna.length).grouped(3).toList

    temp3 //right now, getting out only the third "frame"
  }


  //here, testing the definitions above
  val testDNA = "AGAGCCU"
  println(toRNA(testDNA))

  val testMRNA = "UCAUGAUCUCGUAAGA" //from Khan example
  toProtein(testMRNA).foreach(println)
  threeToProt(toProtein(testMRNA)).foreach(println)
  

}
