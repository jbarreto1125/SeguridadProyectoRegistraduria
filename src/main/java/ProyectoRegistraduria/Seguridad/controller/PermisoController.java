package ProyectoRegistraduria.Seguridad.controller;

import ProyectoRegistraduria.Seguridad.model.Permiso;
import ProyectoRegistraduria.Seguridad.repository.PermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permisos")
public class PermisoController {

    @Autowired
    private PermisoRepository permisoRepo;

    @GetMapping("")
    public List<Permiso> index() {
        return this.permisoRepo.findAll();
    }

    @GetMapping("{id}")
    public Permiso show(@PathVariable String id) {
        Optional<Permiso> optPermiso = this.permisoRepo.findById(id);
        return optPermiso.orElse(null);
    }

    @PostMapping("")
    public Permiso create(@RequestBody Permiso p) {
        return this.permisoRepo.save(p);
    }

    @PutMapping("{id}")
    public Permiso update(@PathVariable String id, @RequestBody Permiso p) {
        Optional<Permiso> optPermiso = this.permisoRepo.findById(id);
        if(optPermiso.isPresent())
        {
            Permiso actual = optPermiso.get();
            actual.setUrl(p.getUrl());
            actual.setMetodo(p.getMetodo());
            return this.permisoRepo.save(actual);
        }
        return null;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id)
    {
        this.permisoRepo.deleteById(id);
    }
}
