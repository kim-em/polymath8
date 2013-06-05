package polymath

import scala.collection.SeqView

object RichardsSequence {
  def apply(m: Int) = {
    val h0 = Iterator(1) ++ primes.iterator.drop(m)
    h0.flatMap(x => Seq(x, -x)).toStream
  }

  def apply(m: Int, k: Int): Seq[Int] = {
    shifted(m, 0, k)
  }

  // take j extra positive primes, and j fewer negative primes
  def shifted(m: Int, j: Int, k: Int): Seq[Int] = {
    val pv = primes.view

    val h0 = Array(1, -1).view
    val h1 = pv.drop(m).take((k + 1) / 2 - 1 + j)
    val h2 = pv.drop(m).take((k) / 2 - 1 - j).map(-_)
    val result = h0 ++ h1 ++ h2
    require(result.size == k)
    result
  }

  def firstOfLength(k: Int) = {
    import Admissible._

    def report(m: Int) = {
      if (m % 500 == 0) {
        println("   trying m = " + m)
      }
    }

    Iterator.from(0).find({ m =>
      report(m)
      RichardsSequence(m, k).isAdmissible
    }).get
  }

  def firstShift(m: Int, k: Int) = {
    import Admissible._

    def report(j: Int) = {
      if (j % 50 == 0) {
        println("   trying j = " + j)
      }
    }

    (0 to (k / 2 - 1)).find({ j =>
      report(j)
      RichardsSequence.shifted(m, j, k).isAdmissible
    })
  }
  
  def bestShift(m: Int, k: Int) = {
    import Admissible._

    def report(j: Int) = {
      if (j % 50 == 0) {
        println("   trying j = " + j)
      }
    }

    val candidates = (0 to (k / 2 - 1)).iterator.map({j => report(j); (j, RichardsSequence.shifted(m, j, k)) }).filter(_._2.isAdmissible)
    if(candidates.isEmpty) {
      None
    } else {
      Some(candidates.maxBy(_._2.width)._1)
    }
  }

  
  // this code is not actually effective yet!
  def bestOfLength(k: Int) = {
    import Admissible._

    val m0 = firstOfLength(k)
    val pairs = (m0 to 0 by -1).iterator.map({ m =>
      println(" trying m = " + m)
      (m, bestShift(m, k))
    }).takeWhile(_._2.nonEmpty).map({ case (m, Some(j)) => (m, j) }).toSeq
    pairs.sortBy({ case (m, j) => RichardsSequence.shifted(m, j, k).width }).head
  }

}