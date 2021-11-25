import java.io.File
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

object Futures extends App {

  def getListOfFiles(dir: String):Future[List[String]] = Future{
    def getList(dire: File, a: Boolean): List[File] = {
      val d = new File(dir)
      if (d.exists && d.isDirectory) {
        dire.listFiles ++ dire.listFiles.filter(_.isDirectory).filter(_ => a).flatMap(getList(_, a))
      }.toList
      else List[File]()
    }
    val paths: List[File] = getList(new File("./src"), true)
    paths.asInstanceOf[List[String]]
  }

  val files = Await.result(getListOfFiles("./src"), 1 second)
  println(files)

}
