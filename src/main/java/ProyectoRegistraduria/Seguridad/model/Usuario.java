package ProyectoRegistraduria.Seguridad.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Usuario {

    @Id
    private String _id;

    private String nombre_usuario;
    private String email;
    private String contrasena;

    @DBRef
    private Rol rol;

    public Usuario(String nombre_usuario, String email, String contrasena)
    {
        this.nombre_usuario = nombre_usuario;
        this.email = email;
        this.contrasena = contrasena;
    }
}
