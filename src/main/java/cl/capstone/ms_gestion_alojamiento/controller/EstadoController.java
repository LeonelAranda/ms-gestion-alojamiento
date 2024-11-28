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
import cl.capstone.ms_gestion_alojamiento.model.Estado;
import cl.capstone.ms_gestion_alojamiento.service.IEstadoService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // CORS para todos los endpoints en esta clase
public class EstadoController {

    @Autowired
    private IEstadoService estadoService;

    @GetMapping("/estados/traer")
    public ResponseEntity<Response> getEstados() {

        List<Estado> estados = estadoService.getEstados();
        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        if (estados == null || estados.isEmpty()) {
            response.setCodigoRetorno(-1);
            response.setGlosaRetorno("Estados no encotrados.");
            response.setResultado(estados);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setCodigoRetorno(0);
            response.setGlosaRetorno("Estados encontrados.");
            response.setResultado(estados);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @PostMapping("/estados/crear")
    public ResponseEntity<Response> saveEstado(@RequestBody Estado estado) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        estado.setIdEstado(null);

        Estado estado2 = estadoService.saveEstado(estado);

        if (estado2 != null) {
            // Si existe el trabajador, procedemos a eliminarlo
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Estado creado.");
            response.setResultado(estado2);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si el trabajador no existe, retornamos un mensaje de error
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("No se pudo crear el estado.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/estados/borrar/{id}")
    public ResponseEntity<Response> deleteEstado(@PathVariable Long id) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        Estado estado = estadoService.findEstado(id);

        if (estado != null) {
            // Si existe el trabajador, procedemos a eliminarlo
            estadoService.deleteEstado(id);
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Estado eliminado.");
            response.setResultado(estado);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si el trabajador no existe, retornamos un mensaje de error
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("No se encontró el estado.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/estados/traer/{id}")
    public ResponseEntity<Response> traerEstado(@PathVariable Long id) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        Estado estado = estadoService.findEstado(id);

        if (estado == null) {
            response.setCodigoRetorno(-1);
            response.setGlosaRetorno("Estado no encotrado.");
            response.setResultado(estado);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setCodigoRetorno(0);
            response.setGlosaRetorno("Estado encontrado.");
            response.setResultado(estado);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/estados/editar/{id}")
    public ResponseEntity<Response> editEstado(@RequestBody Estado estado) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        Estado estadoEncontrado = estadoService.findEstado(estado.getIdEstado());

        if (estadoEncontrado != null) {
            // Si existe el trabajador, procedemos a eliminarlo
            estadoService.editEstado(estado);
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Estado modificado.");
            response.setResultado(estadoEncontrado);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si el trabajador no existe, retornamos un mensaje de error
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("No se encontró el Estado.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

}
