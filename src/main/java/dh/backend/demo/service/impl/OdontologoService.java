package dh.backend.demo.service.impl;

import dh.backend.demo.dao.IDao;
import dh.backend.demo.model.Odontologo;
import dh.backend.demo.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IService<Odontologo> {
    private final IDao<Odontologo> repository;

    public OdontologoService(IDao<Odontologo> repository) {
        this.repository = repository;
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        return repository.registrar(odontologo);
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return repository.buscarPorId(id);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return repository.buscarTodos();
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        repository.actualizar(odontologo);
    }

    @Override
    public void eliminar(Integer id) {
        repository.eliminar(id);
    }
}
