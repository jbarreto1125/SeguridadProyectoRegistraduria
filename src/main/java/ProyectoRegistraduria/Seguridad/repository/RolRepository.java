package ProyectoRegistraduria.Seguridad.repository;

import ProyectoRegistraduria.Seguridad.model.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RolRepository extends MongoRepository<Rol, String> {
}
