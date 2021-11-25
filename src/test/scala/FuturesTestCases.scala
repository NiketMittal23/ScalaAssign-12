import Futures.getListOfFiles
import org.scalatest.funsuite.AnyFunSuite
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class FuturesTestCases extends AnyFunSuite {
  val dir: Future[List[String]] = getListOfFiles("./src")
  test("if the directory present then the test pass") {
    for {
      response <- dir
    } yield {
      assert(response == List(""))
    }
  }
}