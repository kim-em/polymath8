package polymath

import scala.collection.mutable

object Sieve {
  def apply(residueClass: Int => Int)(until: Int): Set[Int] = {
    val bs = new mutable.BitSet(until)
    for(n <- 0 until until) bs += n // TODO there's probably a faster way to do this
    for(p <- primes.takeWhile(_ < until)) {
      for(n <- residueClass(p) until until by p) {
        bs += n
      }
    }
    bs.toImmutable
  }
}