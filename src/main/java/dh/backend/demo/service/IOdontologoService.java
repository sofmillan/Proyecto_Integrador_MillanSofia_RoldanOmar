package dh.backend.demo.service;

import dh.backend.demo.model.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);
    Odontologo buscarOdontologoPorId(Integer id);
    List<Odontologo> buscarTodos();
    void actualizarOdontologo(Odontologo paciente);
    void eliminarOdontologo(Integer id);
}
