package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.EstudanteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class EstudanteController {
    private final EstudanteService estudanteService;

    public EstudanteController(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }

    @PostMapping("/registar/estudante")
    public ResponseEntity<?> criarConta(@Valid @RequestBody EstudanteEntity dadosEstudante) {
        return this.estudanteService.criarConta(dadosEstudante);
    }

    @GetMapping("/login/email={emailEstudante}&senha={senhaEstudante}")
    public ResponseEntity<EstudanteEntity> login(@Valid @RequestBody @PathVariable("emailEstudante") String emailEstudante, @PathVariable("senhaEstudante") String senhaEstudante) {
        return this.estudanteService.login(emailEstudante, senhaEstudante);
    }

    @GetMapping("/perfil/id={idEstudante}")
    public ResponseEntity<EstudanteEntity> verPerfil(@RequestBody @PathVariable("idEstudante") long idEstudante) throws Exception {
        return this.estudanteService.verPerfil(idEstudante);
    }

    @PutMapping("/perfil/id={idEstudante}")
    public ResponseEntity<String> editarPerfil(@PathVariable("idEstudante") long idEstudante, EstudanteEntity dadosEstudante) throws Exception {
        return this.estudanteService.editarPerfil(idEstudante, dadosEstudante);
    }

    @DeleteMapping("/perfil/id={idEstudante}")
    public ResponseEntity<?> apagarConta(@PathVariable("idEstudante") long idEstudante) {
        return this.estudanteService.apagarConta(idEstudante);
    }

    @GetMapping("/listarEstudantes")
    public ResponseEntity<List<EstudanteEntity>> verEstudantes() {
        return this.estudanteService.verEstudantes();
    }
}