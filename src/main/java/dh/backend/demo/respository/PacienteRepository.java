package dh.backend.demo.respository;

import dh.backend.demo.entity.Paciente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Optional<Paciente> findTopByDni(String dni);

    List<Paciente> findByDomicilioProvincia(String provincia);
}