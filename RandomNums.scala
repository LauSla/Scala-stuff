object RandomNums extends App {
  //TODO generate a sequence of 100 random 2 dice throws so and then calculate the average and print frequency of each throw
  val r = scala.util.Random
  val myRandoms = for (i <- 1 to 20) yield ((r.nextInt(6)+1) + (r.nextInt(6)+1)) //so we want two dice throws - 100 of them
  println(myRandoms)

  println(s"Average throw value is ${myRandoms.sum/myRandoms.length.toDouble}")
  println(myRandoms.groupBy(identity)) //get a HashMap which kind of does this, but ugly

  //myRandoms.groupBy(identity) foreach {case (key, value) => println (key + "-->" + value)}
}