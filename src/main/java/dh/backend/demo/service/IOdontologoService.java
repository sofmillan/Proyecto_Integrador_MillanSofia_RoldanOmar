package dh.backend.demo.service;

import dh.backend.demo.entity.Odontologo;
import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
  Odontologo registrarOdontologo(Odontologo odontologo);

  Optional<Odontologo> buscarOdontologoPorId(Integer id);

  List<Odontologo> buscarTodos();

  void actualizarOdontologo(Odontologo paciente);

  void eliminarOdontologo(Integer id);
}
