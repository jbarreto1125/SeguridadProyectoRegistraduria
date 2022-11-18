package ProyectoRegistraduria.Seguridad.repository;

import ProyectoRegistraduria.Seguridad.model.Permiso;
import ProyectoRegistraduria.Seguridad.model.PermisoRol;
import ProyectoRegistraduria.Seguridad.model.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PermisoRolRepository extends MongoRepository<PermisoRol, String>{

    Optional<PermisoRol> findByRolAndPermiso(Rol rol, Permiso permiso);

}

