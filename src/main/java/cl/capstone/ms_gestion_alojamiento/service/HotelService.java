package cl.capstone.ms_gestion_alojamiento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_alojamiento.model.Hotel;
import cl.capstone.ms_gestion_alojamiento.repository.IHotelRepository;

@Service
public class HotelService implements IHotelService {

    @Autowired
    IHotelRepository hotelRepository;

    @Override
    public List<Hotel> getHoteles() {

        List<Hotel> listaHoteles = hotelRepository.findAll();
        return listaHoteles;
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        hotelRepository.save(hotel);
        return hotel;
    }

    @Override
    public void deleteHotel(Long id) {

        hotelRepository.deleteById(id);
    }

    @Override
    public Hotel findHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        return hotel;
    }

    @Override
    public void editHotel(Hotel hotel) {

        this.hotelRepository.save(hotel);
    }

}
