package dh.backend.demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H2Connection {
  private static final String DB_JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:~/clinicaMVC3005";
  private static final String DB_USER = "sa";
  private static final String DB_PASS = "sa";
  private static final Logger LOGGER = LoggerFactory.getLogger(H2Connection.class);

  public static Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName(DB_JDBC_DRIVER);
    return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
  }

  public static void crearTablas() {
    Connection connection = null;
    try {
      Class.forName("org.h2.Driver");
      connection =
          DriverManager.getConnection(
              "jdbc:h2:~/clinicaMVC3005;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.error(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        LOGGER.error(e.getMessage());
      }
    }
  }
}
