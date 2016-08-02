package ro.igstan.slicker

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.MySQLDriver.api._

object Main {
  private val db = Database.forURL(
    url = "jdbc:mysql://localhost:3306/slick?useSSL=false",
    driver = "com.mysql.jdbc.Driver",
    user = "root"
  )

  def main(args: Array[String]): Unit = {
    // println(s"SQL: ${Suppliers.all.schema.createStatements.toList}")
    // println(s"SQL: ${Coffees.all.schema.createStatements.toList}")

    val f = for {
      _ <- db.run {
        Suppliers.all += Supplier(
          id = 42,
          name = "name",
          street = "street",
          city = "city",
          state = "state",
          zip = "zip"
        )
      }
      s <- db.run(Suppliers.all.result)
      // _ <- db.run(Coffees.all.schema.create)
    } yield s

    f.onComplete {
      case Success(s) =>
        println(s)
      case Failure(e) =>
        e.printStackTrace
        db.close()
    }

    // val supplierIDs = Suppliers.all.join(Coffees.all).on(_.id === _.supID)
    //   .filter { case (supplier, coffee) => supplier.name === "Starbucks" }
    //   .map { case (supplier, coffee) => supplier.id }

    // val sql = Coffees.all
    //   .filter(coffee => coffee.supID.in(supplierIDs) || coffee.name === "Arabica")
    //   .filter(_.name === "Arabica")
    //   .delete
    //   .statements
    //   .head

    // val results = db.run {
    //   Coffees.all
    // }

    // println(s"SQL: $sql")
  }
}
