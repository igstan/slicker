// package ro.igstan.slicker
// package test

// import org.scalatest.Suite

// trait DatabaseFixture extends Suite {
//   override def withFixture(test: NoArgTest): Unit = {
//     try {
//       db.run {
//         for {
//           _ <- Suppliers.all.schema.create
//           _ <- Coffees.all.schema.create
//         } yield ()
//       }

//       super.withFixture(test)
//     } finally {
//       val _ = db.run {
//         for {
//           _ <- Coffees.all.schema.drop
//           _ <- Suppliers.all.schema.drop
//         } yield ()
//       }
//     }
//   }
// }
