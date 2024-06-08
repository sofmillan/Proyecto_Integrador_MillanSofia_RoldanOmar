package dh.backend.demo.service.impl;

import dh.backend.demo.entity.Odontologo;
import dh.backend.demo.respository.OdontologoRepository;
import dh.backend.demo.service.IOdontologoService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OdontologoService implements IOdontologoService {
  private final OdontologoRepository repository;

  public OdontologoService(OdontologoRepository repository) {
    this.repository = repository;
  }

  @Override
  public Odontologo registrarOdontologo(Odontologo odontologo) {
    return repository.save(odontologo);
  }

  @Override
  public Optional<Odontologo> buscarOdontologoPorId(Integer id) {
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
}
