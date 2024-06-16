package dh.backend.demo.service.impl;

import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.response.OdontologoResponseDto;
import dh.backend.demo.entity.Odontologo;
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
    Odontologo odontologoGuardado = repository.save(mapRequestToModel(odontologo));
    return mapModelToResponse(odontologoGuardado);
  }

  @Override
  public Optional<Odontologo> buscarOdontologoPorId(Integer id) {
    Odontologo odontologoEncontrado = repository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Odontologo no encontrado"));
    return repository.findById(id);
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

  public Odontologo mapRequestToModel(OdontologoRequestDto odontologo) {
    return mapper.map(odontologo, Odontologo.class);
  }

  public OdontologoResponseDto mapModelToResponse(Odontologo odontologo) {
    return mapper.map(odontologo, OdontologoResponseDto.class);
  }

  @Override
  public List<Odontologo> buscarPorNombre(String nombre) {
    return repository.findByNombre(nombre);
  }

  @Override
  public List<Odontologo> buscarPorMatricula(String matricula) {
    return repository.findByNroMatricula(matricula);
  }

}
