package com.sb_restapi.framework.db.migrations;

import org.flywaydb.core.api.migration.Context;
import org.flywaydb.core.api.migration.BaseJavaMigration;

import java.sql.Statement;

public class V1697926195__PersonsCreateTable extends BaseJavaMigration {
  @Override
  public void migrate(Context context) throws Exception {
    try (Statement stmnt = context.getConnection().createStatement()) {
      String createTablePersons = """
          CREATE TABLE IF NOT EXISTS persons (
            id VARCHAR(50) PRIMARY KEY DEFAULT gen_random_uuid()::VARCHAR,
            name VARCHAR(250),
            email VARCHAR(250) UNIQUE,
            birth_date DATE
          );
          """;

      stmnt.execute(createTablePersons);
    }
  }
}