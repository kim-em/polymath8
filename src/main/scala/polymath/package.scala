import java.io.File
import scala.io.Source
import java.util.zip.GZIPInputStream
import java.io.FileInputStream

package object polymath {

  lazy val primes = {
    val pf = new File("primes.m.gz")
    Source.fromInputStream(new GZIPInputStream(new FileInputStream(pf))).getLines.map(_.toInt).toArray
  }
  
}