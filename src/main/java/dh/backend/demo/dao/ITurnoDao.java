package dh.backend.demo.dao;

import dh.backend.demo.model.Turno;

import java.util.List;

public interface ITurnoDao {
    Turno registrar (Turno t);
    Turno buscarPorId(Integer id);
    List<Turno> buscarTodos();
    void actualizar(Turno t);
    void eliminar(Integer id);
}
