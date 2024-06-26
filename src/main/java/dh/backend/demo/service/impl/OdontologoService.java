package dh.backend.demo.service.impl;

import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.response.OdontologoResponseDto;
import dh.backend.demo.entity.Odontologo;
import dh.backend.demo.exception.BadRequestException;
import dh.backend.demo.exception.ResourceNotFoundException;
import dh.backend.demo.respository.OdontologoRepository;
import dh.backend.demo.service.IOdontologoService;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OdontologoService implements IOdontologoService {
  private final OdontologoRepository repository;
  private static final ModelMapper mapper = new ModelMapper();
  private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

  public OdontologoService(OdontologoRepository repository) {
    this.repository = repository;
  }

  @Override
  public OdontologoResponseDto registrarOdontologo(OdontologoRequestDto odontologoDto) {
    if(validarOdontogo(odontologoDto)){
      LOGGER.error("Error al guardar odontólogo - Información de odontólogo inválida");
      throw new BadRequestException("Información de odontólogo inválida");
    }

    Odontologo odontologoGuardado = repository.save(mapRequestToModel(odontologoDto));
    LOGGER.info("Odontólogo guardado exitosamente "+odontologoGuardado);

    return mapModelToResponse(odontologoGuardado);
  }

  @Override
  public OdontologoResponseDto buscarOdontologoPorId(Integer id) {
    Odontologo odontologoEncontrado = repository.findById(id).orElseThrow(
            ()-> {
              LOGGER.error("Error al buscar odontólogo - Odontologo con id:"+id+" no encantrado");
              throw new ResourceNotFoundException("Odontólogo con id:"+id+" no encontrado");
            });
    LOGGER.info("Paciente con id:"+id+" encontrado -> "+odontologoEncontrado);
    return mapModelToResponse(odontologoEncontrado);
  }

  @Override
  public List<OdontologoResponseDto> buscarTodos() {
    List<OdontologoResponseDto> odontologosEncontrados = repository.findAll()
            .stream()
            .map(OdontologoService::mapModelToResponse)
            .collect(Collectors.toList());
    LOGGER.info("Odontólogos fueron encontrados");
    return odontologosEncontrados;
  }

  @Override
  public void actualizarOdontologo(OdontologoRequestDto odontologoDto, Integer idOdontologo) {
    if(validarOdontogo(odontologoDto)){
      LOGGER.error("Error al actualizar odontólogo - Información de odontólogo inválida");
      throw new BadRequestException("Debe proveer id para actualizar odontólogo");
    }
    Odontologo odontologoGuardar = mapRequestToModel(odontologoDto);
    odontologoGuardar.setId(idOdontologo);
    repository.save(odontologoGuardar);
    LOGGER.info("Odontólogo con id:"+idOdontologo+" actualizado");
  }

  @Override
  public void eliminarOdontologo(Integer id) {
    this.buscarOdontologoPorId(id);
    repository.deleteById(id);
    LOGGER.info("Odontólogo con id:"+id+" eliminado");
  }

  @Override
  public List<OdontologoResponseDto> buscarPorNombre(String nombre) {
    System.out.println(nombre);
    List<OdontologoResponseDto> odontologosEncontrados = repository.findByNombre(nombre)
            .stream()
            .map(OdontologoService::mapModelToResponse)
            .collect(Collectors.toList());
    System.out.println(odontologosEncontrados);
    LOGGER.info("Odontólogos fueron encontrados por nombre");
    return odontologosEncontrados;
  }

  @Override
  public OdontologoResponseDto buscarPorMatricula(String matricula) {
    Odontologo odontologoEncontrado = repository.findTopByNroMatricula(matricula).orElseThrow(
            ()-> {
              LOGGER.error("Error al buscar odontólogo - Odontologo con matricula "+matricula+" no encantrado");
              throw new ResourceNotFoundException("Odontólogo con matricula "+matricula+" no encontrado");
            });
    LOGGER.info("Odontólogo encontrado por nro de matrícula:"+matricula+" ->"+odontologoEncontrado);
    return mapModelToResponse(odontologoEncontrado);
  }

  public static Odontologo mapRequestToModel(OdontologoRequestDto odontologo){
    return mapper.map(odontologo, Odontologo.class);
  }
  public static OdontologoResponseDto mapModelToResponse(Odontologo odontologo){
    return mapper.map(odontologo, OdontologoResponseDto.class);
  }

  private static boolean validarOdontogo(OdontologoRequestDto odontologo){
    if(odontologo.getNroMatricula() == null || odontologo.getNombre() == null || odontologo.getApellido() == null){
      return true;
    }

    return odontologo.getNroMatricula().isBlank() || odontologo.getNombre().isBlank() || odontologo.getApellido().isBlank();
  }

}
