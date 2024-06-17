package dh.backend.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dh.backend.demo.entity.Domicilio;
import dh.backend.demo.entity.Paciente;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PacienteServiceTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(PacienteServiceTest.class);

  private IPacienteService pacienteService;
  private static Paciente paciente;

  @Autowired
  public PacienteServiceTest(IPacienteService pacienteService) {
    this.pacienteService = pacienteService;
  }

  @BeforeAll
  // Populate the database
  static void setup() {
    paciente = new Paciente();
    paciente.setNombre("Menganito");
    paciente.setApellido("Cosme");
    paciente.setDni("464646");
    paciente.setFechaIngreso(LocalDate.of(2024, 01, 12));
    Domicilio domicilio = new Domicilio();
    domicilio.setCalle("Calle");
    domicilio.setNumero(123);
    domicilio.setLocalidad("San Pedro");
    domicilio.setProvincia("Jujuy");
    paciente.setDomicilio(domicilio);
  }

  @Test
  void testPacienteGuardado() {
  /*  Paciente pacienteGuardado = pacienteService.registrarPaciente(paciente);

    assertNotNull(pacienteGuardado);*/
  }

/*  @Test
  void testPacienteId() {
    Integer id = 1;
    Optional<Paciente> pacienteEncontrado = pacienteService.buscarPorId(id);
    Paciente pacienteRecuperado = pacienteEncontrado.get();

    assertEquals(id, pacienteRecuperado.getId());
  }*/

/*  @Test
  void testBusquedaTodos() {
    List<Paciente> pacientes = pacienteService.buscarTodos();

    assertTrue(pacientes.size() != 0);
  }*/
}
