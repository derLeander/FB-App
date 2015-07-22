import org.scalatest._

import scala.concurrent.Await
import scala.concurrent.duration._

case class Distance(start: String, end: String, distance: Int)

class WebflyerTest extends FunSpec with Matchers {
  describe("distance calculations") {
    List(Distance("ZRH", "SIN", 6400)).foreach({ dist =>
      it(s"distance between ${dist.start} and ${dist.end} should be ${dist.distance}") {
        Await.result(Webflyer.distance(dist.start, dist.end), 30.seconds) should be(dist.distance)
      }
    })
  }
}
