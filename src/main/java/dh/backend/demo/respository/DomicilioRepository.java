package dh.backend.demo.respository;

import dh.backend.demo.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {}
