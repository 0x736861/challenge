package org.challenge.eshop.storage.sql

import com.twitter.util.Future
import org.challenge.eshop.storage.sql.schema.EShopSchema
import org.scalatest._
import org.scalatest.concurrent.AsyncAssertions.Waiter
import org.scalatest.concurrent.Eventually._
import org.scalatest.time.SpanSugar._
import org.squeryl.PrimitiveTypeMode._


/**
 * Created by Alexander Shurmin.
 */
trait BaseSqlStorageTest extends FunSpec with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  override protected def beforeAll(): Unit = {
    val settings = JdbcSettings(
      driverClass = "org.h2.Driver",
      jdbcUrl = "jdbc:h2:mem:test",
      user = "",
      password = "",
      minPoolSize = 5,
      maxPoolSize = 10
    )

    SqlDbManager.init(settings)
  }

  before({
    transaction {
      EShopSchema.create
    }
  })

  after({
    transaction {
      EShopSchema.drop
    }
  })

  def testAsync(action: => Future[_]): Unit = {
    val w = new Waiter
    action.ensure {
      w.dismiss()
    }
    w.await(timeout(10 seconds))
  }

}
