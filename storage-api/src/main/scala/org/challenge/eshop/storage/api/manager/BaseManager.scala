package org.challenge.eshop.storage.api.manager

import com.twitter.util.Future
import org.challenge.eshop.model.Entity

/**
 * Created by Alexander Shurmin.
 */
trait BaseManager[TKey, TValue <: Entity[TKey]] {

  def getById(id: TKey): Future[Option[TValue]]

  def getInRange(offset: Int, limit: Int): Future[List[TValue]]

  def create(model: TValue): Future[TValue]

  def update(model: TValue): Future[TValue]

  def delete(id: String): Future[Boolean]
}
