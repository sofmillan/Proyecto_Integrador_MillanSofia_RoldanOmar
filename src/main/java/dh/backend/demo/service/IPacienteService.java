package dh.backend.demo.service;

import dh.backend.demo.model.Paciente;
import java.util.List;

public interface IPacienteService {
  Paciente registrarPaciente(Paciente paciente);

  Paciente buscarPorId(Integer id);

  List<Paciente> buscarTodos();

  void actualizarPaciente(Paciente paciente);

  void eliminarPaciente(Integer id);
}
