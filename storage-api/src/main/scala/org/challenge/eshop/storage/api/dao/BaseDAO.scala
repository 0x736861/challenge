package org.challenge.eshop.storage.api.dao

import com.twitter.util.Future
import org.challenge.eshop.storage.api.dao.entity.BaseEntity

/**
 * Created by Alexander Shurmin.
 */
trait BaseDAO[TKey, TValue <: BaseEntity[TKey]] {

  def getById(id: TKey): Future[Option[TValue]]

  def getInRange(offset: Int, limit: Int): Future[List[TValue]]

  def create(model: TValue): Future[TValue]

  def update(model: TValue): Future[Unit]

  def delete(id: TKey): Future[Int]
}
