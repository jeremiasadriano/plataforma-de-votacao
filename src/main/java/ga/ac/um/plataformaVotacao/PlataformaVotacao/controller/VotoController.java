package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.VotoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.VotoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class VotoController {
    private final VotoService votoService;

    @PostMapping("/estudante/criar-votacao")
    public ResponseEntity<?> criarVotacao(@Valid @RequestBody VotoEntity dadosVotoEntity) {
        return this.votoService.criarVotacao(dadosVotoEntity);
    }

    @PostMapping("/estudante/votar/{idOpcao}/{idEstudante}")
    public ResponseEntity<?> votar(@RequestBody @PathVariable("idOpcao") Long opcoesId, @PathVariable("idEstudante") Long estudanteId) {
        return this.votoService.votar(opcoesId, estudanteId);
    }

    @DeleteMapping("/estudante/remover/{opcoesId}/{estudanteId}")
    public ResponseEntity<?> removerVoto(@PathVariable("opcoesId") Long opcoesId, @PathVariable("estudanteId") Long estudanteId) {
        return this.votoService.removerVoto(opcoesId, estudanteId);
    }

    @GetMapping("/estudante/votar")
    public ResponseEntity<?> listarOpcoesVoto() {
        return this.votoService.listarOpcoesVoto();
    }

    @GetMapping("/estudante/votos/{idp}")
    public ResponseEntity<?> listarNomesVotantes(@RequestBody @PathVariable("idp") Long opcoesId) {
        return this.votoService.listarNomesVotantes(opcoesId);
    }

    @DeleteMapping("/estudante/removerVotacao/{estudanteId}/{votoId}")
    public ResponseEntity<?> removerVotacao(@PathVariable("estudanteId") Long estudanteId, @PathVariable("votoId") Long votoId) {
        return this.votoService.removerVotacao(estudanteId, votoId);
    }
}
