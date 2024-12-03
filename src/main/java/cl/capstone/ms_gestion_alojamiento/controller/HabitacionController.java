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
import cl.capstone.ms_gestion_alojamiento.model.Habitacion;
import cl.capstone.ms_gestion_alojamiento.service.IHabitacionService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // CORS para todos los endpoints en esta clase
public class HabitacionController {
    @Autowired
    private IHabitacionService habitacionService;

    @GetMapping("/habitacion/traer")
    public ResponseEntity<Response> getHabitacion() {

        List<Habitacion> habitacions = habitacionService.getHabitaciones();
        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        if (habitacions == null || habitacions.isEmpty()) {
            response.setCodigoRetorno(-1);
            response.setGlosaRetorno("Habitaciones no encotradas.");
            response.setResultado(habitacions);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setCodigoRetorno(0);
            response.setGlosaRetorno("Habitaciones encontradas.");
            response.setResultado(habitacions);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @PostMapping("/habitacion/crear")
    public ResponseEntity<Response> saveHabitacion(@RequestBody Habitacion habitacion) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        habitacion.setIdHabitacion(null);

        Habitacion habitacion2 = habitacionService.saveHabitacion(habitacion);

        if (habitacion2 != null) {
            // Si existe el trabajador, procedemos a eliminarlo
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Habitación creada.");
            response.setResultado(habitacion2);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si el trabajador no existe, retornamos un mensaje de error
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("No se pudo crear la habitación.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/habitacion/borrar/{id}")
    public ResponseEntity<Response> deleteHabitacion(@PathVariable Long id) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        Habitacion habitacion = habitacionService.findHabitacion(id);

        if (habitacion != null) {
            // Si existe el trabajador, procedemos a eliminarlo
            habitacionService.deleteHabitacion(id);
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Habitación eliminada.");
            response.setResultado(habitacion);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si el trabajador no existe, retornamos un mensaje de error
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("No se encontró la habitación.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/habitacion/traer/{id}")
    public ResponseEntity<Response> traerHabitacion(@PathVariable Long id) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        Habitacion habitacion = habitacionService.findHabitacion(id);

        if (habitacion == null) {
            response.setCodigoRetorno(-1);
            response.setGlosaRetorno("Habitación no encotrada.");
            response.setResultado(habitacion);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setCodigoRetorno(0);
            response.setGlosaRetorno("Habitacion encontrada.");
            response.setResultado(habitacion);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/habitacion/editar/{id}")
    public ResponseEntity<Response> editHabitacion(@RequestBody Habitacion habitacion) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        Habitacion habitacion2 = habitacionService.findHabitacion(habitacion.getIdHabitacion());

        if (habitacion2 != null) {
            // Si existe el trabajador, procedemos a eliminarlo
            habitacionService.editHabitacion(habitacion2);
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Habitación modificada.");
            response.setResultado(habitacion2);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si el trabajador no existe, retornamos un mensaje de error
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("No se encontró la habitación.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/habitacion/traerporhotel/{idHotel}")
    public ResponseEntity<Response> traerHabitacionPorHotel(@PathVariable Long idHotel) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        List<Habitacion> habitacion = habitacionService.findByHotel_IdHotel(idHotel);

        if (habitacion == null) {
            response.setCodigoRetorno(-1);
            response.setGlosaRetorno("Habitación no encotrada.");
            response.setResultado(habitacion);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setCodigoRetorno(0);
            response.setGlosaRetorno("Habitacion encontrada.");
            response.setResultado(habitacion);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
