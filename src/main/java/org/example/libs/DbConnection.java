package org.example.libs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

//  private static final String DB_URL = "jdbc:postgresql://localhost:5432/ibatech";
  private static final String DB_URL = "jdbc:postgresql://localhost:5432/example";
  private static final String USERNAME = "postgres";
  private static final String USER_PASS = "root";

  private static Connection connection;

  private DbConnection() {}

  public static Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(DB_URL, USERNAME, USER_PASS);
      } catch (SQLException e) {
        throw new RuntimeException("Something went wrong during connection", e);
      }
    }
    return connection;
  }
}
