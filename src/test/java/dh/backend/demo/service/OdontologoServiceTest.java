package dh.backend.demo.service;

import dh.backend.demo.dao.impl.OdontologoDaoH2;
import dh.backend.demo.model.Odontologo;
import dh.backend.demo.repository.IOdontologoRepository;
import dh.backend.demo.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class OdontologoServiceTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(PacienteServiceTest.class);
    private static IOdontologoService odontologoService = new OdontologoService(mock(IOdontologoRepository.class));
    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/demo;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
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
    void testOdontologoGuardado(){
       /* Odontologo odontologo = new Odontologo("123","Ricky","Shen");
        Odontologo odontologoGuardado = odontologoService.registrarOdontologo(odontologo);
        assertNotNull(odontologoGuardado);*/
    }

    @Test
    void testOdontologoId(){
        Integer id = 1;
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarOdontologoPorId(id);

        assertTrue(odontologoEncontrado.isPresent());
        assertEquals(id, odontologoEncontrado.get().getId());
    }

    @Test
    void testBusquedaTodos() {
     /*   Optional<Odontologo> odontologo = new Odontologo("123","Ricky","Shen");

        odontologoService.registrarOdontologo(odontologo.get());

        List<Odontologo> odontologos = odontologoService.buscarTodos();

        assertTrue(odontologos.size()!=0);*/

    }
}
