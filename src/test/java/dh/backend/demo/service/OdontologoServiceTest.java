package dh.backend.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.request.OdontologoUpdateDto;
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
   OdontologoRequestDto odontologo = new OdontologoRequestDto("12345", "Nombre","Apellido");

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
        OdontologoUpdateDto odontologo = new OdontologoUpdateDto(1,"999", "Nombre","Apellido");

        odontologoService.actualizarOdontologo(odontologo);

        assertEquals("999", odontologoService.buscarOdontologoPorId(1).getNroMatricula());
    }

    @Test
    void testEliminarOdontologo() {

        odontologoService.eliminarOdontologo(1);

        assertThrows(ResourceNotFoundException.class, ()-> odontologoService.buscarOdontologoPorId(1));
    }
}
