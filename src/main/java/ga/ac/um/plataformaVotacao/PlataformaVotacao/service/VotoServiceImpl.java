package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.OpcoesVotacao;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.VotoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.OpcoesVotacaoRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.VotoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotoServiceImpl implements VotoService {

    private VotoRepository objVotoRepository;
    private OpcoesVotacaoRepository objOpcoesVotacaoRepository;

    public VotoServiceImpl(VotoRepository objVotoRepository, OpcoesVotacaoRepository objOpcoesVotacaoRepository) {
        this.objVotoRepository = objVotoRepository;
        this.objOpcoesVotacaoRepository = objOpcoesVotacaoRepository;
    }

    @Override
    public ResponseEntity<?> criarVotacao(VotoEntity dadosVotoEntity) {
        if (dadosVotoEntity == null)
            return ResponseEntity.badRequest().body("Erro: Não foi possível iniciar a votação,dados inválidos!");
        return ResponseEntity.ok(this.objVotoRepository.save(dadosVotoEntity));
    }

    @Override
    public ResponseEntity<OpcoesVotacao> votar(Long idOpcao, long idEstudante) {
        Optional<OpcoesVotacao> objOptionalOpcoesVotacao = this.objOpcoesVotacaoRepository.findById(idOpcao);
        if (objOptionalOpcoesVotacao.isEmpty()) return ResponseEntity.badRequest().build();

        OpcoesVotacao opcoesVotacao = new OpcoesVotacao();
        Long voto = objOptionalOpcoesVotacao.get().getVoteCount();

        if (voto == null) {
            voto = (long) 1;
        } else {
            voto++;
        }
        opcoesVotacao.setIdEstudanteVoto(idEstudante);
        opcoesVotacao.setVoteCount(voto);
        opcoesVotacao.setOpcoesVoto(objOptionalOpcoesVotacao.get().getOpcoesVoto());
        return ResponseEntity.ok(this.objOpcoesVotacaoRepository.save(opcoesVotacao));
    }
}
