package dh.backend.demo.service.impl;

import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.response.OdontologoResponseDto;
import dh.backend.demo.entity.Odontologo;
import dh.backend.demo.exception.BadRequestException;
import dh.backend.demo.exception.ResourceNotFoundException;
import dh.backend.demo.respository.OdontologoRepository;
import dh.backend.demo.service.IOdontologoService;
import java.util.List;
import java.util.Optional;

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
    if(!validarOdontologo(odontologo)){
/*
      LOGGER.error("Error al guardar odontólogo - Información de odontólogo inválida");
*/
      throw new BadRequestException("Información de odontólogo inválida");
    }
    Odontologo odontologoGuardado = repository.save(mapRequestToModel(odontologo));
    LOGGER.info("Odontólogo guardado exitosamente "+odontologoGuardado);

    return mapModelToResponse(odontologoGuardado);
  }

  @Override
  public OdontologoResponseDto buscarOdontologoPorId(Integer id) {
    Odontologo odontologoEncontrado = repository.findById(id).orElseThrow(
            ()-> new ResourceNotFoundException("Odontologo no encontrado"));
    return mapModelToResponse(odontologoEncontrado);
  }

  @Override
  public List<Odontologo> buscarTodos() {
    return repository.findAll();
  }

  @Override
  public void actualizarOdontologo(Odontologo odontologo) {
    repository.save(odontologo);
  }

  @Override
  public void eliminarOdontologo(Integer id) {
    repository.deleteById(id);
  }

  private Odontologo mapRequestToModel(OdontologoRequestDto odontologo){
    return mapper.map(odontologo, Odontologo.class);
  }
  private OdontologoResponseDto mapModelToResponse(Odontologo odontologo){
    return mapper.map(odontologo, OdontologoResponseDto.class);
  }

  private boolean validarOdontologo(OdontologoRequestDto odontologoRequestDto){
    if(odontologoRequestDto.getNombre() == null
            || odontologoRequestDto.getApellido() == null
            || odontologoRequestDto.getNroMatricula() == null){
      return false;
    }

    if(odontologoRequestDto.getNombre().isBlank()
            || odontologoRequestDto.getApellido().isBlank()
            || odontologoRequestDto.getNroMatricula().isBlank()){
      return false;
    }
    return true;
  }


}
