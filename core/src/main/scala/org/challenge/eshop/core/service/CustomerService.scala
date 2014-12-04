package org.challenge.eshop.core.service

import com.twitter.util.Future
import org.challenge.eshop.model.Customer
import org.challenge.eshop.storage.api.manager.CustomerManager

/**
 * Created by Alexander Shurmin.
 */
class CustomerService(implicit customerManager: CustomerManager) extends BaseService {

  def get(id: String): Future[Option[Customer]] = {
    logger.info(s"Get customer by Id=$id")
    customerManager.getById(id)
  }

  def get(offset: Int, limit: Int): Future[List[Customer]] = {
    logger.info(s"Get customers in range: offset=$offset, limit=$limit")
    customerManager.getInRange(offset, limit)
  }

  def create(customer: Customer): Future[Customer] = {
    logger.info(s"Creating customer: $customer")
    customerManager.create(customer)
  }

  def update(customer: Customer): Future[Customer] = {
    logger.info(s"Updating customer: $customer")
    customerManager.update(customer)
  }

  def delete(id: String): Future[Boolean] = {
    logger.info(s"Deleting customer by Id=$id")
    customerManager.delete(id)
  }
}
