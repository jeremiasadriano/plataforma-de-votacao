package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.OpcoesVotacao;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.VotoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.VotoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotoServiceImpl implements VotoService {

    private VotoRepository objVotoRepository;
    private Opcoes

    public VotoServiceImpl(VotoRepository objVotoRepository) {
        this.objVotoRepository = objVotoRepository;
    }

    @Override
    public ResponseEntity<?> criarVotacao(VotoEntity dadosVotoEntity) {
        if (dadosVotoEntity == null)
            return ResponseEntity.badRequest().body("Erro: Não foi possível iniciar a votação,dados inválidos!");
        return ResponseEntity.ok(this.objVotoRepository.save(dadosVotoEntity));
    }

    @Override
    public ResponseEntity<OpcoesVotacao> votar(Long idOpcao, long idEstudante) {
        Optional<OpcoesVotacao> objOptionalOpcoesVotacao = this.objVotoRepository.findBy(idOpcao);

        return null;
    }
}
