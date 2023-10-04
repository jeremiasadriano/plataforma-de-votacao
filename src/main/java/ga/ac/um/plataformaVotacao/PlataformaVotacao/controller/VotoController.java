package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.VotoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.VotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class VotoController {

    private VotoService objVotoService;

    public VotoController(VotoService objVotoService) {
        this.objVotoService = objVotoService;
    }

    @PostMapping("/estudante/criar-votacao")
    public ResponseEntity<?> criarVotacao(@RequestBody VotoEntity dadosVotoEntity) {
        return this.objVotoService.criarVotacao(dadosVotoEntity);
    }

    @PostMapping("/estudante/votar/{idp}/{ide}")
    public ResponseEntity<?> votar(@RequestBody @PathVariable("idp") Long idOpcoes, @PathVariable("ide") Long idEstudante) {
        return this.objVotoService.votar(idOpcoes, idEstudante);
    }

    @GetMapping("/estudante/votar")
    public ResponseEntity<?> listarOpcoesVoto() {
        return this.objVotoService.listarOpcoesVoto();
    }

    @GetMapping("/estudante/votos/{idOpcao}")
    public ResponseEntity<?> contarVotos(@PathVariable("idOpcao") Long idOpcao) {
        return this.objVotoService.contarVotos(idOpcao);
    }
}
