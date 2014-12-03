package org.challenge.eshop.storage.memory

import com.twitter.util.Await
import org.challenge.eshop.storage.api.dao.entity.BaseEntity
import org.challenge.eshop.storage.memory.dao.BaseInMemoryDAO
import org.scalatest.FunSpec

/**
 * Created by Alexander Shurmin.
 */

case class FooEntity(var id: Option[String], value: Int) extends BaseEntity[String]

class FooDAO extends BaseInMemoryDAO[FooEntity]

class BaseMemoryDAOTest extends FunSpec {

  describe("BaseMemoryDAO") {

    it("should create entity") {
      val manager = new FooDAO
      val newEntity = FooEntity(Some("11"), 1)
      val createdEntity = Await.result(manager.create(newEntity))
      assert(newEntity == createdEntity)
    }

    it("should get entity by Id") {
      val manager = new FooDAO
      val newEntity = FooEntity(Some("11"), 1)
      Await.result(manager.create(newEntity))
      val oldEntity = Await.result(manager.getById("11"))
      assert(oldEntity.isDefined)
      assert(newEntity.equals(oldEntity.get))
    }

    it("should get entities in range") {
      val manager = new FooDAO

      val entity1 = FooEntity(Some("11"), 1)
      val entity2 = FooEntity(Some("22"), 2)
      val entity3 = FooEntity(Some("33"), 3)

      Await.result(manager.create(entity1))
      Await.result(manager.create(entity2))
      Await.result(manager.create(entity3))

      val entities = Await.result(manager.getInRange(2, 1))
      assert(entities.length == 1)
      assert(entities.head == entity3)
    }

    it("should delete entity by Id") {
      val manager = new FooDAO
      val newEntity = FooEntity(Some("11"), 1)
      Await.result(manager.create(newEntity))
      Await.result(manager.delete("11"))
      val oldEntity = Await.result(manager.getById("11"))
      assert(oldEntity.isEmpty)
    }

    it("should update entity") {
      val manager = new FooDAO

      val newEntity = FooEntity(Some("11"), 1)
      Await.result(manager.create(newEntity))

      val changedEntity = newEntity.copy(value = 2)
      Await.result(manager.update(changedEntity))

      val savedEntity = Await.result(manager.getById("11"))
      assert(savedEntity.isDefined)
      assert(changedEntity == savedEntity.get)
    }
  }
}
