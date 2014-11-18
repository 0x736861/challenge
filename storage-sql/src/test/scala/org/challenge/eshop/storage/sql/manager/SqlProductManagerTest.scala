package org.challenge.eshop.storage.sql.manager

import org.challenge.eshop.common.converter.TwitterFutureConverter._
import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.storage.sql.BaseSqlStorageTest
import org.joda.money.{CurrencyUnit, Money}
import org.scalatest.concurrent.ScalaFutures

/**
 * Created by Alexander Shurmin.
 */
class SqlProductManagerTest extends BaseSqlStorageTest {

  describe("SqlProductManager") {
    it("should create one Product record") {
      val product = ProductInfo(name = "product", price = Money.of(CurrencyUnit.of("USD"), 55.77))

      ScalaFutures.whenReady(SqlProductManager.create(product).toScala)(result => {
        result.id.isDefined shouldEqual true
        product shouldEqual result.copy(id = None)
      })
    }

    it("should get Product by Id") {
      val product = ProductInfo(name = "product", price = Money.of(CurrencyUnit.of("USD"), 55.77))

      val future = SqlProductManager.create(product)
        .flatMap(createdEntity => SqlProductManager.getById(createdEntity.id.get).map(entityById => (createdEntity, entityById)))

      ScalaFutures.whenReady(future.toScala) {
        case (createdEntity, entityById) =>
          entityById.isDefined shouldEqual true
          entityById.get shouldEqual createdEntity
      }
    }

    it("should update product") {
      val product = ProductInfo(name = "product", price = Money.of(CurrencyUnit.of("USD"), 55.77))
      val newDescriptionValue = "description"
      val newPrice = Money.of(CurrencyUnit.of("RUR"), 100.99)

      val future = SqlProductManager.create(product).flatMap(createdEntity => {
        val entityWithChanges = createdEntity.copy(description = Some(newDescriptionValue), price = newPrice)
        SqlProductManager.update(entityWithChanges).flatMap(_ =>
          SqlProductManager.getById(createdEntity.id.get)
        )
      })

      ScalaFutures.whenReady(future.toScala)(updatedEntity => {
        updatedEntity.get.description.isDefined shouldEqual true
        updatedEntity.get.description.get shouldEqual newDescriptionValue
        updatedEntity.get.price shouldEqual newPrice
      })
    }

    it("should delete product") {
      val product = ProductInfo(name = "product", price = Money.of(CurrencyUnit.of("USD"), 55.77))

      val future = for {
        createdEntity <- SqlProductManager.create(product)
        countOfDeletedEntities <- SqlProductManager.delete(createdEntity.id.get)
      } yield countOfDeletedEntities

      ScalaFutures.whenReady(future.toScala)(countOfDeletedEntities =>
        countOfDeletedEntities shouldEqual 1
      )
    }
  }
}
