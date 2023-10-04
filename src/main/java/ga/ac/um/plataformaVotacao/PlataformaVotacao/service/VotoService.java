package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.VotoEntity;
import org.springframework.http.ResponseEntity;

public interface VotoService {
    ResponseEntity<?> criarVotacao(VotoEntity dadosVotoEntity);

    ResponseEntity<?> votar(Long idOpcoes, Long idEstudante);

    ResponseEntity<?> listarOpcoesVoto();

    ResponseEntity<?> contarVotos(Long idOpcao);

}
