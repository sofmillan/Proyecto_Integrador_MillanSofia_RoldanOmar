package dh.backend.demo.service;

import dh.backend.demo.dto.request.PacienteRequestDto;
import dh.backend.demo.dto.response.PacienteResponseDto;
import dh.backend.demo.entity.Paciente;
import java.util.List;
import java.util.Optional;

public interface IPacienteService {
  PacienteResponseDto registrarPaciente(PacienteRequestDto paciente);

  PacienteResponseDto buscarPorId(Integer id);

  List<PacienteResponseDto> buscarTodos();

  PacienteResponseDto buscarPorDni(String dni);

  List<PacienteResponseDto> buscarPorDomicilioProvincia(String provincia);

  void actualizarPaciente(PacienteRequestDto paciente, Integer idPaciente);

  void eliminarPaciente(Integer id);
}
