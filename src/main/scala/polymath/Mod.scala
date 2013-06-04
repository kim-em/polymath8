package polymath

object Mod {
	implicit class ModdableInt(x: Int) {
	  def mod(k: Int) = {
//	    require(k != 0)
	    if(x < 0) {
	      -((-x-1) % k) + k - 1
	    } else {
	      x % k
	    }
	  }
	}
	implicit class ModdableLong(x: Long) {
	  def mod(k: Long) = {
//	    require(k != 0)
	    if(x < 0) {
	      -((-x-1) % k) + k - 1
	    } else {
	      x % k
	    }
	  }
	}
}