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
    private final EstudanteService objEstudanteService;

    public EstudanteController(EstudanteService objEstudanteService) {
        this.objEstudanteService = objEstudanteService;
    }

    @PostMapping("/registar/estudante")
    public ResponseEntity<?> criarConta(@Valid @RequestBody EstudanteEntity dadosEstudante) {
        return this.objEstudanteService.criarConta(dadosEstudante);
    }

    @GetMapping("/login/email={emailEstudante}&senha={senhaEstudante}")
    public ResponseEntity<EstudanteEntity> login(@Valid @RequestBody @PathVariable("emailEstudante") String emailEstudante, @PathVariable("senhaEstudante") String senhaEstudante) {
        return this.objEstudanteService.login(emailEstudante, senhaEstudante);
    }

    @GetMapping("/perfil/id={idEstudante}")
    public ResponseEntity<EstudanteEntity> verPerfil(@RequestBody @PathVariable("idEstudante") long idEstudante) throws Exception {
        return this.objEstudanteService.verPerfil(idEstudante);
    }

    @PutMapping("/perfil/id={idEstudante}")
    public ResponseEntity<String> editarPerfil(@PathVariable("idEstudante") long idEstudante, EstudanteEntity dadosEstudante) throws Exception {
        return this.objEstudanteService.editarPerfil(idEstudante, dadosEstudante);
    }

    @DeleteMapping("/perfil/id={idEstudante}")
    public ResponseEntity<?> apagarConta(@PathVariable("idEstudante") long idEstudante) {
        return this.objEstudanteService.apagarConta(idEstudante);
    }

    @GetMapping("/listarEstudantes")
    public ResponseEntity<List<EstudanteEntity>> verEstudantes() {
        return this.objEstudanteService.verEstudantes();
    }
}