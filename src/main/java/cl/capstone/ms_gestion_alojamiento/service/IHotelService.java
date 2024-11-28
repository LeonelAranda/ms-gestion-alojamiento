package cl.capstone.ms_gestion_alojamiento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_alojamiento.model.Hotel;

@Service
public interface IHotelService {

    public List<Hotel> getHoteles();

    public Hotel saveHotel(Hotel hotel);

    public void deleteHotel(Long id);

    public Hotel findHotel(Long id);

    public void editHotel(Hotel hotel);
}
