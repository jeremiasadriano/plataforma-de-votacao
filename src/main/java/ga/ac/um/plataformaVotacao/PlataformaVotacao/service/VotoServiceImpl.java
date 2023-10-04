package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.CountVotos;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.OpcoesVotos;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.VotoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.CountVotosRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.EstudanteRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.OpcoesVotosRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.VotoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository objVotoRepository;
    private final OpcoesVotosRepository objOpcoesVotosRepository;
    private final EstudanteRepository objEstudanteRepository;
    private final CountVotosRepository objCountVotosRepository;

    public VotoServiceImpl(VotoRepository objVotoRepository, OpcoesVotosRepository objOpcoesVotosRepository, EstudanteRepository objEstudanteRepository, CountVotosRepository objCountVotosRepository) {
        this.objVotoRepository = objVotoRepository;
        this.objOpcoesVotosRepository = objOpcoesVotosRepository;
        this.objEstudanteRepository = objEstudanteRepository;
        this.objCountVotosRepository = objCountVotosRepository;
    }

    @Override
    public ResponseEntity<?> criarVotacao(VotoEntity dadosVotoEntity) {
        if (dadosVotoEntity == null)
            return ResponseEntity.badRequest().body("Erro: Não foi possível iniciar a votação,dados inválidos!");
        return ResponseEntity.ok(this.objVotoRepository.save(dadosVotoEntity));
    }

    @Override
    public ResponseEntity<?> votar(Long idOpcoes, Long idEstudante) {
        Optional<OpcoesVotos> opcoesVotosOptional = this.objOpcoesVotosRepository.findById(idOpcoes);
        Optional<EstudanteEntity> estudanteEntityOptional = this.objEstudanteRepository.findById(idEstudante);

        if (opcoesVotosOptional.isEmpty() || estudanteEntityOptional.isEmpty())
            return ResponseEntity.badRequest().build();

        CountVotos countVotos = new CountVotos();
        countVotos.setIdOpcao(opcoesVotosOptional.get().getId());
        countVotos.setIdEstudante(estudanteEntityOptional.get().getId());


        return ResponseEntity.ok(this.objCountVotosRepository.save(countVotos));
    }

    @Override
    public ResponseEntity<?> listarOpcoesVoto() {
        List<OpcoesVotos> opcoesVotosList = this.objOpcoesVotosRepository.findAll();
        if (opcoesVotosList.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(opcoesVotosList);
    }
}
