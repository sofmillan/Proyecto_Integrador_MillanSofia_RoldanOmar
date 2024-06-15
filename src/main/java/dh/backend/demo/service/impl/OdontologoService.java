package dh.backend.demo.service.impl;

import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.request.OdontologoUpdateDto;
import dh.backend.demo.dto.response.OdontologoResponseDto;
import dh.backend.demo.entity.Odontologo;
import dh.backend.demo.exception.BadRequestException;
import dh.backend.demo.exception.ResourceNotFoundException;
import dh.backend.demo.respository.OdontologoRepository;
import dh.backend.demo.service.IOdontologoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OdontologoService implements IOdontologoService {
  private final OdontologoRepository repository;
  private final ModelMapper mapper;
  private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

  public OdontologoService(OdontologoRepository repository, ModelMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public OdontologoResponseDto registrarOdontologo(OdontologoRequestDto odontologo) {
    if(odontologo.getNombre() == null || odontologo.getNroMatricula() == null || odontologo.getApellido() == null){
      LOGGER.error("Error al guardar odontólogo - Información de odontólogo inválida");
      throw new BadRequestException("Información de odontólogo inválida");
    }

    Odontologo odontologoGuardado = repository.save(mapRequestToModel(odontologo));
    LOGGER.info("Odontólogo guardado exitosamente "+odontologoGuardado);

    return mapModelToResponse(odontologoGuardado);
  }

  @Override
  public OdontologoResponseDto buscarOdontologoPorId(Integer id) {
    Odontologo odontologoEncontrado = repository.findById(id).orElseThrow(
            ()-> {
              LOGGER.error("Error al buscar odontólogo - Odontologo con id "+id+" no encantrado");
              throw new ResourceNotFoundException("Odontólogo con id "+id+" no encontrado");
            });

    return mapModelToResponse(odontologoEncontrado);
  }

  @Override
  public List<OdontologoResponseDto> buscarTodos() {
    List<OdontologoResponseDto> odontologosEncontrados = repository.findAll()
            .stream()
            .map(this::mapModelToResponse)
            .collect(Collectors.toList());
    LOGGER.info("Odontólogos fueron encontrados");
    return odontologosEncontrados;
  }

  @Override
  public void actualizarOdontologo(OdontologoUpdateDto odontologo) {
    if(odontologo.getId() == null){
      LOGGER.error("Error al actualizar odontólogo - Información de odontólogo inválida");
      throw new BadRequestException("Debe proveer id para actualizar odontólogo");
    }
    repository.save(mapupdateToModel(odontologo));
    LOGGER.info("Odontólogo con id:"+odontologo.getId()+" actualizado");
  }

  @Override
  public void eliminarOdontologo(Integer id) {
    repository.deleteById(id);
    LOGGER.info("Odontólogo con id:"+id+" eliminado");
  }

  private Odontologo mapRequestToModel(OdontologoRequestDto odontologo){
    return mapper.map(odontologo, Odontologo.class);
  }
  private OdontologoResponseDto mapModelToResponse(Odontologo odontologo){
    return mapper.map(odontologo, OdontologoResponseDto.class);
  }

  private Odontologo mapupdateToModel(OdontologoUpdateDto odontologo){
    return mapper.map(odontologo, Odontologo.class);
  }

}
