package dh.backend.demo.service.impl;

import dh.backend.demo.dto.request.DomicilioRequestDto;
import dh.backend.demo.dto.request.PacienteRequestDto;
import dh.backend.demo.dto.response.PacienteResponseDto;
import dh.backend.demo.entity.Domicilio;
import dh.backend.demo.entity.Paciente;
import dh.backend.demo.exception.BadRequestException;
import dh.backend.demo.exception.ResourceNotFoundException;
import dh.backend.demo.respository.PacienteRepository;
import dh.backend.demo.service.IPacienteService;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteService {

  private final PacienteRepository pacienteRepository;
  private static final ModelMapper mapper = new ModelMapper();
  private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);


  public PacienteService(PacienteRepository pacienteRepository) {
    this.pacienteRepository = pacienteRepository;
  }

  @Override
  public PacienteResponseDto registrarPaciente(PacienteRequestDto pacienteDto) {
    if(validarPaciente(pacienteDto)){
      LOGGER.error("Error al crear paciente - Información inválida");
      throw new BadRequestException("Información de paciente inválida");
    }
    Paciente paciente = mapPacienteRequestToModel(pacienteDto);
    paciente.setDomicilio(mapDomicilioRequestToModel(pacienteDto.getDomicilio()));

    Paciente pacienteGuardado = pacienteRepository.save(paciente);
    LOGGER.info("Paciente guardado ->"+pacienteGuardado);
    return mapPacienteModelToResponse(pacienteGuardado);
  }

  @Override
  public PacienteResponseDto buscarPorId(Integer id) {
    Paciente pacienteEncontrado = pacienteRepository.findById(id).orElseThrow(()->{
      LOGGER.error("Paciente con id "+id+" no encontrado");
      throw new ResourceNotFoundException("Paciente con id:"+id+" no encontrado");
    });
    LOGGER.info("Paciente con id "+id+" encontrado -> "+pacienteEncontrado);
    return mapPacienteModelToResponse(pacienteEncontrado);
  }

  @Override
  public List<PacienteResponseDto> buscarTodos() {
    List<PacienteResponseDto> pacientesEncontrados = pacienteRepository.findAll()
            .stream()
            .map(PacienteService::mapPacienteModelToResponse)
            .collect(Collectors.toList());
    LOGGER.info("Pacientes fueron encontrados");
    return pacientesEncontrados;
  }

  @Override
  public List<PacienteResponseDto> buscarPorDni(String dni) {
    List<PacienteResponseDto> pacientesEncontrados = pacienteRepository.findByDni(dni)
            .stream()
            .map(PacienteService::mapPacienteModelToResponse)
            .collect(Collectors.toList());
    LOGGER.info("Pacientes fueron encontrados por DNI");
    return pacientesEncontrados;
  }

  @Override
  public List<PacienteResponseDto> buscarPorDomicilioProvincia(String provincia) {
    List<PacienteResponseDto> pacientesEncontrados = pacienteRepository.findByDomicilioProvincia(provincia)
            .stream()
            .map(PacienteService::mapPacienteModelToResponse)
            .collect(Collectors.toList());
    LOGGER.info("Pacientes fueron encontrados por Provincia");
    return pacientesEncontrados;
  }

  @Override
  public void actualizarPaciente(PacienteRequestDto pacienteDto, Integer idPaciente) {
    if(validarPaciente(pacienteDto)){
      LOGGER.error("Error al actualizar paciente - Información inválida");
      throw new BadRequestException("Información de paciente inválida");

    }
    Paciente paciente = mapPacienteRequestToModel(pacienteDto);
    paciente.setId(idPaciente);
    LOGGER.info("Paciente con id "+idPaciente+" actualizado");
    pacienteRepository.save(paciente);
  }

  @Override
  public void eliminarPaciente(Integer id) {
    pacienteRepository.deleteById(id);
    LOGGER.info("Paciente con id:"+id+" eliminado");
  }

  public static Paciente mapPacienteRequestToModel(PacienteRequestDto paciente){
    return mapper.map(paciente, Paciente.class);
  }

  public static PacienteResponseDto mapPacienteModelToResponse(Paciente paciente){
    return mapper.map(paciente, PacienteResponseDto.class);
  }

  public static Domicilio mapDomicilioRequestToModel(DomicilioRequestDto domicilio){
    return mapper.map(domicilio, Domicilio.class);
  }

  private boolean validarPaciente(PacienteRequestDto pacienteDto){
    if(pacienteDto.getNombre() == null
            || pacienteDto.getEmail() == null
            || pacienteDto.getApellido()==null
            || pacienteDto.getDni() == null){
      return true;
    }
    return pacienteDto.getNombre().isBlank()
             || pacienteDto.getEmail().isBlank()
            || pacienteDto.getApellido().isBlank()
            || pacienteDto.getDni().isBlank();
  }
}
