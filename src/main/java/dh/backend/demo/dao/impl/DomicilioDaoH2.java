package dh.backend.demo.dao.impl;

import dh.backend.demo.dao.IDao;
import dh.backend.demo.db.H2Connection;
import dh.backend.demo.model.Domicilio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DomicilioDaoH2 implements IDao<Domicilio> {
  private static final Logger LOGGER = LoggerFactory.getLogger(DomicilioDaoH2.class);
  private static String SQL_INSERT = "INSERT INTO DOMICILIOS VALUES (DEFAULT,?,?,?,?)";
  private static String SQL_SELECT_ID = "SELECT * FROM DOMICILIOS WHERE ID=?";
  private static String SQL_SELECT_ALL = "SELECT * FROM DOMICILIOS";
  private static String SQL_UPDATE =
      "UPDATE DOMICILIOS SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE" + " ID=?";
  private static String SQL_DELETE = "DELETE FROM DOMICILIOS WHERE ID=?";

  @Override
  public Domicilio registrar(Domicilio domicilio) {
    Connection connection = null;
    Domicilio domicilioARetornar = null;
    try {
      connection = H2Connection.getConnection();
      connection.setAutoCommit(false);

      PreparedStatement preparedStatement =
          connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, domicilio.getCalle());
      preparedStatement.setInt(2, domicilio.getNumero());
      preparedStatement.setString(3, domicilio.getLocalidad());
      preparedStatement.setString(4, domicilio.getProvincia());
      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      while (resultSet.next()) {
        Integer id = resultSet.getInt(1);
        domicilioARetornar =
            new Domicilio(
                id,
                domicilio.getCalle(),
                domicilio.getNumero(),
                domicilio.getLocalidad(),
                domicilio.getProvincia());
      }
      LOGGER.info("domicilio guardado " + domicilioARetornar.toString());
      connection.commit();
      connection.setAutoCommit(true);
    } catch (Exception e) {
      if (connection != null) {
        try {
          connection.rollback();
        } catch (SQLException ex) {
          LOGGER.info(ex.getMessage());
          ex.printStackTrace();
        }
      }
      LOGGER.info(e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        LOGGER.info(e.getMessage());
        e.printStackTrace();
      }
    }

    return domicilioARetornar;
  }

  @Override
  public Domicilio buscarPorId(Integer id) {
    Connection connection = null;
    Domicilio domicilioEncontrado = null;
    try {
      connection = H2Connection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ID);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        domicilioEncontrado =
            new Domicilio(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getInt(3),
                resultSet.getString(4),
                resultSet.getString(5));
      }
      LOGGER.info("domicilio encontrado" + domicilioEncontrado.toString());

    } catch (Exception e) {
      LOGGER.info(e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        LOGGER.info(e.getMessage());
        e.printStackTrace();
      }
    }
    return domicilioEncontrado;
  }

  @Override
  public List<Domicilio> buscarTodos() {
    Connection connection = null;
    Domicilio domicilioEncontrado = null;
    List<Domicilio> domicilios = new ArrayList<Domicilio>();
    try {
      connection = H2Connection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        domicilioEncontrado =
            new Domicilio(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getInt(3),
                resultSet.getString(4),
                resultSet.getString(5));
        domicilios.add(domicilioEncontrado);
      }
      LOGGER.info("domicilios encontrados" + domicilios.toString());

    } catch (Exception e) {
      LOGGER.info(e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        LOGGER.info(e.getMessage());
        e.printStackTrace();
      }
    }
    return domicilios;
  }

  @Override
  public void actualizar(Domicilio domicilio) {
    Connection connection = null;
    try {
      connection = H2Connection.getConnection();
      connection.setAutoCommit(false);

      PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
      preparedStatement.setString(1, domicilio.getCalle());
      preparedStatement.setInt(2, domicilio.getNumero());
      preparedStatement.setString(3, domicilio.getLocalidad());
      preparedStatement.setString(4, domicilio.getProvincia());
      preparedStatement.setInt(5, domicilio.getId());
      preparedStatement.executeUpdate();

      LOGGER.info("domicilio actualizado" + domicilio.toString());
      connection.commit();
      connection.setAutoCommit(true);
    } catch (Exception e) {
      if (connection != null) {
        try {
          connection.rollback();
        } catch (SQLException ex) {
          LOGGER.info(ex.getMessage());
          ex.printStackTrace();
        }
      }
      LOGGER.info(e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        LOGGER.info(e.getMessage());
        e.printStackTrace();
      }
    }
  }

  @Override
  public void eliminar(Integer id) {
    Connection connection = null;
    try {
      connection = H2Connection.getConnection();
      connection.setAutoCommit(false);

      PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
      preparedStatement.setInt(1, id);
      preparedStatement.executeUpdate();

      LOGGER.info("domicilio eliminado con id: " + id);
      connection.commit();
      connection.setAutoCommit(true);
    } catch (Exception e) {
      if (connection != null) {
        try {
          connection.rollback();
        } catch (SQLException ex) {
          LOGGER.info(ex.getMessage());
          ex.printStackTrace();
        }
      }
      LOGGER.info(e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        LOGGER.info(e.getMessage());
        e.printStackTrace();
      }
    }
  }
}
