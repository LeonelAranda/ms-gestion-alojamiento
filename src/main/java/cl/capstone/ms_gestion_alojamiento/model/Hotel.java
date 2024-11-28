package cl.capstone.ms_gestion_alojamiento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "HOTEL")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HOTEL", nullable = false)
    private Long idHotel;

    @Column(name = "NOMBRE_HOTEL", nullable = false)
    private String nombreHotel;

    @Column(name = "CIUDAD_HOTEL", nullable = false)
    private String CiudadHotel;

    @Column(name = "CALLE", nullable = false)
    private String calle;

    @Column(name = "TELEFONO", nullable = false)
    private String telefono;

}
