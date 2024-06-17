package dh.backend.demo.service.impl;


import dh.backend.demo.dto.request.TurnoRequestDto;
import dh.backend.demo.dto.response.TurnoResponseDto;
import dh.backend.demo.entity.Odontologo;
import dh.backend.demo.entity.Paciente;
import dh.backend.demo.entity.Turno;
import dh.backend.demo.exception.ResourceNotFoundException;
import dh.backend.demo.respository.OdontologoRepository;
import dh.backend.demo.respository.PacienteRepository;
import dh.backend.demo.respository.TurnoRepository;
import dh.backend.demo.service.ITurnoService;

import java.time.LocalDate;
import java.util.List;

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
  public TurnoResponseDto registrar(TurnoRequestDto turno) {
    Paciente pacienteBuscado = pacienteRepository.findById(turno.getPaciente_id()).orElseThrow(()->{
      LOGGER.error("Paciente no encontrado");
      throw new ResourceNotFoundException("Paciente no encontrado");
    });
    Odontologo odontologoBuscado = odontologoRepository.findById(turno.getOdontologo_id()).orElseThrow(()->{
      LOGGER.error("Odontólogo no encontrado");
      throw new ResourceNotFoundException("Odontólogo no encontrado");
    });

    Turno turnoARegistrar = new Turno();
    turnoARegistrar.setOdontologo(odontologoBuscado);
    turnoARegistrar.setPaciente(pacienteBuscado);
    turnoARegistrar.setFecha(LocalDate.parse(turno.getFecha()));
    Turno turnoGuardado = turnoRepository.save(turnoARegistrar);

    TurnoResponseDto turnoResponse = new TurnoResponseDto();
    turnoResponse.setId(turnoGuardado.getId());
    turnoResponse.setPaciente(PacienteService.mapPacienteModelToResponse(turnoGuardado.getPaciente()));
    turnoResponse.setOdontologo(OdontologoService.mapModelToResponse(turnoGuardado.getOdontologo()));
    turnoResponse.setFecha(turnoGuardado.getFecha());
    return turnoResponse;
  }

  @Override
  public TurnoResponseDto buscarPorId(Integer id) {
    return null;
  }

  @Override
  public List<TurnoResponseDto> buscarTodos() {
    return null;
  }

  @Override
  public void actualizarTurno(TurnoRequestDto turno) {
 /*   Optional<Paciente> pacienteBuscado = pacienteRepository.findById(turno.getPaciente().getId());
    Optional<Odontologo> odontologoBuscado =
        odontologoRepository.findById(turno.getOdontologo().getId());
    Turno turnoAModificar = new Turno();
    if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
      turnoAModificar.setId(turno.getId());
      turnoAModificar.setOdontologo(odontologoBuscado.get());
      turnoAModificar.setPaciente(pacienteBuscado.get());
      turnoAModificar.setFecha(turno.getFecha());
      turnoRepository.save(turnoAModificar);
    }*/
  }

  @Override
  public void eliminarTurno(Integer id) {
    turnoRepository.deleteById(id);
  }

  @Override
  public List<TurnoResponseDto> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate) {
    return null;
  }

  @Override
  public List<TurnoResponseDto> buscarTurnoPorPaciente(String Nombre) {
    return null;
  }

  private TurnoResponseDto mapModelToResponse(Turno turno){
    TurnoResponseDto n = new TurnoResponseDto();
    n.setId(turno.getId());
  return null;
  }
}
