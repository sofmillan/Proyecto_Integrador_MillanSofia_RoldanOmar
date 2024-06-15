package dh.backend.demo.service.impl;

import dh.backend.demo.entity.Odontologo;
import dh.backend.demo.entity.Paciente;
import dh.backend.demo.entity.Turno;
import dh.backend.demo.respository.OdontologoRepository;
import dh.backend.demo.respository.PacienteRepository;
import dh.backend.demo.respository.TurnoRepository;
import dh.backend.demo.service.ITurnoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TurnoService implements ITurnoService {
  private TurnoRepository turnoRepository;
  private PacienteRepository pacienteRepository;
  private OdontologoRepository odontologoRepository;

  public TurnoService(
      TurnoRepository turnoRepository,
      PacienteRepository pacienteRepository,
      OdontologoRepository odontologoRepository) {
    this.turnoRepository = turnoRepository;
    this.pacienteRepository = pacienteRepository;
    this.odontologoRepository = odontologoRepository;
  }

  @Override
  public Turno registrar(Turno turno) {
    Optional<Paciente> pacienteBuscado = pacienteRepository.findById(turno.getPaciente().getId());
    Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(turno.getOdontologo().getId());
    Turno turnoARegistrar = new Turno();
    Turno turnoADevolver = null;
    if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
      turnoARegistrar.setOdontologo(odontologoBuscado.get());
      turnoARegistrar.setPaciente(pacienteBuscado.get());
      turnoARegistrar.setFecha(turno.getFecha());
      turnoADevolver = turnoRepository.save(turnoARegistrar);
    }
    return turnoADevolver;
  }

  @Override
  public Optional<Turno> buscarPorId(Integer id) {
    return turnoRepository.findById(id);
  }

  @Override
  public List<Turno> buscarTodos() {
    return turnoRepository.findAll();
  }

  @Override
  public void actualizarTurno(Turno turno) {
    Optional<Paciente> pacienteBuscado = pacienteRepository.findById(turno.getPaciente().getId());
    Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(turno.getOdontologo().getId());
    Turno turnoAModificar = new Turno();
    if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
      turnoAModificar.setId(turno.getId());
      turnoAModificar.setOdontologo(odontologoBuscado.get());
      turnoAModificar.setPaciente(pacienteBuscado.get());
      turnoAModificar.setFecha(turno.getFecha());
      turnoRepository.save(turnoAModificar);
    }
  }

  @Override
  public void eliminarTurno(Integer id) {
    turnoRepository.deleteById(id);
  }

  @Override
  public List<Turno> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate) {
    List<Turno> listadoTurnos = turnoRepository.buscarTurnoEntreFechas(startDate, endDate);
    List<Turno> listadoARetornar = new ArrayList<>();
    for (Turno turno : listadoTurnos) {
      listadoARetornar.add(turno);
    }
    return listadoARetornar;
  }

  @Override
  public List<Turno> buscarTurnoPorPaciente(String nombre) {
    return turnoRepository.findByPacienteNombre(nombre);
  }
}
