package ProyectoRegistraduria.Seguridad.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Rol {

    @Id
    private String _id;
    private String nombre;


    public Rol(String nombre) {
        this.nombre = nombre;

    }
    public Rol(){

    }
}
