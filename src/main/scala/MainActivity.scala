package org.leander

import android.app.Activity
import android.util.Log
import android.os._
import java.sql.{Driver, DriverManager}
import android.database.sqlite._
import android.content._
import scala.slick.driver.SQLiteDriver.api._
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global


class MainActivity extends Activity {
  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)
    val driver = "org.sqldroid.SQLDroidDriver"
    val dbName = "flights"
    DriverManager.registerDriver(Class.forName(driver, true, getClass.getClassLoader).newInstance.asInstanceOf[Driver])
    val db = Database.forURL(s"jdbc:sqlite:s${Environment.getExternalStorageDirectory }/databases/$dbName", driver, keepAliveConnection = true)

    val result: DBIO[Option[Int]] = sql"""SELECT COUNT(name) FROM sqlite_master WHERE type='table'""".as[Int].headOption
    db.run(result).foreach(result =>
      if(!result.exists(_ > 0)) {
        // Intialize Database
        val future = for {
          _ <- db.run(Models.distanceTable)
          _ <- db.run(Models.flightTable)
          r <- db.run(Models.flightNumberTable)
        } yield r
        future.onFailure { case s ⇒
          Log.wtf("Table Creation", s"db tables not created. Reason: $s")
        }
        future.onSuccess { case s ⇒
          Log.d("Table Creation", s"tables created!")
        }
      }
    )
  }

}
