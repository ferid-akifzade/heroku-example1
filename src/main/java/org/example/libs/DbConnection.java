package org.example.libs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

//  private static final String DB_URL = "jdbc:postgresql://localhost:5432/ibatech";
  private static final String DB_URL = "jdbc:postgresql://ec2-52-44-166-58.compute-1.amazonaws.com:5432/d3iomdo1olcja5";
  private static final String USERNAME = "hlxosbnasrttpl";
  private static final String USER_PASS = "0591438da8ebb7e6cb8f8d33a983a5170fc68e010c64a31cbab6d72766070a6b";

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
