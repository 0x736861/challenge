package org.challenge.eshop.ws.to.converter

/**
 * Created by Alexander Shurmin.
 */
trait TOConverter[TransferObjectType, ModelType] {

  def toTransferObject(model: ModelType): TransferObjectType

  def mergeTransferObjectToModel(to: TransferObjectType, model: ModelType): ModelType

  def toModel(to: TransferObjectType): ModelType
}
