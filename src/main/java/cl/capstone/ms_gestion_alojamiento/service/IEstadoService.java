package cl.capstone.ms_gestion_alojamiento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_alojamiento.model.Estado;

@Service
public interface IEstadoService {

    public List<Estado> getEstados();

    public Estado saveEstado(Estado estado);

    public void deleteEstado(Long id);

    public Estado findEstado(Long id);

    public void editEstado(Estado estado);

}
