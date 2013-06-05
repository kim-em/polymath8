package polymath

import java.io.PrintWriter
import java.io.FileOutputStream
import java.io.File

case class HenleyRichardsResidueSequence(override val size: Int, m: Int, j: Int = 0) extends ResidueSequence {

  // describe the difference between this sequence and that
  private def diff(that: HenleyRichardsResidueSequence): (Iterator[Int], Iterator[Int]) = {
    def toAdd1 = ((m until that.m).iterator ++ ((that.indexA + 1) to indexA).iterator).map(primes)
    def toAdd2 = ((m until that.m).iterator ++ ((that.indexB + 1) to indexB).iterator).map(primes).map(-_)
    def toRemove1 = ((that.m until m).iterator ++ ((indexA + 1) to that.indexA).iterator).map(primes)
    def toRemove2 = ((that.m until m).iterator ++ ((indexB + 1) to that.indexB).iterator).map(primes).map(-_)

//    println("to add: ")
//    
//    println(Seq(m, that.m, indexA, that.indexA, indexB, that.indexB))
//    println((toAdd1 ++ toAdd2).toList)
//    println(iterator.toSet.diff(that.iterator.toSet))
//    println((toRemove1 ++ toRemove2).toList)
//    println(that.iterator.toSet.diff(iterator.toSet))
    
//    require((toAdd1 ++ toAdd2).toSet == iterator.toSet.diff(that.iterator.toSet))
//    require((toRemove1 ++ toRemove2).toSet == that.iterator.toSet.diff(iterator.toSet))
    
    //    (iterator.toSet.diff(that.iterator.toSet).iterator, that.iterator.toSet.diff(iterator.toSet).iterator)
    (toAdd1 ++ toAdd2, toRemove1 ++ toRemove2)
  }

  // update the residue classes
  if (HenleyRichardsResidueSequence.last != null) {
    residues = HenleyRichardsResidueSequence.last.residues
    lastPrimeIndex = HenleyRichardsResidueSequence.last.lastPrimeIndex
    val (add, remove) = diff(HenleyRichardsResidueSequence.last)
    addResiduesFor(add)
    removeResiduesFor(remove)
  }
  HenleyRichardsResidueSequence.last = this

  private def indexA = size / 2 - 1 - j + m - 1
  private def indexB = (size + 1) / 2 - 1 + j + m - 1

  override def width = primes(indexA) + primes(indexB)

  override def iterator = {
    val pv = primes.view

    val h0 = Array(1, -1).view
    val h1 = pv.drop(m).take((size + 1) / 2 - 1 + j)
    val h2 = pv.drop(m).take((size) / 2 - 1 - j).map(-_)
    val result = h0 ++ h1 ++ h2
    require(result.size == size)
    result.iterator
  }
}

object HenleyRichardsResidueSequence {
  private var last: HenleyRichardsResidueSequence = null
}

object HenleyRichards {
  def firstOfLength(k: Int): Int = {
    Iterator.from(0).find({ m => if(m % 100 == 0) println("  trying m=" + m); HenleyRichardsResidueSequence(k, m, 0).isAdmissible }).get
  }
  
  
  // not actually fast enough...
  def bestShiftedOfLength(k: Int) = {
    (for(m <- (0 until 400).iterator; j <- (0 until 100).iterator; s = { println((m,j)); HenleyRichardsResidueSequence(k, m, j)}; if s.isAdmissible) yield {
      (m, j, s.width)
    }).toSeq.sortBy(_._3).head
  }
}

object HenleyRichardsApp extends App {
  val k = args(0).toInt
  println("Looking for a Henley-Richards sequence of size " + k + " ...")
//  val m = HenleyRichards.firstOfLength(k)
//  val j = 0

  val (m, j, _) = HenleyRichards.bestShiftedOfLength(k)  
  
  println("m = " + m)
  println("j = " + j)

  val s = HenleyRichardsResidueSequence(k, m, 0)
  println("width = " + s.width)
  
  val out = new PrintWriter(new FileOutputStream(new File("HenleyRichards_" + k + "_" + m + ".out")))
  for(n <- s.iterator) out.println(n)
  out.close
}