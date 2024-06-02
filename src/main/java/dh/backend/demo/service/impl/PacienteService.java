package dh.backend.demo.service.impl;

import dh.backend.demo.dao.IDao;
import dh.backend.demo.model.Paciente;
import dh.backend.demo.service.IPacienteService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteService {

  private IDao<Paciente> pacienteDao;

  public PacienteService(IDao<Paciente> pacienteDao) {
    this.pacienteDao = pacienteDao;
  }

  @Override
  public Paciente registrarPaciente(Paciente paciente) {
    return pacienteDao.registrar(paciente);
  }

  @Override
  public Paciente buscarPorId(Integer id) {
    return pacienteDao.buscarPorId(id);
  }

  @Override
  public List<Paciente> buscarTodos() {
    return pacienteDao.buscarTodos();
  }

  @Override
  public void actualizarPaciente(Paciente paciente) {
    pacienteDao.actualizar(paciente);
  }

  @Override
  public void eliminarPaciente(Integer id) {
    pacienteDao.eliminar(id);
  }
}
