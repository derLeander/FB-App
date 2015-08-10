package org.leander

import scala.slick.driver.SQLiteDriver.api._
import slick.lifted.Tag
import com.github.nscala_time.time.Imports._
import slick.profile.SqlProfile.ColumnOption._
 
case class Distance(departure: String, arrival: String, miles: Int)
class Distances(tag: Tag) extends Table[Distance](tag, "distances") {
  def departure = column[String]("depature", NotNull)
  def arrival = column[String]("arrival", NotNull)
  def miles = column[Int]("miles", NotNull)
}

case class Flight(flightNumber: String, date: DateTime)
class Flights(tag: Tag) extends Table[Flight](tag, "flights") {
  def flightNumber = column[String]("flightNumber", NotNull)
  def date = column[DateTime]("date", NotNull)(Implicits.JodaTimeMapper)
}
case class FlightNumber(flightNumber: String, from: String, to: String)
class FlightNumbers(tag: Tag) extends Table[FlightNumber](tag, "flightNumbers") {
  def departure = column[String]("depature", NotNull)
  def arrival = column[String]("arrival", NotNull)
  def flightNumber = column[String]("flightNumber", NotNull, O.PrimaryKey)
}

object Models {
  val distanceTable = sqlu"""
CREATE TABLE distances (
departure TEXT NOT NULL,
arrival TEXT NOT NULL,
miles INTEGER NOT NULL,
CONSTRAINT compositeDistance PRIMARY KEY(departure, arrival))"""

  val flightTable = sqlu"""
CREATE TABLE flights (
flightNumber PRIMARY KEY TEXT NOT NULL,
date TEXT NOT NULL),
CONSTRAINT compositeFligts PRIMARY KEY(flightNumber, date))"""

  val flightNumberTable = sqlu"""
CREATE TABLE flightNumbers (
departure TEXT NOT NULL,
arrival TEXT NOT NULL,
flightNumber TEXT NOT NULL PRIMARY KEY)"""
}

object Implicits {
  implicit val JodaTimeMapper =
    MappedColumnType.base[DateTime, String] (_.toString, d => DateTime.parse(d))
}
