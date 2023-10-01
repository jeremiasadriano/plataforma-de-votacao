package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.AdminEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService objAdminService;

    public AdminController(AdminService objAdminService) {
        this.objAdminService = objAdminService;
    }

    @PostMapping("/registarAdmin")
    public ResponseEntity<AdminEntity> criarConta(@RequestBody AdminEntity dadosAdmin) {
        return this.objAdminService.criarConta(dadosAdmin);
    }

    @GetMapping("/login/emailAdmin={emailEntity}&senha={senhaEntity}")
    public ResponseEntity<String> fazerLogin(@PathVariable("emailEntity") String emailEntity, @PathVariable("senhaEntity") String senhaEntity) {
        return this.objAdminService.fazerLogin(emailEntity, senhaEntity);
    }

    @GetMapping("/perfil/adminID={id}")
    public ResponseEntity<AdminEntity> verPerfil(@RequestBody @PathVariable("id") long id) throws Exception {
        return this.objAdminService.verPerfil(id);
    }

    @PutMapping("/perfil/adminID={id}")
    public ResponseEntity<AdminEntity> editarPerfil(@RequestBody AdminEntity dadosAdmin, @PathVariable("id") long id) throws Exception {
        return this.objAdminService.editarPerfil(dadosAdmin, id);
    }

    @DeleteMapping("/perfil/adminID={id}")
    public ResponseEntity<?> apagarConta(@PathVariable("id") long id) {
        return this.objAdminService.apagarConta(id);
    }

    @GetMapping("/perfil/admins")
    public ResponseEntity<List<AdminEntity>> listarAdmin() {
        return this.objAdminService.listarAdmin();
    }
}
