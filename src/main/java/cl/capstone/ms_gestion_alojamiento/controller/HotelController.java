package cl.capstone.ms_gestion_alojamiento.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.capstone.ms_gestion_alojamiento.dto.Response;
import cl.capstone.ms_gestion_alojamiento.model.Hotel;
import cl.capstone.ms_gestion_alojamiento.service.IHotelService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // CORS para todos los endpoints en esta clase
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @GetMapping("/hotel/traer")
    public ResponseEntity<Response> getHoteles() {

        List<Hotel> hoteles = hotelService.getHoteles();
        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        if (hoteles == null || hoteles.isEmpty()) {
            response.setCodigoRetorno(-1);
            response.setGlosaRetorno("hoteles no encotrados.");
            response.setResultado(hoteles);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setCodigoRetorno(0);
            response.setGlosaRetorno("hoteles encontrados.");
            response.setResultado(hoteles);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @PostMapping("/hotel/crear")
    public ResponseEntity<Response> saveHotel(@RequestBody Hotel hotel) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();
        hotel.setIdHotel(null);

        Hotel hotel2 = hotelService.saveHotel(hotel);

        if (hotel2 != null) {
            // Si existe el trabajador, procedemos a eliminarlo
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Hotel creado.");
            response.setResultado(hotel2);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si el trabajador no existe, retornamos un mensaje de error
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("No se pudo crear el hotel.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/hotel/borrar/{id}")
    public ResponseEntity<Response> deleteHotel(@PathVariable Long id) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        Hotel hotel = hotelService.findHotel(id);

        if (hotel != null) {
            // Si existe el trabajador, procedemos a eliminarlo
            hotelService.deleteHotel(id);
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Hotel eliminado.");
            response.setResultado(hotel);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si el trabajador no existe, retornamos un mensaje de error
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("No se encontró el hotel.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/hotel/traer/{id}")
    public ResponseEntity<Response> traerHotel(@PathVariable Long id) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        Hotel hotel = hotelService.findHotel(id);

        if (hotel == null) {
            response.setCodigoRetorno(-1);
            response.setGlosaRetorno("Hotel no encotrad.");
            response.setResultado(hotel);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setCodigoRetorno(0);
            response.setGlosaRetorno("Hotel encontrad.");
            response.setResultado(hotel);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/hotel/editar/{id}")
    public ResponseEntity<Response> editHotel(@RequestBody Hotel hotel) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        Hotel hotel2 = hotelService.findHotel(hotel.getIdHotel());

        if (hotel2 != null) {
            // Si existe el trabajador, procedemos a eliminarlo
            hotelService.editHotel(hotel2);
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Hotel modificado.");
            response.setResultado(hotel2);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si el trabajador no existe, retornamos un mensaje de error
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("No se encontró el hotel.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

}
