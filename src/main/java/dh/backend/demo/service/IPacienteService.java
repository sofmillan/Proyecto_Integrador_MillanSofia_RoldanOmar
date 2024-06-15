package dh.backend.demo.service;

import dh.backend.demo.dto.request.PacienteRequestDto;
import dh.backend.demo.dto.response.PacienteResponseDto;
import dh.backend.demo.entity.Paciente;
import java.util.List;
import java.util.Optional;

public interface IPacienteService {
  PacienteResponseDto registrarPaciente(PacienteRequestDto paciente);

  Optional<Paciente> buscarPorId(Integer id);

  List<Paciente> buscarTodos();

  void actualizarPaciente(Paciente paciente);

  void eliminarPaciente(Integer id);
}
