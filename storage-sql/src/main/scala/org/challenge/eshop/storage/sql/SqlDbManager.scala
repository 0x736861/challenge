package org.challenge.eshop.storage.sql

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.challenge.eshop.storage.sql.schema.EShopSchema
import org.squeryl.adapters.H2Adapter
import org.squeryl.{Session, SessionFactory}
import org.squeryl.PrimitiveTypeMode._

/**
 * Created by Alexander Shurmin.
 */
object SqlDbManager {

  def init(settings: JdbcSettings): Unit = {
    val dataSource = new ComboPooledDataSource

    dataSource.setDriverClass(settings.driverClass)
    dataSource.setJdbcUrl(settings.jdbcUrl)
    dataSource.setUser(settings.user)
    dataSource.setPassword(settings.password)
    dataSource.setMinPoolSize(settings.minPoolSize)
    dataSource.setMaxPoolSize(settings.maxPoolSize)

    SessionFactory.concreteFactory = Some(() =>
      Session.create(dataSource.getConnection, new H2Adapter))
  }

  def createSchema() = {
    transaction {
      EShopSchema.create
    }
  }

  def dropSchema() = {
    transaction {
      EShopSchema.drop
    }
  }
}
