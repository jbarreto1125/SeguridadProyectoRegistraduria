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
    private String correo;
    private String contrasena;

    @DBRef
    private Rol rol;

    public Usuario(String nombre_usuario, String correo, String contrasena)
    {
        this.nombre_usuario = nombre_usuario;
        this.correo = correo;
        this.contrasena = contrasena;
    }
}
