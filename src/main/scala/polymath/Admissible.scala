package polymath

import Mod._

object Admissible {

  var lastPrimeIndex = 0

  implicit class IsAdmissibleSet(set: Traversable[Int]) {
    def width = set.max - set.min

    def isAdmissibleAt(p: Int) = {
      val bs = new scala.collection.mutable.BitSet(p)
      set.foreach(t => bs += t mod p)
      bs.size < p
    }

    def isAdmissible = {
      val otherPrimesChunked = {
        (((lastPrimeIndex + 1) until primes.indexWhere(_ > set.size)) ++ (0 until lastPrimeIndex)).grouped(1000).map(_.par)
      }

      isAdmissibleAt(primes(lastPrimeIndex)) && otherPrimesChunked.forall(_.forall({ i =>
        val p = primes(i)
        if (isAdmissibleAt(p)) {
          true
        } else {
          println("inadmissibility witnessed by p=" + p)
          lastPrimeIndex = i
          false
        }
      }))

    }
  }

}