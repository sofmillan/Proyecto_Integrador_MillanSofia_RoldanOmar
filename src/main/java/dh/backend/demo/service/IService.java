package dh.backend.demo.service;

import java.util.List;

public interface IService<T> {
    T registrar(T paciente);
    T buscarPorId(Integer id);
    List<T> buscarTodos();
    void actualizar(T paciente);
    void eliminar(Integer id);
}
