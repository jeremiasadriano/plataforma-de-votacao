package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.Component.Mensagem;
import org.springframework.http.ResponseEntity;

public interface MensagemService {
    ResponseEntity<?> comentar(Mensagem mensagem);

    ResponseEntity<?> listarMensagens();
    ResponseEntity<?> verMensagem(Long idMensagem);

    ResponseEntity<?> editarMensagem(Long idMensagem, Long idEstudante, Mensagem mensagem);

    ResponseEntity<?> apagarMensagem(Long idMensagem, Long idEstudante);
}
