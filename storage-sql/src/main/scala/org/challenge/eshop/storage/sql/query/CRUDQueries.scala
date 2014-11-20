package org.challenge.eshop.storage.sql.query

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.{KeyedEntity, Table}

/**
 * Created by Alexander Shurmin.
 */
trait CRUDQueries {

  type EntityType <: KeyedEntity[String]

  val table: Table[EntityType]

  def getById(id: String): Option[EntityType] = {
    inTransaction {
      from(table)(t =>
        where(t.id === id)
          select t
      ).headOption
    }
  }

  def getInRange(offset: Int, limit: Int): List[EntityType] = {
    inTransaction {
      from(table)(e =>
        select(e)
          orderBy e.id
      ).page(offset, limit)
        .toList
    }
  }

  def create(entity: EntityType): EntityType = {
    inTransaction {
      table.insert(entity)
    }
  }

  def update(entity: EntityType) {
    inTransaction {
      table.update(entity)
    }
  }

  def delete(id: String): Int = {
    inTransaction {
      table.deleteWhere(p => p.id === id)
    }
  }
}
