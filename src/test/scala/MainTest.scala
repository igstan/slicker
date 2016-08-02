package ro.igstan.slicker
package test

import scala.concurrent.{ Await, Future }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import slick.driver.MySQLDriver.api._
import org.scalatest.{ FunSuite, MustMatchers }

class MainTest extends FunSuite with MustMatchers {
  private val db = Database.forURL(
    url = "jdbc:mysql://localhost:3306/slicker?useSSL=false",
    driver = "com.mysql.jdbc.Driver",
    user = "root"
  )

  def withDatabase[A](block: Database => A): A = {
    Await.result(db.run {
      for {
        _ <- Suppliers.all.schema.create
        _ <- Coffees.all.schema.create
      } yield ()
    }, 10.minutes)

    try {

      block(db)

    } finally {
      Await.result(db.run {
        for {
          _ <- Coffees.all.schema.drop
          _ <- Suppliers.all.schema.drop
        } yield ()
      }, 10.minutes)
    }
  }

  test("database access") { withDatabase { db =>
    val supplier = Supplier(
      id = 42,
      name = "name",
      street = "street",
      city = "city",
      state = "state",
      zip = "zip"
    )

    val suppliers =
      for {
        _ <- db.run(Suppliers.all += supplier)
        suppliers <- db.run(Suppliers.all.result)
      } yield suppliers

    val supplierss = Await.result(suppliers, 10.minutes)

    supplierss mustEqual Seq(supplier)
  }}
}
