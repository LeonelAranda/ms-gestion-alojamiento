package cl.capstone.ms_gestion_alojamiento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_alojamiento.model.Habitacion;

@Service
public interface IHabitacionService {

    public List<Habitacion> getHabitaciones();

    public Habitacion saveHabitacion(Habitacion habitacion);

    public void deleteHabitacion(Long id);

    public Habitacion findHabitacion(Long id);

    public void editHabitacion(Habitacion habitacion);

    public List<Habitacion> findByHotel_IdHotel(Long idHotel);

}
