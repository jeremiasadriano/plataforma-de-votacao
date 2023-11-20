package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.CursoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.CursoRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.EstudanteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EstudanteServiceImpl implements EstudanteService {
    private final EstudanteRepository estudanteRepository;
    private final CursoRepository cursoRepository;
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
            if (estudanteEntity.isEstadoConta()) {
                return ResponseEntity.ok(estudanteEntity);
            } else {
                return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).build();
            }
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
    public ResponseEntity<List<EstudanteEntity>> anuncios() {
        return ResponseEntity.ok(this.estudanteRepository.findAll());
    }

    @Override
    public List<EstudanteEntity> listarEstudantsVotes() {
        List<EstudanteEntity> estudanteEntities = this.estudanteRepository.findAll();
        if (estudanteEntities.isEmpty()) return Collections.emptyList();
        return estudanteEntities;
    }

    @Override
    public List<EstudanteEntity> listarTodosEstudantes() {
        var estudantes = estudanteRepository.findAll();
        if (estudantes.isEmpty()) {
            return Collections.emptyList();
        }
        return estudantes;
    }

    @Override
    public ResponseEntity<? extends EstudanteEntity> editarPerfil(EstudanteEntity estudante) {
        Optional<EstudanteEntity> estudanteDados = this.estudanteRepository.findById(estudante.getId());
        if (estudanteDados.isEmpty()) return ResponseEntity.notFound().build();
        if (estudante.getId() == null) return ResponseEntity.notFound().build();
        EstudanteEntity entidade = estudanteDados.get();
        entidade.setEstadoConta(estudante.isEstadoConta());
        entidade.setRoles(estudante.getRoles());
        return ResponseEntity.ok(this.estudanteRepository.save(entidade));
    }

    @Override
    public ResponseEntity<?> atualizarPerfil(EstudanteEntity estudante) {
        Optional<EstudanteEntity> estudanteDados = this.estudanteRepository.findById(estudante.getId());
        if (estudanteDados.isEmpty()) return ResponseEntity.notFound().build();
        EstudanteEntity entidade = estudanteDados.get();
        entidade.setNome(estudante.getNome());
        entidade.setEmail(estudante.getEmail());
        entidade.setSexo(estudante.getSexo());
        entidade.setTurnoEstudante(estudante.getTurnoEstudante());
        entidade.setAnoEstudante(estudante.getAnoEstudante());
        entidade.setCursoId(estudante.getCursoId());
        if (!estudante.getSenha().isEmpty()) {
            entidade.setSenha(passwordEncoder.encode(estudante.getSenha()));
        }
        return ResponseEntity.ok(this.estudanteRepository.save(entidade));
    }

    @Override
    public ResponseEntity<EstudanteEntity> recuperarSenha(String emailEstudante, String animal) {
        if (emailEstudante.isEmpty() || animal.isEmpty()) return ResponseEntity.notFound().build();
        var estudante = this.estudanteRepository.findByEmail(emailEstudante);
        if (estudante == null) {
            return ResponseEntity.notFound().build();
        } else if (estudante.getAnimal().equalsIgnoreCase(animal)) {
            String novaSenha = "123456789";
            estudante.setSenha(passwordEncoder.encode(novaSenha));
            return ResponseEntity.ok(this.estudanteRepository.save(estudante));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public CursoEntity cursoEstudante(Long cursoId) {
        Optional<CursoEntity> curso = this.cursoRepository.findById(cursoId);
        return curso.orElse(null);
    }

    @Override
    public ResponseEntity<EstudanteEntity> detalhesEstudantes(Long id) {
        if (id == null) return ResponseEntity.notFound().build();
        Optional<EstudanteEntity> estudante = this.estudanteRepository.findById(id);
        return estudante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
