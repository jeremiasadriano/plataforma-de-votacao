package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.CursoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CursoController {
    private final CursoService objCursoService;

    public CursoController(CursoService objCursoService) {
        this.objCursoService = objCursoService;
    }

    @PostMapping("/registar")
    public ResponseEntity<CursoEntity> criarCurso_E_Estudante(@RequestBody CursoEntity dadosEstudante) {
        return this.objCursoService.criarCurso_E_Estudante(dadosEstudante);
    }

    @GetMapping("/listarCurso")
    public ResponseEntity<List<CursoEntity>> listarCurso_E_Estudante() {
        return this.objCursoService.listarCurso_E_Estudante();
    }
    @GetMapping("/curso/id={idCurso}")
    public ResponseEntity<List<EstudanteEntity>>listarEstudantesDoCurso(@PathVariable("idCurso") long idCurso ){
        return this.objCursoService.listarEstudantesDoCurso(idCurso);
    }
}
