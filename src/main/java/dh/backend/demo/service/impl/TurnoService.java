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
import java.util.stream.Collectors;

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
    LOGGER.info("Turno guardado");

    return mapModelToResponse(turnoGuardado);
  }

  @Override
  public TurnoResponseDto buscarPorId(Integer id) {
    Turno turnoEncontrado = turnoRepository.findById(id).orElseThrow(()->{
      LOGGER.error("Turno con id:"+id+" no enconrado");
      throw new ResourceNotFoundException("Turno con id:"+id+" no enconrado");
    });
    LOGGER.info("Turno con id:"+id+" encontrado");
    return mapModelToResponse(turnoEncontrado);
  }

  @Override
  public List<TurnoResponseDto> buscarTodos() {
    List<TurnoResponseDto> turnosEncontrados = turnoRepository.findAll()
            .stream()
            .map(this::mapModelToResponse)
            .collect(Collectors.toList());
    LOGGER.info("Turnos encontrados");
    return turnosEncontrados;
  }

  @Override
  public void actualizarTurno(TurnoRequestDto turno, Integer turnoId) {
    Paciente pacienteBuscado = pacienteRepository.findById(turno.getPaciente_id()).orElseThrow(()->{
      LOGGER.error("Paciente no encontrado");
      throw new ResourceNotFoundException("Paciente no encontrado");
    });
    Odontologo odontologoBuscado = odontologoRepository.findById(turno.getOdontologo_id()).orElseThrow(()->{
      LOGGER.error("Odontólogo no encontrado");
      throw new ResourceNotFoundException("Odontólogo no encontrado");
    });

    Turno turnoARegistrar = new Turno();
    turnoARegistrar.setId(turnoId);
    turnoARegistrar.setOdontologo(odontologoBuscado);
    turnoARegistrar.setPaciente(pacienteBuscado);
    turnoARegistrar.setFecha(LocalDate.parse(turno.getFecha()));

    turnoRepository.save(turnoARegistrar);
    LOGGER.info("Turno actualizado");

  }

  @Override
  public void eliminarTurno(Integer id) {
    turnoRepository.deleteById(id);
    LOGGER.info("Turno con id:"+id+" eliminado");

  }

  @Override
  public List<TurnoResponseDto> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate) {
    List<TurnoResponseDto> turnosEncontrados = turnoRepository.buscarTurnoEntreFechas(startDate, endDate)
            .stream()
            .map(this::mapModelToResponse)
            .collect(Collectors.toList());
    LOGGER.info("Turnos encontrados entre fechas");
    return turnosEncontrados;
  }

  @Override
  public List<TurnoResponseDto> buscarTurnoPorPaciente(String nombre) {
    List<TurnoResponseDto> turnosEncontrados = turnoRepository.findByPacienteNombre(nombre)
            .stream()
            .map(this::mapModelToResponse)
            .collect(Collectors.toList());
    LOGGER.info("Turnos encontrados por paciente");
    return turnosEncontrados;
  }

  private TurnoResponseDto mapModelToResponse(Turno turno){
    TurnoResponseDto turnoResponse = new TurnoResponseDto();
    turnoResponse.setId(turno.getId());
    turnoResponse.setPaciente(PacienteService.mapPacienteModelToResponse(turno.getPaciente()));
    turnoResponse.setOdontologo(OdontologoService.mapModelToResponse(turno.getOdontologo()));
    turnoResponse.setFecha(turno.getFecha());
   return turnoResponse;
  }
}
