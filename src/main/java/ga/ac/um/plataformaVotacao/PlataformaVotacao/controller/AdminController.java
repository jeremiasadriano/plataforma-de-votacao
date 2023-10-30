package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.AdminEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.AdminService;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.EstudanteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final EstudanteService estudanteService;

    @PostMapping("/registarAdmin")
    public ResponseEntity<?> criarConta(@Valid @RequestBody AdminEntity dadosAdmin) {
        return this.adminService.criarConta(dadosAdmin);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        return this.adminService.fazerLogin(email, senha);
    }

    @GetMapping("/perfil/adminID={id}")
    public ResponseEntity<AdminEntity> verPerfil(@RequestBody @PathVariable("id") long id) throws Exception {
        return this.adminService.verPerfil(id);
    }

    @PutMapping("/perfil/adminID={id}")
    public ResponseEntity<AdminEntity> editarPerfil(@Valid @RequestBody AdminEntity dadosAdmin, @PathVariable("id") long id) throws Exception {
        return this.adminService.editarPerfil(dadosAdmin, id);
    }

    @DeleteMapping("/perfil/adminID={id}")
    public ResponseEntity<?> apagarConta(@PathVariable("id") long id) {
        return this.adminService.apagarConta(id);
    }

    @GetMapping("/perfil/admins")
    public ResponseEntity<List<AdminEntity>> listarAdmin() {
        return this.adminService.listarAdmin();
    }

    @GetMapping("/listarEstudantes")
    public ResponseEntity<List<EstudanteEntity>> verEstudantes() {
        return this.estudanteService.verEstudantes();
    }
}
