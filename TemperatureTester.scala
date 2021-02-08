import scala.io.StdIn.readLine

object TemperatureTester extends App {
  //TODO write a program which asks for user's temperature
  //if Temperature is over 37C print a warming about high temperature
  //if temperature is under 35 print a warning about being cold
  //otherwise print that everything is great :0

  val myTemp = readLine("What is your temperature? ").toFloat //convert to number
  if (myTemp >= 37) {
    println("You've got fever!")
  }
  else if (myTemp <= 35) {
    println("You've got hypothermia!")
  }
  else {
    println("You're doing great!")
  }
}