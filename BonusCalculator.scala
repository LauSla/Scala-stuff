import scala.io.StdIn.readLine

object BonusCalculator extends App {
  //TODO Calculate yearly Xmas Bonus
  //Ask for Employee Name
  val employee = readLine("What is your name? ")
  println(s"Nice to talk to you $employee!")

  //Ask for how long they have worked
  val worked_for = readLine("How many years have you worked here for? ").toFloat //convert to number
  //ask for monthly wage
  val salary = readLine(s"And how much do we pay you, $employee? ").toFloat //convert to number
  //Calculate Xmas bonus if they have worked at least 2  years
  //Bonus is years worked over 2 years * 15% of monthly wage
  //so 5 years worked, 1000 Euros wage would give 450 Euro bonus
  val bonus = salary * (worked_for.floor - 2) * 0.15 //.floor rounds it down, count FULL years only

  if (worked_for < 2) {
    println("Sorry, no bonus for you!")
  }
  else {
    println(s"$employee, your bonus is $bonus!")
  }
}