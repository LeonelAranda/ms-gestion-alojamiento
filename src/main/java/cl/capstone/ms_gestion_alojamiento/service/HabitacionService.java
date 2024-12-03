package cl.capstone.ms_gestion_alojamiento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_alojamiento.model.Estado;
import cl.capstone.ms_gestion_alojamiento.model.Habitacion;
import cl.capstone.ms_gestion_alojamiento.repository.IEstadoRepository;
import cl.capstone.ms_gestion_alojamiento.repository.IHabitacionRepository;

@Service
public class HabitacionService implements IHabitacionService {

    @Autowired
    IHabitacionRepository habitacionRepository;
    @Autowired
    IEstadoRepository estadoRepository;

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

    @Override
    public List<Habitacion> findByHotel_IdHotel(Long idHotel) {
        List<Habitacion> habitacion = this.habitacionRepository.findByHotel_IdHotel(idHotel);
        return habitacion;
    }

    @Override
    public Habitacion updateEstadoHabitacion(Long idHabitacion, Long idEstado) {
        // Buscar la habitación por ID
        Habitacion habitacion = habitacionRepository.findById(idHabitacion)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        // Buscar el estado por ID
        Estado estado = estadoRepository.findById(idEstado)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        // Actualizar el estado de la habitación
        habitacion.setEstado(estado);

        // Guardar los cambios
        return habitacionRepository.save(habitacion);
    }

}
