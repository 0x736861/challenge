package org.challenge.eshop.storage.api.manager

import com.twitter.util.Future

/**
 * Created by Alexander Shurmin.
 */
trait BaseManager[TKey, TEntity] {

  def getById(id: TKey): Future[Option[TEntity]]

  def getInRange(offset: Int, limit: Int): Future[List[TEntity]]

  def create(model: TEntity): Future[TEntity]

  def update(model: TEntity): Future[TEntity]

  def delete(id: String): Future[Boolean]
}
