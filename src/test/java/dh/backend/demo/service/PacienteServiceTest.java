package dh.backend.demo.service;

import dh.backend.demo.dto.request.DomicilioRequestDto;
import dh.backend.demo.dto.request.PacienteRequestDto;
import dh.backend.demo.dto.response.PacienteResponseDto;
import java.time.LocalDate;
import java.util.List;

import dh.backend.demo.exception.ResourceNotFoundException;
import dh.backend.demo.service.impl.PacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

  @Autowired
  private PacienteService pacienteService;

  @Test
  @Order(1)
  void testGuardarPaciente() {
    DomicilioRequestDto domicilioRequest = new DomicilioRequestDto(null, "Calle 45", 12, "Pereira", "Quindío");
    PacienteRequestDto pacienteRequest = new PacienteRequestDto("Millán", "Sofía", "example@gmail.com", "123", LocalDate.of(2021,8,1), domicilioRequest);

    PacienteResponseDto pacienteGuardado = pacienteService.registrarPaciente(pacienteRequest);

    assertNotNull(pacienteGuardado);
  }

  @Test
  @Order(2)
  void testBuscarPacientePorId() {
    Integer id = 1;
    PacienteResponseDto pacienteEncontrado = pacienteService.buscarPorId(id);

    assertEquals(id, pacienteEncontrado.getId());
  }
  @Test
  @Order(3)
  void testBuscarTodosPacientes() {
    List<PacienteResponseDto> pacientes = pacienteService.buscarTodos();

    assertTrue(pacientes.size() != 0);
  }

  @Test
  @Order(4)
  void testBuscarPacientesPorProvincia() {
    List<PacienteResponseDto> pacientes = pacienteService.buscarPorDomicilioProvincia("Quindío");

    assertTrue(pacientes.size() != 0);
  }

  @Test
  @Order(5)
  void testBuscarPacientesPorDni() {
    PacienteResponseDto pacienteEncontrado = pacienteService.buscarPorDni("123");

    assertNotNull(pacienteEncontrado);
  }

  @Test
  @Order(6)
  void testActualizarPaciente() {
    DomicilioRequestDto domicilioRequest = new DomicilioRequestDto(1, "Calle 45", 12, "Pereira", "Quindío");
    PacienteRequestDto pacienteRequest = new PacienteRequestDto("Millán", "Sofía", "sofia@gmail.com", "123", LocalDate.of(2021,8,1), domicilioRequest);

    pacienteService.actualizarPaciente(pacienteRequest, 1);

    assertEquals("sofia@gmail.com",pacienteRequest.getEmail());
  }

  @Test
  @Order(7)
  void testEliminarPaciente() {
    pacienteService.eliminarPaciente(1);

    assertThrows(ResourceNotFoundException.class, ()-> pacienteService.buscarPorId(1));
  }
}
