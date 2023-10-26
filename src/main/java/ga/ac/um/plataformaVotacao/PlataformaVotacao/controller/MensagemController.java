package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.Mensagem;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.MensagemService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MensagemController {
    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @PostMapping("estudante/mensagem")
    public ResponseEntity<?> comentar(@RequestBody Mensagem mensagem) {
        return this.mensagemService.comentar(mensagem);
    }

    @GetMapping("estudante/mensagem/{idMensagem}")
    public ResponseEntity<?> verMensagem(@RequestBody @PathVariable("idMensagem") Long idMensagem) {
        return this.mensagemService.verMensagem(idMensagem);
    }

    @PutMapping("estudante/editarMensagem/{idMensagem}/{idEstudante}")
    public ResponseEntity<?> editarMensagem(@RequestBody @PathVariable("idMensagem") Long idMensagem, @PathVariable("idEstudante") Long idEstudante, Mensagem mensagem) {
        return this.mensagemService.editarMensagem(idMensagem, idEstudante, mensagem);
    }
}


