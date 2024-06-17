package dh.backend.demo.respository;

import dh.backend.demo.entity.Paciente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    List<Paciente> findByDni(String dni);

    List<Paciente> findByDomicilioProvincia(String provincia);
}