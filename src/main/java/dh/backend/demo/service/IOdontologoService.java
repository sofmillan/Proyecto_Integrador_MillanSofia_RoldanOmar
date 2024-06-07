package dh.backend.demo.service;

import dh.backend.demo.model.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);

    Optional<Odontologo> buscarOdontologoPorId(Integer id);
    List<Odontologo> buscarTodos();

    void modificarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Integer id);
}
