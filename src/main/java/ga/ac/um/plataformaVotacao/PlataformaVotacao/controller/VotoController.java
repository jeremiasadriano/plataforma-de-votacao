package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.VotoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.VotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping("/estudante/criar-votacao")
    public ResponseEntity<?> criarVotacao(@RequestBody VotoEntity dadosVotoEntity) {
        return this.votoService.criarVotacao(dadosVotoEntity);
    }

    @PostMapping("/estudante/votar/{idOpcao}/{idEstudante}")
    public ResponseEntity<?> votar(@RequestBody @PathVariable("idOpcao") Long opcoesId, @PathVariable("idEstudante") Long estudanteId) {
        return this.votoService.votar(opcoesId, estudanteId);
    }

    @DeleteMapping("/estudante/removerVoto/{idp}/{ide}")
    public ResponseEntity<?> removerVoto(@RequestBody @PathVariable("idp") Long opcoesId, @PathVariable("ide") Long estudanteId) {
        return this.votoService.removerVoto(opcoesId, estudanteId);
    }

    @GetMapping("/estudante/votar")
    public ResponseEntity<?> listarOpcoesVoto() {
        return this.votoService.listarOpcoesVoto();
    }

    @GetMapping("/estudante/votos/{idOpcao}")
    public ResponseEntity<?> contarVotos(@PathVariable("idOpcao") Long idOpcao) {
        return this.votoService.contarVotos(idOpcao);
    }
}
