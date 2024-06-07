package dh.backend.demo.service.impl;

import dh.backend.demo.entity.Odontologo;
import dh.backend.demo.entity.Paciente;
import dh.backend.demo.entity.Turno;
import dh.backend.demo.respository.OdontologoRepository;
import dh.backend.demo.respository.PacienteRepository;
import dh.backend.demo.respository.TurnoRepository;
import dh.backend.demo.service.ITurnoService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TurnoService implements ITurnoService {
  private TurnoRepository turnoRepository;
  private PacienteRepository pacienteRepository;
  private OdontologoRepository odontologoRepository;

  public TurnoService(
      TurnoRepository turnoRepository,
      PacienteRepository pacienteRepository,
      OdontologoRepository odontologoRepository) {
    this.turnoRepository = turnoRepository;
    this.pacienteRepository = pacienteRepository;
    this.odontologoRepository = odontologoRepository;
  }

  @Override
  public Turno registrar(Turno turno) {
    Paciente paciente = pacienteRepository.buscarPorId(turno.getPaciente().getId());
    Odontologo odontologo = odontologoRepository.buscarPorId(turno.getOdontologo().getId());
    Turno turnoARegistrar = new Turno();
    Turno turnoADevolver = null;
    if (paciente != null && odontologo != null) {
      turnoARegistrar.setOdontologo(odontologo);
      turnoARegistrar.setPaciente(paciente);
      turnoARegistrar.setFecha(turno.getFecha());
      turnoADevolver = turnoRepository.save(turnoARegistrar);
    }
    return turnoADevolver;
  }

  @Override
  public Optional<Turno> buscarPorId(Integer id) {
    return turnoRepository.findById(id);
  }

  @Override
  public List<Turno> buscarTodos() {
    return turnoRepository.findAll();
  }

  @Override
  public void actualizarTurno(Turno turno) {
    Paciente paciente = pacienteRepository.buscarPorId(turno.getPaciente().getId());
    Odontologo odontologo = odontologoRepository.buscarPorId(turno.getOdontologo().getId());
    Turno turnoAModificar = new Turno();
    if (paciente != null && odontologo != null) {
      turnoAModificar.setId(turno.getId());
      turnoAModificar.setOdontologo(odontologo);
      turnoAModificar.setPaciente(paciente);
      turnoAModificar.setFecha(turno.getFecha());
      turnoRepository.save(turnoAModificar);
    }
  }

  @Override
  public void eliminarTurno(Integer id) {
    turnoRepository.deleteById(id);
  }
}

