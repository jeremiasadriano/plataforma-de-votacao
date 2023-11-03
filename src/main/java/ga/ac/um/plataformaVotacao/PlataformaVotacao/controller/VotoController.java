package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.VotoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.VotoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class VotoController {
    private final VotoService votoService;

    @PostMapping("/estudante/criar-votacao")
    public String criarVotacao(@Valid VotoEntity dadosVotoEntity) {
        ResponseEntity response = this.votoService.criarVotacao(dadosVotoEntity);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/dashboard";
        } else {
            return "pages/community";
        }
    }

    @PostMapping("/estudante/votar/{idOpcao}/{idEstudante}")
    public Object votar(@PathVariable("idOpcao") Long opcoesId, @PathVariable("idEstudante") Long estudanteId) {
        ResponseEntity response = this.votoService.votar(opcoesId, estudanteId);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/dashboard";
        } else {
            return "redirect:/dashboard";
        }
    }

    @PostMapping("/estudante/remover/{opcoesId}/{estudanteId}")
    public String removerVoto(@PathVariable("opcoesId") Long opcoesId, @PathVariable("estudanteId") Long estudanteId) {
        var response = this.votoService.removerVoto(opcoesId, estudanteId);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/";
        } else {
            return "redirect:/";
        }
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
