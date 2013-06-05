package polymath

import scala.collection.mutable
import Mod._

trait ResidueSequence {
  def iterator: Iterator[Int]
  def size: Int = iterator.size
  def width: Int = iterator.max - iterator.min

  var residues: Array[Int] = null
  var lastPrimeIndex = 0

  protected def addResidueFor(t: Int) {
    residues(t mod primes(lastPrimeIndex)) += 1

  }
  protected def removeResidueFor(t: Int) {
    residues(t mod primes(lastPrimeIndex)) -= 1

  }
  protected def addResiduesFor(t: TraversableOnce[Int]) {
    for (n <- t) addResidueFor(n)
  }
  protected def removeResiduesFor(t: TraversableOnce[Int]) {
    for (n <- t) removeResidueFor(n)
  }

  private def isAdmissibleAt(i: Int) = {
    if (i != lastPrimeIndex) {
      val p = primes(i)
      val r = Array.fill(p)(0)
      for (n <- iterator) r(n mod p) += 1

      if (r.contains(0)) {
        true
      } else {
        residues = r
        lastPrimeIndex = i
        false
      }
    } else {
      //      val p = primes(i)
      //      val rr = Array.fill(p)(0)
      //      for (n <- iterator) rr(n mod p) += 1
      //      
      //      println(rr.toList)
      //      println(residues.toList)
      //      
      //      require(rr.toList == residues.toList)
      residues.contains(0)
    }
  }

  lazy val isAdmissible = {
    if (residues == null) {
      val p = primes(lastPrimeIndex)
      residues = Array.fill(p)(0)
      for (n <- iterator) residues(n mod p) += 1
    }

    val otherPrimesChunked = {
      (((lastPrimeIndex + 1) until primes.indexWhere(_ > size)) ++ (0 until lastPrimeIndex)).grouped(1000).map(_.par)
    }

    isAdmissibleAt(lastPrimeIndex) && otherPrimesChunked.forall(_.forall({ i =>
      if (isAdmissibleAt(i)) {
        true
      } else {
        println("   inadmissibility witnessed by p=" + primes(i))
        false
      }
    }))
  }
}