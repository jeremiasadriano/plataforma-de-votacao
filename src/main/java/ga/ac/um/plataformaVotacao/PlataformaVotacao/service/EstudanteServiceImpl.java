package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.EstudanteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudanteServiceImpl implements EstudanteService {
    private final EstudanteRepository objEstudanteRepository;

    public EstudanteServiceImpl(EstudanteRepository objEstudanteRepository) {
        this.objEstudanteRepository = objEstudanteRepository;
    }

    @Override
    public ResponseEntity<?> criarConta(EstudanteEntity dadosEstudante) {
        return ResponseEntity.ok(this.objEstudanteRepository.save(dadosEstudante));
    }

    @Override
    public ResponseEntity<EstudanteEntity> login(String emailEstudante, String senhaEstudante) {
        EstudanteEntity estudanteEntity = this.objEstudanteRepository.findByEmailEntityAndSenhaEntity(emailEstudante, senhaEstudante);
        if (estudanteEntity == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(estudanteEntity);
    }

    @Override
    public ResponseEntity<EstudanteEntity> verPerfil(long idEstudante) {
        Optional<EstudanteEntity> estudanteEntity = this.objEstudanteRepository.findById(idEstudante);
        return estudanteEntity.map(estudanteEntityDados -> ResponseEntity.ok().body(estudanteEntityDados)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<String> editarPerfil(long idEstudante, EstudanteEntity dadosEstudante) throws Exception {
        Optional<EstudanteEntity> estudanteEntity = this.objEstudanteRepository.findById(idEstudante);
        if (estudanteEntity.isEmpty()) throw new Exception("Estudante Vazio");

        estudanteEntity.get().setNomeEntity(dadosEstudante.getNomeEntity());
        estudanteEntity.get().setSenhaEntity(dadosEstudante.getSenhaEntity());
        estudanteEntity.get().setEmailEntity(dadosEstudante.getEmailEntity());
        this.objEstudanteRepository.save(estudanteEntity.get());
        return ResponseEntity.accepted().body("Dados Atualizados com sucesso");
    }

    @Override
    public ResponseEntity<?> apagarConta(long idEstudante) {
        this.objEstudanteRepository.deleteById(idEstudante);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<EstudanteEntity>> verEstudantes() {
        return ResponseEntity.ok().body(this.objEstudanteRepository.findAll());
    }
}
