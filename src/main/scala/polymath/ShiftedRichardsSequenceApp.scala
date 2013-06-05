package polymath

import java.io.PrintWriter
import java.io.FileOutputStream
import java.io.File

object ShiftedRichardsSequenceApp extends App {
  import Admissible._

  val k = args(0).toInt

  val (m, j) = if (args.length > 1) {
    val m0 = args(1).toInt
    println("Looking for the best shift j so that the shifted Richards-Henley sequence of size " + k + ", offset " + m0 + " and shift j is admissible")
    val jO = RichardsSequence.bestShift(m0, k)
    jO match {
      case None =>
        println("None found"); System.exit(1); (0, 0)
      case Some(j) => (m0, j)
    }
  } else {
    println("Looking for the best shifted Richards-Henley sequence of size " + k)
    val (m0, j0) = RichardsSequence.bestOfLength(k)
    println("m = " + m0)
    (m0, j0)
  }

  println("j = " + j)
  val s = RichardsSequence.shifted(m, j, k)
  println("width = " + s.width)

  val out = new PrintWriter(new FileOutputStream(new File("rh")))
  for (n <- s) out.println(n)
  out.close

}