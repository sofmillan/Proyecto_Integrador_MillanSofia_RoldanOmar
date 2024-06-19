package dh.backend.demo.service;

import dh.backend.demo.dto.request.DomicilioRequestDto;
import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.request.PacienteRequestDto;
import dh.backend.demo.dto.request.TurnoRequestDto;
import dh.backend.demo.dto.response.TurnoResponseDto;
import dh.backend.demo.exception.ResourceNotFoundException;
import dh.backend.demo.service.impl.OdontologoService;
import dh.backend.demo.service.impl.PacienteService;
import dh.backend.demo.service.impl.TurnoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;


    @Test
    @Order(1)
    void testGuardarTurno() {
        DomicilioRequestDto domicilioRequest = new DomicilioRequestDto(null, "Calle 45", 12, "Pereira", "Quindío");
        PacienteRequestDto pacienteRequest = new PacienteRequestDto("Millán", "Sofía", "example@gmail.com", "123", LocalDate.of(2021,8,1), domicilioRequest);
        OdontologoRequestDto odontologoRequest = new OdontologoRequestDto("12345", "Ricky","Shen");
        TurnoRequestDto turnoRequest = new TurnoRequestDto(1, 1, "2024-05-05");

        pacienteService.registrarPaciente(pacienteRequest);
        odontologoService.registrarOdontologo(odontologoRequest);

        TurnoResponseDto turnoGuardado = turnoService.registrar(turnoRequest);

        assertNotNull(turnoGuardado);
    }

    @Test
    @Order(2)
    void testBuscarTurnoPorId() {
        Integer id = 1;

        TurnoResponseDto turnoEncontrado = turnoService.buscarPorId(id);

        assertEquals(id, turnoEncontrado.getId());
    }

    @Test
    @Order(3)
    void testBuscarTodosTurnos(){
        List<TurnoResponseDto> turnosEncontrados = turnoService.buscarTodos();

        assertTrue(turnosEncontrados.size() != 0);
    }

    @Test
    @Order(4)
    void testBuscarTurnosEntreFechas(){
        List<TurnoResponseDto> turnosEncontrados = turnoService.buscarTurnoEntreFechas(LocalDate.of(2024,4,4),LocalDate.of(2024,6,6) );

        assertTrue(turnosEncontrados.size() != 0);
    }

    @Test
    @Order(5)
    void testBuscarTurnosPorNombrePaciente(){
        List<TurnoResponseDto> turnosEncontrados = turnoService.buscarTurnoPorPaciente("Sofía");

        assertTrue(turnosEncontrados.size() != 0);
    }

    @Test
    @Order(6)
    void testActualizarTurno(){
        OdontologoRequestDto odontologoRequest = new OdontologoRequestDto("12345", "Hannah","Marin");
        odontologoService.registrarOdontologo(odontologoRequest);


        TurnoRequestDto turnoRequest = new TurnoRequestDto(1, 2, "2024-05-05");

        turnoService.actualizarTurno(turnoRequest, 1);

        assertEquals(2, turnoService.buscarPorId(1).getOdontologo().getId());
    }

    @Test
    @Order(7)
    void testEliminarTurno(){
        Integer turnoId = 1;

        turnoService.eliminarTurno(turnoId);

        assertThrows(ResourceNotFoundException.class, ()-> turnoService.buscarPorId(turnoId));
    }
}
