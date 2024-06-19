package dh.backend.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.response.OdontologoResponseDto;

import java.util.List;

import dh.backend.demo.exception.ResourceNotFoundException;
import dh.backend.demo.service.impl.OdontologoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OdontologoServiceTest {
  @Autowired
  private OdontologoService odontologoService;

  @Test
  void testOdontologoGuardado() {
   OdontologoRequestDto odontologo = new OdontologoRequestDto("12345", "Ricky","Shen");

    OdontologoResponseDto odontologoGuardado = odontologoService.registrarOdontologo(odontologo);

    assertNotNull(odontologoGuardado);
  }

  @Test
  void testOdontologoId() {
    Integer id = 1;
    OdontologoResponseDto odontologoEncontrado = odontologoService.buscarOdontologoPorId(id);

    assertEquals(id, odontologoEncontrado.getId());
  }
  @Test
  void testBusquedaTodos() {
    List<OdontologoResponseDto> odontologos = odontologoService.buscarTodos();

    assertTrue(odontologos.size() != 0);
  }

    @Test
    void testActualizarOdontologo() {
        OdontologoRequestDto odontologo = new OdontologoRequestDto("999", "Ricky","Shen");

        odontologoService.actualizarOdontologo(odontologo, 1);

        assertEquals("999", odontologoService.buscarOdontologoPorId(1).getNroMatricula());
    }

    @Test
    void testBuscarPorNombre() {
        odontologoService.registrarOdontologo(new OdontologoRequestDto("12345", "Hannah","Marin"));

        List<OdontologoResponseDto> odontologos = odontologoService.buscarPorNombre("Hannah");

        assertTrue(odontologos.size() != 0);
    }

    @Test
    void testBuscarPorNumeroMatricula() {
        OdontologoResponseDto odontologo = odontologoService.buscarPorMatricula("999");

        assertNotNull(odontologo);
    }

    @Test
    void testEliminarOdontologo() {

        odontologoService.eliminarOdontologo(1);

        assertThrows(ResourceNotFoundException.class, ()-> odontologoService.buscarOdontologoPorId(1));
    }
}
