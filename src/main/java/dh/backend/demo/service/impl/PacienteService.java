package dh.backend.demo.service.impl;

import dh.backend.demo.dto.request.DomicilioRequestDto;
import dh.backend.demo.dto.request.PacienteRequestDto;
import dh.backend.demo.dto.response.DomicilioResponseDto;
import dh.backend.demo.dto.response.PacienteResponseDto;
import dh.backend.demo.entity.Domicilio;
import dh.backend.demo.entity.Paciente;
import dh.backend.demo.exception.BadRequestException;
import dh.backend.demo.exception.ResourceNotFoundException;
import dh.backend.demo.respository.PacienteRepository;
import dh.backend.demo.service.IPacienteService;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteService {

  private final PacienteRepository pacienteRepository;
  private final ModelMapper mapper;
  private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);


  public PacienteService(PacienteRepository pacienteRepository, ModelMapper mapper) {
    this.pacienteRepository = pacienteRepository;
    this.mapper = mapper;
  }

  @Override
  public PacienteResponseDto registrarPaciente(PacienteRequestDto paciente) {
    if(paciente.getNombre() == null ){
      LOGGER.error("Error al crear paciente - Información inválida");
      throw new BadRequestException("Información de paciente inválida");
    }
    Paciente paciente1 = requestToModel(paciente);
    paciente1.setDomicilio(domicilioRequestToModel(paciente.getDomicilio()));
    return modelToResponse(pacienteRepository.save(paciente1));
  }

  @Override
  public PacienteResponseDto buscarPorId(Integer id) {
    Paciente pacienteEncontrado = pacienteRepository.findById(id).orElseThrow(()->{
      LOGGER.error("Paciente con id "+id+" no encontrado");
      throw new ResourceNotFoundException("Paciente con id:"+id+" no encontrado");
    });
    return modelToResponse(pacienteEncontrado);
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

  private Paciente requestToModel(PacienteRequestDto paciente){
    return mapper.map(paciente, Paciente.class);
  }

  private PacienteResponseDto modelToResponse(Paciente paciente){
    return mapper.map(paciente, PacienteResponseDto.class);
  }

  private Domicilio domicilioRequestToModel(DomicilioRequestDto domicilio){
    return mapper.map(domicilio, Domicilio.class);
  }
}
