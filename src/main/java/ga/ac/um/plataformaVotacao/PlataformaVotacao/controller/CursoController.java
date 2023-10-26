package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.CursoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos/")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping("/registar")
    public ResponseEntity<CursoEntity> criarCurso_E_Estudante(@Valid @RequestBody CursoEntity dadosEstudante) {
        return this.cursoService.criarCurso_E_Estudante(dadosEstudante);
    }

    @GetMapping("/listarCurso")
    public ResponseEntity<List<CursoEntity>> listarCurso_E_Estudante() {
        return this.cursoService.listarCurso_E_Estudante();
    }

    @GetMapping("/curso/id={idCurso}")
    public ResponseEntity<List<EstudanteEntity>> listarEstudantesDoCurso(@PathVariable("idCurso") long idCurso) {
        return this.cursoService.listarEstudantesDoCurso(idCurso);
    }
}
