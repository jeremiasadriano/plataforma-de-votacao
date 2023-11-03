package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.Component.OpcoesVotos;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.VotoEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VotoService {
    ResponseEntity<?> criarVotacao(VotoEntity dadosVotoEntity);

    ResponseEntity<?> votar(Long opcoesId, Long estudanteId);

    ResponseEntity<?> removerVoto(Long opcoesId, Long estudanteId);


    ResponseEntity<List<OpcoesVotos>>  listarOpcoesVoto();

    ResponseEntity<?> listarNomesVotantes(Long opcoesId);

    ResponseEntity<?> removerVotacao(Long estudanteId, Long votoId);


    List<VotoEntity> listarEstudantsVotes();
}
