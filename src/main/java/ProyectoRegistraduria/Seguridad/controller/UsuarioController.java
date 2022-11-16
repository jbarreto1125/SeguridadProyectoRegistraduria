package ProyectoRegistraduria.Seguridad.controller;

import ProyectoRegistraduria.Seguridad.model.Usuario;
import ProyectoRegistraduria.Seguridad.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepo;

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
        return this.usuarioRepo.save(infoUsuario);
    }


}
