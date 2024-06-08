package dh.backend.demo.service;

import dh.backend.demo.entity.Turno;
import java.util.List;
import java.util.Optional;

public interface ITurnoService {
  Turno registrar(Turno turno);

  Optional<Turno> buscarPorId(Integer id);

  List<Turno> buscarTodos();

  void actualizarTurno(Turno turno);

  void eliminarTurno(Integer id);
}
