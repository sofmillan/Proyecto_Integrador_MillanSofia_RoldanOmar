package dh.backend.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.response.OdontologoResponseDto;
import dh.backend.demo.entity.Odontologo;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OdontologoServiceTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(PacienteServiceTest.class);
  private IOdontologoService odontologoService;

  private static OdontologoRequestDto odontologo;

  @Autowired
  public OdontologoServiceTest(IOdontologoService odontologoService) {
    this.odontologoService = odontologoService;
  }

  @BeforeAll
  static void setup() {
    odontologo = new OdontologoRequestDto();
    odontologo.setNroMatricula("12345");
    odontologo.setNombre("odontologo nombre");
    odontologo.setApellido("apellido");
  }

  @Test
  void testOdontologoGuardado() {
    OdontologoResponseDto odontologoGuardado = odontologoService.registrarOdontologo(odontologo);

    assertNotNull(odontologoGuardado);
  }

  @Test
  void testOdontologoId() {
    Integer id = 1;
    Optional<Odontologo> odontologoEncontrado = odontologoService.buscarOdontologoPorId(id);
    Odontologo odontologoRecuperado = odontologoEncontrado.get();

    assertEquals(id, odontologoRecuperado.getId());
  }

  // @Test
  void testBusquedaTodos() {
    List<Odontologo> odontologos = odontologoService.buscarTodos();

    assertTrue(odontologos.size() != 0);
  }
}
