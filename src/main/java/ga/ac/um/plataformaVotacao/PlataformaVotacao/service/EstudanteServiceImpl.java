package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.EstudanteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EstudanteServiceImpl implements EstudanteService {
    private final EstudanteRepository estudanteRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> criarConta(EstudanteEntity dadosEstudante) {
        dadosEstudante.setSenha(passwordEncoder.encode(dadosEstudante.getSenha()));
        return ResponseEntity.ok(this.estudanteRepository.save(dadosEstudante));
    }

    @Override
    public ResponseEntity<EstudanteEntity> login(String emailEstudante, String senhaEstudante) {
        EstudanteEntity estudanteEntity = this.estudanteRepository.findByEmailAndSenha(emailEstudante, senhaEstudante);
        if (estudanteEntity == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(estudanteEntity);
    }

    @Override
    public ResponseEntity<EstudanteEntity> verPerfil(long idEstudante) {
        Optional<EstudanteEntity> estudanteEntity = this.estudanteRepository.findById(idEstudante);
        return estudanteEntity.map(estudanteEntityDados -> ResponseEntity.ok().body(estudanteEntityDados)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<String> editarPerfil(long idEstudante, EstudanteEntity dadosEstudante) throws Exception {
        Optional<EstudanteEntity> estudanteEntity = this.estudanteRepository.findById(idEstudante);
        if (estudanteEntity.isEmpty()) throw new Exception("Estudante Vazio");

        estudanteEntity.get().setNome(dadosEstudante.getNome());
        estudanteEntity.get().setSenha(dadosEstudante.getSenha());
        estudanteEntity.get().setEmail(dadosEstudante.getEmail());
        this.estudanteRepository.save(estudanteEntity.get());
        return ResponseEntity.accepted().body("Dados Atualizados com sucesso");
    }

    @Override
    public ResponseEntity<?> apagarConta(long idEstudante) {
        this.estudanteRepository.deleteById(idEstudante);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<EstudanteEntity>> verEstudantes() {
        return ResponseEntity.ok().body(this.estudanteRepository.findAll());
    }
}
