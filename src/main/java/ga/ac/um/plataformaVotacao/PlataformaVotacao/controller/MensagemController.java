package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.Mensagem;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.MensagemService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {
    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @PostMapping("/")
    public ResponseEntity<?> comentar(@RequestBody Mensagem mensagem) {
        return this.mensagemService.comentar(mensagem);
    }

    @GetMapping("/mensagens")
    public ResponseEntity<?> listarMensagens() {
        return this.mensagemService.listarMensagens();
    }

    @GetMapping("estudante/{idMensagem}")
    public ResponseEntity<?> verMensagem(@RequestBody @PathVariable("idMensagem") Long idMensagem) {
        return this.mensagemService.verMensagem(idMensagem);
    }

    @PutMapping("/editarMensagem/{idMensagem}/{idEstudante}")
    public ResponseEntity<?> editarMensagem(@PathVariable("idMensagem") Long idMensagem, @PathVariable("idEstudante") Long idEstudante, @RequestBody Mensagem mensagem) {
        return this.mensagemService.editarMensagem(idMensagem, idEstudante, mensagem);
    }

    @DeleteMapping("/apagarmensagem/{idMensagem}/{idEstudante}")
    public ResponseEntity<?> apagarMensagem(@PathVariable("idMensagem") Long idMensagem, @PathVariable("idEstudante") Long idEstudante) {
        return this.mensagemService.apagarMensagem(idMensagem,idEstudante);
    }
}


