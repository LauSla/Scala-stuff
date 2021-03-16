import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readLine

object GuessingGame extends App {

  val r = scala.util.Random
  val secretNum = r.nextInt(100)+1
  var playing = true
  val allGuesses: ArrayBuffer[Int] = ArrayBuffer()
  println(secretNum)

  def numOfGuesses (allGuesses:ArrayBuffer[Int]): Int = {
    var numGuesses = 0
    for ((v, i) <- allGuesses.zipWithIndex) {
      try {
        if (v != allGuesses(i+1)) numGuesses += 1
      }
      catch
        {
          case unknown => numGuesses += 1
        }
    }
    numGuesses
  }

  while (playing) {
    var guess = readLine("Make a guess: ").toInt
    allGuesses += guess
    if (guess < secretNum) println("Too small!")
    if (guess > secretNum) println("Too big!")
    if (guess == secretNum) {
      println("You guessed correctly!")
      println(s"You made ${allGuesses.toSet.size} unique guesses") // 100, 100, 90, 100, (93) = 3 guesses
      println(s"You made ${numOfGuesses(allGuesses)} non consequent unique guesses") // 100, 100, 90, 100, (93) = 4 guesses
      playing = false
    }
  }

}
