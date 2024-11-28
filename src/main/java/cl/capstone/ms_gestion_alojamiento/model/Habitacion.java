package cl.capstone.ms_gestion_alojamiento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "HABITACION")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HABITACION", nullable = false)
    private Long idHabitacion;

    @Column(name = "NUMERO_HABITACION", nullable = false)
    private Long numeroHabitacion;

    @ManyToOne
    @JoinColumn(name = "ID_HOTEL", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO_HABITACION", nullable = false)
    private Estado estado;

}
