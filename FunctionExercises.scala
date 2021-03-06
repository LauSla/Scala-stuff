import scala.io.StdIn.readLine

object FunctionExercises extends App {
  def isPalindrome(text:String) = {
    //TODO actually check if the text reads the same from both ways
    //"alus ari ir sula" that's a palindrome
    //text.reverse should work :)
    text.toLowerCase().replaceAll(" ", "") == text.reverse.toLowerCase().replaceAll(" ", "")
  }

  val myString = "aluSARI ira sula"
  //val myString = readLine("What string shall we check? ")

  println()
  println(s"Is --> $myString <-- a palindrome? ${isPalindrome(myString)}")


  def getCityYear(p0: Double, perc:Double, delta:Int, p:Int) = {

    def getIncrease(start:Double) =  (start * perc / 100) + delta
    
    if (getIncrease(p0) > 0) {
      var currentPop = p0
      var i = 0
      do {
      currentPop += getIncrease(currentPop)
      i += 1
      } while (currentPop < p)
      i
    }
    else -1
  }
  //Write a function which takes 4 parameters p0, perc, delta, p
  //return integer of years when the population reaches p
  //yearly formula is previous year * percentage increas + delta
  //percentage is in percent so you will need to convert
  //delta is how many people leave or join the city every year
  //probably while loop will work best here
  //also consider that it is possible that city never reaches p, then you should return -1
  //hint: if population does not increase after first year it will never reach p

  println("Population: ")
  println(getCityYear(1000,2,50,1200)) //should print 3
  println(getCityYear(1000,2,-50,1200)) //should print -1
  println(getCityYear(1500000,2.5,10000,2000000)) //should print 10
}