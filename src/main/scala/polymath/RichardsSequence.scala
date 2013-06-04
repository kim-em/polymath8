package polymath

object RichardsSequence {
  def apply(m: Int) = {
    val h0 = Iterator(1) ++ primes.iterator.drop(m)
    h0.flatMap(x => Seq(x, -x)).toStream
  }
  
  def apply(m: Int, k: Int) = {
    val pv = primes.view
    
    val h0 = Seq(1, -1).view
    val h1 = pv.drop(m).take((k + 1) / 2 - 1)
    val h2 = pv.drop(m).take((k) / 2 - 1).map(-_)
    h0 ++ h1 ++ h2
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
}