package dh.backend.demo.dao.impl;

import dh.backend.demo.dao.IDao;
import dh.backend.demo.db.H2Connection;
import dh.backend.demo.model.Domicilio;
import dh.backend.demo.model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PacienteDaoH2 implements IDao<Paciente> {
  private static final Logger LOGGER = LoggerFactory.getLogger(PacienteDaoH2.class);
  private static String SQL_INSERT = "INSERT INTO PACIENTES VALUES (DEFAULT,?,?,?,?,?,?)";
  private static String SQL_SELECT_ID = "SELECT * FROM PACIENTES WHERE ID=?";
  private static String SQL_SELECT_ALL = "SELECT * FROM PACIENTES";
  private static String SQL_UPDATE =
      "UPDATE PACIENTES SET APELLIDO=?, NOMBRE=?, DNI=?, FECHA_INGRESO=?, ID_DOMICILIO=? WHERE"
          + " ID=?";
  private static String SQL_DELETE = "DELETE FROM PACIENTES WHERE ID=?";

  @Override
  public Paciente registrar(Paciente paciente) {
    Connection connection = null;
    DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
    Paciente pacienteARetornar = null;
    try {
      connection = H2Connection.getConnection();
      connection.setAutoCommit(false);

      Domicilio domicilioGuardado = domicilioDaoH2.registrar(paciente.getDomicilio());
      System.out.println(domicilioGuardado.toString());

      PreparedStatement preparedStatement =
          connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, paciente.getApellido());
      preparedStatement.setString(2, paciente.getNombre());
      preparedStatement.setString(3, paciente.getEmail());
      preparedStatement.setString(4, paciente.getDni());
      preparedStatement.setDate(5, Date.valueOf(paciente.getFechaIngreso()));
      preparedStatement.setInt(6, domicilioGuardado.getId());
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      while (resultSet.next()) {
        Integer id = resultSet.getInt(1);
        pacienteARetornar =
            new Paciente(
                id,
                paciente.getApellido(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getDni(),
                paciente.getFechaIngreso(),
                domicilioGuardado);
      }
      LOGGER.info("paciente guardado: " + pacienteARetornar.toString());

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

    return pacienteARetornar;
  }

  @Override
  public Paciente buscarPorId(Integer id) {
    Connection connection = null;
    DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
    Paciente pacienteEncontrado = null;
    try {
      connection = H2Connection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ID);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        pacienteEncontrado =
            new Paciente(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getDate(6).toLocalDate(),
                domicilioDaoH2.buscarPorId(resultSet.getInt(7)));
      }
      LOGGER.info("paciente encontrado" + pacienteEncontrado.toString());

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
    return pacienteEncontrado;
  }

  @Override
  public List<Paciente> buscarTodos() {
    Connection connection = null;
    DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
    Paciente pacienteEncontrado = null;
    List<Paciente> pacientes = new ArrayList<Paciente>();
    try {
      connection = H2Connection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        pacienteEncontrado =
            new Paciente(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getDate(6).toLocalDate(),
                domicilioDaoH2.buscarPorId(resultSet.getInt(7)));
        pacientes.add(pacienteEncontrado);
      }
      LOGGER.info("pacientes encontrados" + pacientes.toString());

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
    return pacientes;
  }

  @Override
  public void actualizar(Paciente paciente) {
    Connection connection = null;
    try {
      connection = H2Connection.getConnection();
      connection.setAutoCommit(false);

      PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
      preparedStatement.setString(1, paciente.getApellido());
      preparedStatement.setString(2, paciente.getNombre());
      preparedStatement.setString(3, paciente.getDni());
      preparedStatement.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
      preparedStatement.setInt(5, paciente.getDomicilio().getId());
      preparedStatement.setInt(6, paciente.getId());
      preparedStatement.executeUpdate();

      System.out.println(paciente);
      LOGGER.info("paciente actualizado" + paciente.toString());
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

      LOGGER.info("paciente eliminado con id: " + id);
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
