package cl.capstone.ms_gestion_alojamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.capstone.ms_gestion_alojamiento.model.Estado;

@Repository
public interface IEstadoRepository extends JpaRepository<Estado, Long> {

}
