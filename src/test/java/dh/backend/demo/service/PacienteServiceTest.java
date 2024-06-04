package dh.backend.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dh.backend.demo.dao.impl.PacienteDaoH2;
import dh.backend.demo.model.Domicilio;
import dh.backend.demo.model.Paciente;
import dh.backend.demo.service.impl.PacienteService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacienteServiceTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(PacienteServiceTest.class);
  private static IPacienteService pacienteService = new PacienteService(new PacienteDaoH2());

  @BeforeAll
  static void crearTablas() {
    Connection connection = null;
    try {
      Class.forName("org.h2.Driver");
      connection =
          DriverManager.getConnection(
              "jdbc:h2:~/demo;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
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

  @Test
  void testPacienteGuardado() {
    Domicilio domocilioDelPaciente = new Domicilio("calle", 1, "localidad", "provincia");
    Paciente paciente =
        new Paciente(
            "Nombre",
            "Apellido1",
            "name@example.com",
            "123",
            LocalDate.now(),
            domocilioDelPaciente);
    Paciente pacienteGuardado = pacienteService.registrarPaciente(paciente);
    assertNotNull(pacienteGuardado);
  }

  @Test
  void testPacienteId() {
    Integer id = 1;
    Paciente pacienteEncontrado = pacienteService.buscarPorId(id);

    assertEquals(id, pacienteEncontrado.getId());
  }

  @Test
  void testBusquedaTodos() {
    Domicilio domocilioDelPaciente = new Domicilio("calle", 1, "localidad", "provincia");
    Paciente paciente =
        new Paciente(
            "Nombre2",
            "Apellido2",
            "name2@example.com",
            "123",
            LocalDate.now(),
            domocilioDelPaciente);

    pacienteService.registrarPaciente(paciente);

    List<Paciente> pacientes = pacienteService.buscarTodos();

    assertTrue(pacientes.size() != 0);
  }
}
