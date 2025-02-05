package com.sksamuel.cohort.db

import com.sksamuel.cohort.HealthCheck
import com.sksamuel.cohort.HealthCheckResult
import javax.sql.DataSource

/**
 * A [HealthCheck] that checks that a connection can be established with
 * a [DataSource] and a basic query executed.
 */
class DatabaseHealthCheck(
  private val ds: DataSource,
  private val query: String = "SELECT 1",
) : HealthCheck {
  override suspend fun check(): HealthCheckResult {
    val conn = ds.connection
    conn.createStatement().executeQuery(query)
    conn.close()
    return HealthCheckResult.Healthy("Connected to database successfully")
  }
}
