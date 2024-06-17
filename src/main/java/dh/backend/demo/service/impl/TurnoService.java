package dh.backend.demo.service.impl;


import dh.backend.demo.dto.request.TurnoRequestDto;
import dh.backend.demo.entity.Odontologo;
import dh.backend.demo.entity.Paciente;
import dh.backend.demo.entity.Turno;
import dh.backend.demo.exception.ResourceNotFoundException;
import dh.backend.demo.respository.OdontologoRepository;
import dh.backend.demo.respository.PacienteRepository;
import dh.backend.demo.respository.TurnoRepository;
import dh.backend.demo.service.ITurnoService;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TurnoService implements ITurnoService {
  private final TurnoRepository turnoRepository;
  private final PacienteRepository pacienteRepository;
  private final OdontologoRepository odontologoRepository;
  private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

  public TurnoService(TurnoRepository turnoRepository, PacienteRepository pacienteRepository, OdontologoRepository odontologoRepository) {
    this.turnoRepository = turnoRepository;
    this.pacienteRepository = pacienteRepository;
    this.odontologoRepository = odontologoRepository;
  }

  @Override
  public Turno registrar(TurnoRequestDto turno) {
    Paciente pacienteBuscado = pacienteRepository.findById(turno.getPaciente_id()).orElseThrow(()->{
      LOGGER.error("Paciente no encontrado");
      throw new ResourceNotFoundException("Paciente no encontrado");
    });
    Odontologo odontologoBuscado = odontologoRepository.findById(turno.getOdontologo_id()).orElseThrow(()->{
      LOGGER.error("Odontólogo no encontrado");
      throw new ResourceNotFoundException("Paciente no encontrado");
    });

    Turno turnoARegistrar = new Turno();
    Turno turnoADevolver = null;
/*    turnoARegistrar.setOdontologo(odontologoBuscado.get());
    turnoARegistrar.setPaciente(pacienteBuscado.get());
    turnoARegistrar.setFecha(turno.getFecha());
    turnoADevolver = turnoRepository.save(turnoARegistrar);
    */
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
    Optional<Paciente> pacienteBuscado = pacienteRepository.findById(turno.getPaciente().getId());
    Optional<Odontologo> odontologoBuscado =
        odontologoRepository.findById(turno.getOdontologo().getId());
    Turno turnoAModificar = new Turno();
    if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
      turnoAModificar.setId(turno.getId());
      turnoAModificar.setOdontologo(odontologoBuscado.get());
      turnoAModificar.setPaciente(pacienteBuscado.get());
      turnoAModificar.setFecha(turno.getFecha());
      turnoRepository.save(turnoAModificar);
    }
  }

  @Override
  public void eliminarTurno(Integer id) {
    turnoRepository.deleteById(id);
  }
}
