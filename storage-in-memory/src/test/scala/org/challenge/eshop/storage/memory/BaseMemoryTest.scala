package org.challenge.eshop.storage.memory

import com.twitter.util.Await
import org.challenge.eshop.model.IdEntity
import org.challenge.eshop.storage.memory.manager.BaseManager
import org.scalatest.FunSpec

/**
 * Created by Alexander Shurmin.
 */

case class IntEntity(id: Option[String], value: Int) extends IdEntity[String]

class IntManager extends BaseManager[IntEntity]

class BaseMemoryTest extends FunSpec {

  describe("BaseManager") {

    it("should create entity") {
      val manager = new IntManager
      val newEntity = IntEntity(Some("11"), 1)
      val createdEntity = Await.result(manager.create(newEntity))
      assert(newEntity == createdEntity)
    }

    it("should get entity by Id") {
      val manager = new IntManager
      val newEntity = IntEntity(Some("11"), 1)
      Await.result(manager.create(newEntity))
      val oldEntity = Await.result(manager.getById("11"))
      assert(oldEntity.isDefined)
      assert(newEntity.equals(oldEntity.get))
    }

    it("should get entities in range") {
      val manager = new IntManager

      val entity1 = IntEntity(Some("11"), 1)
      val entity2 = IntEntity(Some("22"), 2)
      val entity3 = IntEntity(Some("33"), 3)

      Await.result(manager.create(entity1))
      Await.result(manager.create(entity2))
      Await.result(manager.create(entity3))

      val entities = Await.result(manager.getInRange(2, 1))
      assert(entities.length == 1)
      assert(entities.head == entity3)
    }

    it("should delete entity by Id") {
      val manager = new IntManager
      val newEntity = IntEntity(Some("11"), 1)
      Await.result(manager.create(newEntity))
      Await.result(manager.delete("11"))
      val oldEntity = Await.result(manager.getById("11"))
      assert(oldEntity.isEmpty)
    }

    it("should update entity") {
      val manager = new IntManager

      val newEntity = IntEntity(Some("11"), 1)
      Await.result(manager.create(newEntity))

      val changedEntity = newEntity.copy(value = 2)
      Await.result(manager.update(changedEntity))

      val savedEntity = Await.result(manager.getById("11"))
      assert(savedEntity.isDefined)
      assert(changedEntity == savedEntity.get)
    }
  }
}
