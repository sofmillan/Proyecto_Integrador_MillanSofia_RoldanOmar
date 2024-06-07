package dh.backend.demo.service.impl;

import dh.backend.demo.entity.Paciente;
import dh.backend.demo.respository.PacienteRepository;
import dh.backend.demo.service.IPacienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteService {

  private PacienteRepository pacienteRepository;

  public PacienteService(PacienteRepository pacienteRepository) {
    this.pacienteRepository = pacienteRepository;
  }

  @Override
  public Paciente registrarPaciente(Paciente paciente) {
    return pacienteRepository.save(paciente);
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
}
