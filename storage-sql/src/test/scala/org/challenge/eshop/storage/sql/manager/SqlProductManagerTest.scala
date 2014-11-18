package org.challenge.eshop.storage.sql.manager

import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.storage.sql.BaseSqlStorageTest
import org.joda.money.{CurrencyUnit, Money}

/**
 * Created by Alexander Shurmin.
 */
class SqlProductManagerTest extends BaseSqlStorageTest {

  describe("SqlProductManager") {
    it("should create one Product record") {
      testAsync {
        val product = ProductInfo(name = "product", price = Money.of(CurrencyUnit.of("USD"), 55.77))
        SqlProductManager.create(product).onSuccess(result => {
          result.id.isDefined shouldEqual true
          product shouldEqual result.copy(id = None)
        })
      }
    }
  }
}
