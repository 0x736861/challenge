package org.challenge.eshop.storage.api.manager

import com.twitter.util.Future
import org.challenge.eshop.model.IdEntity

/**
 * Created by Alexander Shurmin.
 */
trait CRUDManager[TKey, TValue <: IdEntity[TKey]] {

  def getById(id: TKey): Future[Option[TValue]]

  def getInRange(offset: Int, limit: Int): Future[List[TValue]]

  def create(model: TValue): Future[TValue]

  def update(model: TValue): Future[Unit]

  def delete(id: String): Future[Int]
}
