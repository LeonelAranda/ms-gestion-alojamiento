package cl.capstone.ms_gestion_alojamiento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_alojamiento.model.Habitacion;
import cl.capstone.ms_gestion_alojamiento.repository.IHabitacionRepository;

@Service
public class HabitacionService implements IHabitacionService {

    @Autowired
    IHabitacionRepository habitacionRepository;

    @Override
    public List<Habitacion> getHabitaciones() {

        List<Habitacion> listaHabitaciones = habitacionRepository.findAll();
        return listaHabitaciones;
    }

    @Override
    public Habitacion saveHabitacion(Habitacion habitacion) {
        habitacionRepository.save(habitacion);
        return habitacion;
    }

    @Override
    public void deleteHabitacion(Long id) {

        habitacionRepository.deleteById(id);

    }

    @Override
    public Habitacion findHabitacion(Long id) {
        Habitacion habitacion = habitacionRepository.findById(id).orElse(null);
        return habitacion;
    }

    @Override
    public void editHabitacion(Habitacion habitacion) {

        this.habitacionRepository.save(habitacion);

    }

}
