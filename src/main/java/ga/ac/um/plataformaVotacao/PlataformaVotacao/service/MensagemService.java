package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.Mensagem;
import org.springframework.http.ResponseEntity;

public interface MensagemService {
    ResponseEntity<?> comentar(Mensagem mensagem);

    ResponseEntity<?> verMensagem(Long idMensagem);

    ResponseEntity<?> editarMensagem(Long idMensagem, Long idEstudante, Mensagem mensagem);
}
