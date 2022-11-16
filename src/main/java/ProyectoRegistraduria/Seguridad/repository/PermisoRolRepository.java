package ProyectoRegistraduria.Seguridad.repository;

import ProyectoRegistraduria.Seguridad.model.PermisoRol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermisoRolRepository extends MongoRepository<PermisoRol, String> {
}
