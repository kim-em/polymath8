package polymath

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AdmissibleTest extends FlatSpec with ShouldMatchers {
  
  "isAdmissible" should "accept admissible sequences" in {	  
      import Admissible._

      Set(0,2).isAdmissible should equal(true)
      
      HenleyRichardsResidueSequence(10,5).isAdmissible should equal(true)
  }
  
  "isAdmissible" should "reject inadmissible sequences" in {	  
      import Admissible._

      Set(0,1).isAdmissible should equal(false)
      HenleyRichardsResidueSequence(100,5).isAdmissible should equal(false)
  }
  
}
