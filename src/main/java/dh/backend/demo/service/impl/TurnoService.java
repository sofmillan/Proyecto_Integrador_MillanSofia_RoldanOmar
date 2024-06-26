package dh.backend.demo.service.impl;


import dh.backend.demo.dto.request.TurnoRequestDto;
import dh.backend.demo.dto.response.TurnoResponseDto;
import dh.backend.demo.entity.Odontologo;
import dh.backend.demo.entity.Paciente;
import dh.backend.demo.entity.Turno;
import dh.backend.demo.exception.BadRequestException;
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
  private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

  public TurnoService(TurnoRepository turnoRepository, PacienteRepository pacienteRepository, OdontologoRepository odontologoRepository) {
    this.turnoRepository = turnoRepository;
    this.pacienteRepository = pacienteRepository;
    this.odontologoRepository = odontologoRepository;
  }

  @Override
  public TurnoResponseDto registrar(TurnoRequestDto turno) {
    if(validarTurno(turno)){
      LOGGER.error("Error al guardar turno - Información de turno inválida");
      throw new BadRequestException("Información de turno inválida");
    }

    Paciente pacienteBuscado = pacienteRepository.findById(turno.getPacienteId()).orElseThrow(()->{
      LOGGER.error("Paciente no encontrado");
      throw new ResourceNotFoundException("Paciente no encontrado");
    });
    Odontologo odontologoBuscado = odontologoRepository.findById(turno.getOdontologoId()).orElseThrow(()->{
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
      LOGGER.error("Turno con id:"+id+" no encontrado");
      throw new ResourceNotFoundException("Turno con id:"+id+" no encontrado");
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
    if(validarTurno(turno)){
      LOGGER.error("Error al actualizar turno - Información de turno inválida");
      throw new BadRequestException("Información de turno inválida");
    }

    Paciente pacienteBuscado = pacienteRepository.findById(turno.getPacienteId()).orElseThrow(()->{
      LOGGER.error("Paciente no encontrado");
      throw new ResourceNotFoundException("Paciente no encontrado");
    });
    Odontologo odontologoBuscado = odontologoRepository.findById(turno.getOdontologoId()).orElseThrow(()->{
      LOGGER.error("Odontólogo no encontrado");
      throw new ResourceNotFoundException("Odontólogo no encontrado");
    });

    Turno turnoARegistrar = new Turno();
    turnoARegistrar.setId(turnoId);
    turnoARegistrar.setOdontologo(odontologoBuscado);
    turnoARegistrar.setPaciente(pacienteBuscado);
    turnoARegistrar.setFecha(LocalDate.parse(turno.getFecha()));

    turnoRepository.save(turnoARegistrar);
    LOGGER.info("Turno con id:"+turnoId+" actualizado");
  }

  @Override
  public void eliminarTurno(Integer id) {
    this.buscarPorId(id);

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
    LOGGER.info("Turnos encontrados por nombre paciente");
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

  private static boolean validarTurno(TurnoRequestDto turnoDto){
    if(turnoDto.getFecha() == null || turnoDto.getPacienteId() == null || turnoDto.getOdontologoId() == null){
      return true;
    }
    return turnoDto.getFecha().isEmpty();
  }
}
