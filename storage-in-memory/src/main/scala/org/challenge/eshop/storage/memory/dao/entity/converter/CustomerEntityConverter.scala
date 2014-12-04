package org.challenge.eshop.storage.memory.dao.entity.converter

import org.challenge.eshop.model.Customer
import org.challenge.eshop.storage.memory.dao.entity.CustomerEntity

/**
 * Created by Alexander Shurmin.
 */
object CustomerEntityConverter {

  implicit class CustomerEntityConverter(entity: CustomerEntity) {
    def toModel: Customer = {
      Customer(
        id = entity.id,
        name = entity.name
      )
    }
  }

  implicit class CustomerModelConverter(model: Customer) {
    def toEntity: CustomerEntity = {
      CustomerEntity(
        id = model.id,
        name = model.name
      )
    }
  }

}
