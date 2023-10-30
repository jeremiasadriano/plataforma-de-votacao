package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.EstudanteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
        if (dadosEstudante == null) return ResponseEntity.badRequest().build();
        EstudanteEntity estudante = this.estudanteRepository.findByEmail(dadosEstudante.getEmail());
        if (estudante != null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email já existente!");
        }
        dadosEstudante.setSenha(passwordEncoder.encode(dadosEstudante.getSenha()));
        return ResponseEntity.ok(this.estudanteRepository.save(dadosEstudante));
    }

    @Override
    public ResponseEntity<EstudanteEntity> login(String emailEstudante, String senhaEstudante) {
        if (emailEstudante.isEmpty() || senhaEstudante.isEmpty()) return ResponseEntity.badRequest().build();
        EstudanteEntity estudanteEntity = this.estudanteRepository.findByEmail(emailEstudante);
        if (estudanteEntity == null) {
            return ResponseEntity.notFound().build();
        }
        String senha = estudanteEntity.getSenha();
        if (passwordEncoder.matches(senhaEstudante, senha)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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

        EstudanteEntity estudanteDados = estudanteEntity.get();
        estudanteDados.setNome(dadosEstudante.getNome());
        estudanteDados.setSenha(dadosEstudante.getSenha());
        estudanteDados.setEmail(dadosEstudante.getEmail());
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
