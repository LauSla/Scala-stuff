import RandomNums.r

object TraitExercises extends App {
  //you can use RandomNums as inspiration
  //TODO generated sequence of 10 random frogs located in 0<=x<=10, 0<=y<=100
  val r = scala.util.Random

  val frog1 = new Frog(new Point(r.nextInt(11), r.nextInt(101)))
  val frog2 = new Frog(new Point(r.nextInt(11), r.nextInt(101)))
  val frog3 = new Frog(new Point(r.nextInt(11), r.nextInt(101)))
  val frog4 = new Frog(new Point(r.nextInt(11), r.nextInt(101)))
  val frog5 = new Frog(new Point(r.nextInt(11), r.nextInt(101)))



  //TODO generate 10 random rectangles with topLeft again from 0 to 100 both coordinates
  //TODO and bottom right coordinates being also from 0 to 100
  //TODO of course bottom right should be to the right and to the bottom of topLeft


  val leftpoint1 = new Point(r.nextInt(51), r.nextInt(51))
  val rightpoint1 = new Point(r.nextInt(51)+50, r.nextInt(51)+50)
  val rec1 = new Rectangle(leftpoint1, rightpoint1)
  val leftpoint2 = new Point(r.nextInt(51), r.nextInt(51))
  val rightpoint2 = new Point(r.nextInt(51)+50, r.nextInt(51)+50)
  val rec2 = new Rectangle(leftpoint2, rightpoint2)
  val leftpoint3 = new Point(r.nextInt(51), r.nextInt(51))
  val rightpoint3 = new Point(r.nextInt(51)+50, r.nextInt(51)+50)
  val rec3 = new Rectangle(leftpoint3, rightpoint3)

  //val frogs: Seq[Frog]
  val frogs = Seq(frog1, frog2, frog3, frog4, frog5)


  //val gardens: Seq[Rectangle]
  val gardens = Seq(rec1, rec2, rec3)
  //TODO create method that checks if frog is in the rectangle

  val checkFrogs = {
    //TODO for now just print which frogs live inside which gardens
  }
  //
}