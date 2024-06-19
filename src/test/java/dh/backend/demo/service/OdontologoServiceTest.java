package dh.backend.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.response.OdontologoResponseDto;

import java.util.List;

import dh.backend.demo.exception.ResourceNotFoundException;
import dh.backend.demo.service.impl.OdontologoService;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
  @Autowired
  private OdontologoService odontologoService;

  @Test
  @Order(1)
  void testOdontologoGuardado() {
   OdontologoRequestDto odontologo = new OdontologoRequestDto("12345", "Ricky","Shen");

    OdontologoResponseDto odontologoGuardado = odontologoService.registrarOdontologo(odontologo);

    assertNotNull(odontologoGuardado);
  }

  @Test
  @Order(2)
  void testBuscarOdontologoPorId() {
    Integer id = 1;
    OdontologoResponseDto odontologoEncontrado = odontologoService.buscarOdontologoPorId(id);

    assertEquals(id, odontologoEncontrado.getId());
  }
  @Test
  @Order(3)
  void testBuscarTodosOdontologos() {
    List<OdontologoResponseDto> odontologos = odontologoService.buscarTodos();

    assertTrue(odontologos.size() != 0);
  }

    @Test
    @Order(4)
    void testActualizarOdontologo() {
        OdontologoRequestDto odontologo = new OdontologoRequestDto("999", "Ricky","Shen");

        odontologoService.actualizarOdontologo(odontologo, 1);

        assertEquals("999", odontologoService.buscarOdontologoPorId(1).getNroMatricula());
    }

    @Test
    @Order(5)
    void testBuscarOdontologoPorNombre() {
        odontologoService.registrarOdontologo(new OdontologoRequestDto("12345", "Hannah","Marin"));

        List<OdontologoResponseDto> odontologos = odontologoService.buscarPorNombre("Hannah");

        assertTrue(odontologos.size() != 0);
    }

    @Test
    @Order(6)
    void testBuscarOdontologoPorNumeroMatricula() {
        OdontologoResponseDto odontologo = odontologoService.buscarPorMatricula("999");

        assertNotNull(odontologo);
    }

    @Test
    @Order(7)
    void testEliminarOdontologo() {

        odontologoService.eliminarOdontologo(1);

        assertThrows(ResourceNotFoundException.class, ()-> odontologoService.buscarOdontologoPorId(1));
    }
}
