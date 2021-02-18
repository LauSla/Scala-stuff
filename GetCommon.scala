import scala.collection.SortedSet
import scala.util.Random.nextInt

object GetCommon extends App{
  def getCommonElements(s1: Seq[Int], s2: Seq[Int], s3: Seq[Int]): Seq[Int] = {
    //TODO return sorted Sequence of common elements in all 3 sequences
    //Seq(0)

    (((s1.toSet).intersect(s2.toSet)).intersect(s3.toSet)).toSeq.sorted

  }

  val seq1 = Seq(5, 1, 2, 3, 4)
  val seq2 = Seq(5, 6, 7, 3, 4)
  val seq3 = Seq(5, 0, 8, 3, 1)

  println(getCommonElements(seq1, seq2, seq3))


  def isPangram(text: String, alphabet : String = "abcdefghijklmnopqrstuvwxyz"): Boolean= {
    //TODO determine if the sentence contains all English letters
    //https://en.wikipedia.org/wiki/Pangram
    // (case is not important here)
    //this function should work on other languages too, if we pass it different alphabet

    (alphabet.toSet).subsetOf((text.toLowerCase().toSet))
    //this order takes care of space, !, ? etc.
  }
  
  println(isPangram("The five boxing wizards jump quickly.")) //should be true
}