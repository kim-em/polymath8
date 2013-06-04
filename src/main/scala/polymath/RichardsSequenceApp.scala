package polymath

object RichardsSequenceApp extends App {
  import Admissible._

  val k = if (args.size > 0) { args(0).toInt } else { 341640 }
  println("Looking for a Richards-Henley sequence of size " + k + " ...")
  val m = RichardsSequence.firstOfLength(k)
  println("m = " + m)
  val s = RichardsSequence(m, k)
  println("width = " + s.width)
}