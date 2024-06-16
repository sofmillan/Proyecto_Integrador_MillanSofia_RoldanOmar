package dh.backend.demo.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dh.backend.demo.entity.Domicilio;
import dh.backend.demo.entity.Odontologo;
import dh.backend.demo.entity.Paciente;
import dh.backend.demo.entity.Turno;
import dh.backend.demo.service.impl.PacienteService;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TurnoServiceTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(TurnoServiceTest.class);

  private ITurnoService turnoService;
  private IPacienteService pacienteService;
  private IOdontologoService odontologoService;
  private static Turno turno;
  private static Paciente paciente;
  private static Odontologo odontologo;

  @Autowired
  public TurnoServiceTest(ITurnoService turnoService, IPacienteService pacienteService,
      IOdontologoService odontologoService) {
    this.turnoService = turnoService;
    this.odontologoService = odontologoService;
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
    paciente.setId(1);

    odontologo = new Odontologo();
    odontologo.setNombre("Odontologo");
    odontologo.setApellido("Apellido");
    odontologo.setId(1);

    turno = new Turno();
    turno.setFecha(LocalDate.of(2024, 06, 15));
    turno.setPaciente(paciente);
    turno.setOdontologo(odontologo);
  }

  @Test
  void testTurnoGuardado() {
    Turno turnoGuardado = turnoService.registrar(turno);
    assertNotNull(turnoGuardado);
  }

}
