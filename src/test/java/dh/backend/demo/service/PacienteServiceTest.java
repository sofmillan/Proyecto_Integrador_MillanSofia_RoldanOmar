package dh.backend.demo.service;

import dh.backend.demo.dto.request.DomicilioRequestDto;
import dh.backend.demo.dto.request.PacienteRequestDto;
import dh.backend.demo.dto.response.PacienteResponseDto;
import java.time.LocalDate;
import java.util.List;

import dh.backend.demo.exception.ResourceNotFoundException;
import dh.backend.demo.service.impl.PacienteService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PacienteServiceTest {

  @Autowired
  private PacienteService pacienteService;

  @Test
  void testPacienteGuardado() {
    DomicilioRequestDto domicilioRequest = new DomicilioRequestDto(null, "Calle 45", 12, "Pereira", "Quindío");
    PacienteRequestDto pacienteRequest = new PacienteRequestDto("Millán", "Sofía", "example@gmail.com", "123", LocalDate.of(2021,8,1), domicilioRequest);

    PacienteResponseDto pacienteGuardado = pacienteService.registrarPaciente(pacienteRequest);

    assertNotNull(pacienteGuardado);
  }

  @Test
  void testPacienteId() {
    Integer id = 1;
    PacienteResponseDto pacienteEncontrado = pacienteService.buscarPorId(id);

    assertEquals(id, pacienteEncontrado.getId());
  }
  @Test
  void testBusquedaTodos() {
    List<PacienteResponseDto> pacientes = pacienteService.buscarTodos();

    assertTrue(pacientes.size() != 0);
  }

  @Test
  void testBusquedaPorProvincia() {
    List<PacienteResponseDto> pacientes = pacienteService.buscarPorDomicilioProvincia("Quindío");

    assertTrue(pacientes.size() != 0);
  }

  @Test
  void testActualizarPaciente() {
    DomicilioRequestDto domicilioRequest = new DomicilioRequestDto(1, "Calle 45", 12, "Pereira", "Quindío");
    PacienteRequestDto pacienteRequest = new PacienteRequestDto("Millán", "Sofía", "sofia@gmail.com", "123", LocalDate.of(2021,8,1), domicilioRequest);

    pacienteService.actualizarPaciente(pacienteRequest, 1);

    assertEquals("sofia@gmail.com",pacienteRequest.getEmail());
  }


/*  @Test
  void testEliminarPaciente() {

    pacienteService.eliminarPaciente(1);

    assertThrows(ResourceNotFoundException.class, ()-> pacienteService.buscarPorId(1));
  }*/
}
