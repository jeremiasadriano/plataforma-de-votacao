package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.CountVotos;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.OpcoesVotos;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.VotoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.CountVotosRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.EstudanteRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.OpcoesVotosRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

//        Receber o id do voto a partir das suas opções
        long votoId = opcoesVotosOptional.get().getVotoId();
        Optional<VotoEntity> dadosVotosOptional = this.objVotoRepository.findById(votoId);
        if (dadosVotosOptional.isEmpty()) return ResponseEntity.badRequest().build();

//        Validar se o estudante já votou ou não
        List<OpcoesVotos> listaOpcoesVotos = dadosVotosOptional.get().getOpcoesVotos();
        for (OpcoesVotos receberListaOpcoesVotos : listaOpcoesVotos) {
            List<CountVotos> listarTodosCountVotos = receberListaOpcoesVotos.CountList();
            for (CountVotos receberListaTodosCountVotos : listarTodosCountVotos) {
                if (Objects.equals(receberListaTodosCountVotos.getIdEstudante(), idEstudante))
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Um estudante não pode votar duas vezes");
            }
        }
//        Adicionar os dados do voto ao contador
        CountVotos countVotos = new CountVotos();
        countVotos.setIdOpcao(opcoesVotosOptional.get().getId());
        countVotos.setIdEstudante(estudanteEntityOptional.get().getId());
        return ResponseEntity.ok(this.objCountVotosRepository.save(countVotos));
    }

    @Override
    public ResponseEntity<?> listarOpcoesVoto() {
        List<OpcoesVotos> listaOpcoesVotos = this.objOpcoesVotosRepository.findAll();
        if (listaOpcoesVotos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listaOpcoesVotos);
    }

    @Override
    public ResponseEntity<?> contarVotos(Long idOpcao) {
        Optional<OpcoesVotos> opcoesVotosOptional = this.objOpcoesVotosRepository.findById(idOpcao);
        return opcoesVotosOptional.map(e -> ResponseEntity.ok(e.getCountVotos())).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
