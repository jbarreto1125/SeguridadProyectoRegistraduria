package ProyectoRegistraduria.Seguridad.repository;

import ProyectoRegistraduria.Seguridad.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmailAndContrasena(String email, String contrasena);

}
