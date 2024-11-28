package cl.capstone.ms_gestion_alojamiento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_alojamiento.model.Estado;
import cl.capstone.ms_gestion_alojamiento.repository.IEstadoRepository;

@Service
public class EstadoService implements IEstadoService {

    @Autowired
    IEstadoRepository estadoRepository;

    @Override
    public List<Estado> getEstados() {
        List<Estado> estados = estadoRepository.findAll();
        return estados;
    }

    @Override
    public Estado saveEstado(Estado estado) {
        Estado estadoResultado = estadoRepository.save(estado);
        return estadoResultado;
    }

    @Override
    public void deleteEstado(Long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public Estado findEstado(Long id) {
        Estado estado = estadoRepository.findById(id).orElse(null);
        return estado;
    }

    @Override
    public void editEstado(Estado estado) {

        this.saveEstado(estado);
    }

}
