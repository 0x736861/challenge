package org.challenge.eshop.storage.sql.query

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.{KeyedEntity, Table}

/**
 * Created by Alexander Shurmin.
 */
trait BaseQueries {

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

  def delete(id: String): Boolean = {
    inTransaction {
      table.deleteWhere(p => p.id === id) != 0
    }
  }
}
