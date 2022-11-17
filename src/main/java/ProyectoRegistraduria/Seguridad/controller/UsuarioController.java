package ProyectoRegistraduria.Seguridad.controller;

import ProyectoRegistraduria.Seguridad.model.Usuario;
import ProyectoRegistraduria.Seguridad.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @PostMapping("/validate")
    public Usuario validate(@RequestBody Usuario infoUsuario, final HttpServletResponse response) throws IOException {
        String email = infoUsuario.getEmail();
        String contrasena = infoUsuario.getContrasena();
        contrasena = tecnicaHash(contrasena);

        Optional<Usuario> opt = this.usuarioRepo.findByEmailAndContrasena(email, contrasena);

        if (opt.isPresent()) {
            Usuario u = opt.get();
            u.setContrasena("");
            return u;
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }



    @GetMapping("")
    public List<Usuario> index() {
        return this.usuarioRepo.findAll();
    }

    @GetMapping("/{id}")
    public Usuario show(@PathVariable String id) {
        Optional<Usuario> opt = this.usuarioRepo.findById(id);
        return opt.orElse(null);
    }

    @PostMapping("")
    public Usuario create(@RequestBody Usuario infoUsuario){
        String nuevaContrasena = tecnicaHash(infoUsuario.getContrasena());
        infoUsuario.setContrasena(nuevaContrasena);
        return this.usuarioRepo.save(infoUsuario);
    }

    @PutMapping("/{id})")
    public Usuario update(@PathVariable String id, @RequestBody Usuario infoUsuario) {
        Optional<Usuario> opt = this.usuarioRepo.findById(id);
        if(opt.isPresent())
        {
            Usuario actual = opt.get();

            if(infoUsuario.getContrasena() != null && !infoUsuario.getContrasena().isBlank())
                actual.setContrasena(infoUsuario.getContrasena());
            if(infoUsuario.getNombre_usuario() != null && !infoUsuario.getNombre_usuario().isBlank())
                actual.setNombre_usuario(infoUsuario.getNombre_usuario());
            if(infoUsuario.getEmail() != null && !infoUsuario.getEmail().isBlank())
                actual.setEmail(infoUsuario.getEmail());
            if(infoUsuario.getRol() != null)
                actual.setRol(infoUsuario.getRol());

            return this.usuarioRepo.save(actual);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {

        Optional<Usuario> opt = this.usuarioRepo.findById(id);
        if(opt.isPresent())
        {
            this.usuarioRepo.deleteById(id);
        }

    }

    public String tecnicaHash(String contrasena) {

        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] hash = md.digest(contrasena.getBytes());
        StringBuilder sb = new StringBuilder();

        for(byte b : hash) {
            sb.append( String.format("%02x", b) );
        }
        return sb.toString();

    }


}
