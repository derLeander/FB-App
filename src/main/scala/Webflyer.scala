package org.leander

import dispatch._, Defaults._
import org.jsoup.Jsoup

import scala.concurrent.Future
import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery
import scala.slick.driver.SQLiteDriver.api._


object Webflyer {
  def distance(start: String, end: String): Future[Int] = {
    val svc = url("http://www.webflyer.com/travel/mileage_calculator/getmileage.php")
      .addQueryParameter("city", start)
      .addQueryParameter("city", end)
    Http(svc OK { doc =>
      val text = Jsoup.parse(doc.getResponseBody)
        .select("tr.row_odd_bg:nth-child(5) > td:nth-child(2) > b:nth-child(1) > span:nth-child(1)")
        .text()
      text.split(" ")(0).toInt
    })
  }
}

class CachingWebflyer(db: Database) {
  def distance(start: String, end: String): Future[Int] = {
    val distances = TableQuery[Distances]
    db.run(distances.filter(_.departure === start).filter(_.arrival === end).map(_.miles).result.headOption).map(result =>
      val otherwise = Webflyer.distance(start, end)
      // Int here
    )
  }
}
