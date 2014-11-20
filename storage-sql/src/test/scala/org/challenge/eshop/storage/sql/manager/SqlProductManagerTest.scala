package org.challenge.eshop.storage.sql.manager

import org.challenge.eshop.common.converter.TwitterFutureConverter._
import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.storage.sql.BaseSqlStorageTest
import org.scalatest.concurrent.ScalaFutures

/**
 * Created by Alexander Shurmin.
 */
class SqlProductManagerTest extends BaseSqlStorageTest {

  describe("SqlProductManager") {
    it("should create one Product record") {
      val product = ProductInfo(name = "product", price = 55.77)

      ScalaFutures.whenReady(SqlProductManager.create(product).toScala)(result => {
        result.id.isDefined shouldEqual true
        product shouldEqual result.copy(id = None)
      })
    }

    it("should get Product by Id") {
      val product = ProductInfo(name = "product", price = 55.77)

      val future = SqlProductManager.create(product)
        .flatMap(createdEntity => SqlProductManager.getById(createdEntity.id.get).map(entityById => (createdEntity, entityById)))

      ScalaFutures.whenReady(future.toScala) {
        case (createdEntity, entityById) =>
          entityById.isDefined shouldEqual true
          entityById.get shouldEqual createdEntity
      }
    }

    it("should update product") {
      val product = ProductInfo(name = "product", price = 55.77)
      val newName = "newProduct"
      val newPrice = 100.99

      val future = SqlProductManager.create(product).flatMap(createdEntity => {
        val entityWithChanges = createdEntity.copy(name = newName, price = newPrice)
        SqlProductManager.update(entityWithChanges).flatMap(_ =>
          SqlProductManager.getById(createdEntity.id.get)
        )
      })

      ScalaFutures.whenReady(future.toScala)(updatedEntity => {
        updatedEntity.get.name shouldEqual newName
        updatedEntity.get.price shouldEqual newPrice
      })
    }

    it("should delete product") {
      val product = ProductInfo(name = "product", price = 55.77)

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