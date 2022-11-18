package ProyectoRegistraduria.Seguridad.controller;

import ProyectoRegistraduria.Seguridad.model.Permiso;
import ProyectoRegistraduria.Seguridad.model.PermisoRol;
import ProyectoRegistraduria.Seguridad.model.Rol;
import ProyectoRegistraduria.Seguridad.repository.PermisoRepository;
import ProyectoRegistraduria.Seguridad.repository.PermisoRolRepository;
import ProyectoRegistraduria.Seguridad.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permiso_rol")
public class PermisoRolController {
    @Autowired
    private PermisoRolRepository permisoRolRepo;

    @Autowired
    private PermisoRepository permisoRepo;

    @Autowired
    private RolRepository rolRepo;

    @GetMapping("")
    public List<PermisoRol> index() {

        return this.permisoRolRepo.findAll();
    }

    @GetMapping("{id}")
    public PermisoRol show(@PathVariable String id) {
        Optional<PermisoRol> optPermisoRol = this.permisoRolRepo.findById(id);
        return optPermisoRol.orElse(null);

    }

    @PostMapping("")
    public PermisoRol create(@RequestBody PermisoRol p) {

        Optional<Permiso> optPermiso = this.permisoRepo.findById(p.getPermiso().get_id());
        if(optPermiso.isEmpty())
        {
            return null;
        }

        Optional<Rol> optRol = this.rolRepo.findById(p.getRol().get_id());

        if(optRol.isEmpty())
        {
            return null;
        }
        return this.permisoRolRepo.save(p);
    }

    @PutMapping("{id}")
    public PermisoRol update(@PathVariable String id, @RequestBody PermisoRol p){
        Optional<PermisoRol> optPermisosRol = this.permisoRolRepo.findById(id);
        if(optPermisosRol.isPresent()){
            PermisoRol actual = optPermisosRol.get();

            return this.permisoRolRepo.save(p);
        }
        return null;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){

        this.permisoRolRepo.deleteById(id);
    }

    @PostMapping("/validar-permiso/rol/{id_rol}")
    public PermisoRol getPermiso(@PathVariable String id_rol, @RequestBody Permiso infoPermiso,
                                 final HttpServletResponse response) throws IOException {
        Optional<Permiso> opt = this.permisoRepo.findByUrlAndMetodo(infoPermiso.getUrl(), infoPermiso.getMetodo());
        if(opt.isEmpty())
        {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;

        }
        Permiso p = opt.get();

        Optional<Rol> optRol = this.rolRepo.findById(id_rol);
        if(optRol.isEmpty())
        {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        Rol r = optRol.get();

        Optional<PermisoRol> optPermisoRol = this.permisoRolRepo.findByRolAndPermiso(r, p);

        if(optPermisoRol.isEmpty())
        {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        return optPermisoRol.get();

    }


    }



