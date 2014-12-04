package org.challenge.eshop.ws.to.converter

import org.challenge.eshop.model.Customer
import org.challenge.eshop.ws.to.CustomerTO

/**
 * Created by Alexander Shurmin.
 */
object CustomerTOConverter {

  implicit class CustomerModelConverter(model: Customer) {
    def toTransferObject: CustomerTO = {
      CustomerTO(
        id = model.id,
        name = Option(model.name)
      )
    }

    def updateFrom(to: CustomerTO): Customer = {
      var resultModel = model
      if (to.id.isDefined) resultModel = resultModel.copy(id = to.id)
      if (to.name.isDefined) resultModel = resultModel.copy(name = to.name.get)
      resultModel
    }
  }

  implicit class CustomerTOConverter(to: CustomerTO) {
    def toModel: Customer = {
      Customer(
        id = to.id,
        name = to.name.getOrElse(throw new IllegalArgumentException("name"))
      )
    }
  }

}
