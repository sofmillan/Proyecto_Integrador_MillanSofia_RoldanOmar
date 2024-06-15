package dh.backend.demo.service;

import dh.backend.demo.entity.Paciente;
import java.util.List;
import java.util.Optional;

public interface IPacienteService {
  Paciente registrarPaciente(Paciente paciente);

  Optional<Paciente> buscarPorId(Integer id);

  List<Paciente> buscarTodos();

  List<Paciente> buscarPorDni(String dni);

  List<Paciente> buscarPorDomicilioProvincia(String provincia);

  void actualizarPaciente(Paciente paciente);

  void eliminarPaciente(Integer id);
}
