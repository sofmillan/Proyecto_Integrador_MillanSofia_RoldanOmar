package dh.backend.demo.dao;

import dh.backend.demo.model.Odontologo;

import java.util.List;

public interface OdontologoDao {
    Odontologo registrar (Odontologo t);
    Odontologo buscarPorId(Integer id);
    List<Odontologo> buscarTodos();
    void actualizar(Odontologo t);
    void eliminar(Integer id);
}
