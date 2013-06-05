package polymath

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RichardsSequenceTest extends FlatSpec with ShouldMatchers {
  
  "apply" should "give the Richards-Henley sequence" in {	  
      HenleyRichardsResidueSequence(10, 5).iterator.toSet should equal(Set(1, -1, 13, -13, 17, -17, 19, -19, 23, -23))
  }
  
  "firstOfLength" should "identify the first admissible Richards-Henley sequence of a given length" in {
    HenleyRichards.firstOfLength(100) should equal(16)
  }
  
}
