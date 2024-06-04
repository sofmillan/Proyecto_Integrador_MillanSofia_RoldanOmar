package dh.backend.demo.service.impl;

import dh.backend.demo.dao.IDao;
import dh.backend.demo.dao.OdontologoDao;
import dh.backend.demo.model.Odontologo;
import dh.backend.demo.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private final OdontologoDao repository;

    public OdontologoService(OdontologoDao repository) {
        this.repository = repository;
    }


    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return repository.registrar(odontologo);
    }

    @Override
    public Odontologo buscarOdontologoPorId(Integer id) {
        return repository.buscarPorId(id);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return repository.buscarTodos();
    }

    @Override
    public void actualizarOdontologo(Odontologo odontologo) {
        repository.actualizar(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        repository.eliminar(id);
    }
}
