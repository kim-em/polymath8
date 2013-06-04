package polymath

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RichardsSequenceTest extends FlatSpec with ShouldMatchers {
  
  "apply" should "give the Richards-Henley sequence" in {	  
      RichardsSequence(5).take(10).toSet should equal(Set(1, -1, 13, -13, 17, -17, 19, -19, 23, -23))
      RichardsSequence(50).take(800).toSet should equal(RichardsSequence(50,800).toSet)
  }
  
  "firstOfLength" should "identify the first admissible Richards-Henley sequence of a given length" in {
    RichardsSequence.firstOfLength(100) should equal(16)
  }
  
}
