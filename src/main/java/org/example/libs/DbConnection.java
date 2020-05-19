package org.example.libs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

//  private static final String DB_URL = "jdbc:postgresql://localhost:5432/ibatech";
  private static final String DB_URL = "jdbc:postgresql://ec2-34-200-101-236.compute-1.amazonaws.com:5432/dfppffuvsjsndl";
  private static final String USERNAME = "zrofhlizbmdzjh";
  private static final String USER_PASS = "3ffdf1946f49b152087bfd158e17560f755d102b6bcbe5571038583a74eab538";

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
