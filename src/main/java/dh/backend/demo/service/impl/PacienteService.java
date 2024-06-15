package dh.backend.demo.service.impl;

import dh.backend.demo.dto.request.PacienteRequestDto;
import dh.backend.demo.dto.response.PacienteResponseDto;
import dh.backend.demo.entity.Paciente;
import dh.backend.demo.respository.PacienteRepository;
import dh.backend.demo.service.IPacienteService;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteService {

  private final PacienteRepository pacienteRepository;
  private final ModelMapper mapper;

  public PacienteService(PacienteRepository pacienteRepository, ModelMapper mapper) {
    this.pacienteRepository = pacienteRepository;
    this.mapper = mapper;
  }

  @Override
  public PacienteResponseDto registrarPaciente(PacienteRequestDto paciente) {
    return modelToResponse(pacienteRepository.save(responseToModel(paciente)));
  }

  @Override
  public Optional<Paciente> buscarPorId(Integer id) {
    return pacienteRepository.findById(id);
  }

  @Override
  public List<Paciente> buscarTodos() {
    return pacienteRepository.findAll();
  }

  @Override
  public void actualizarPaciente(Paciente paciente) {
    pacienteRepository.save(paciente);
  }

  @Override
  public void eliminarPaciente(Integer id) {
    pacienteRepository.deleteById(id);
  }

  private Paciente responseToModel(PacienteRequestDto paciente){
    return mapper.map(paciente, Paciente.class);
  }

  private PacienteResponseDto modelToResponse(Paciente paciente){
    return mapper.map(paciente, PacienteResponseDto.class);
  }
}
