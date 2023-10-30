package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.CursoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Override
    public ResponseEntity<CursoEntity> criarCurso_E_Estudante(CursoEntity dadosEstudante) {
        if (dadosEstudante == null) return ResponseEntity.badRequest().build();
        CursoEntity cursoEntity = this.cursoRepository.findByNomeCurso(dadosEstudante.getNomeCurso());
        if (cursoEntity != null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok().body(this.cursoRepository.save(dadosEstudante));
    }

    @Override
    public ResponseEntity<List<CursoEntity>> listarCurso_E_Estudante() {
        return ResponseEntity.ok(this.cursoRepository.findAll());
    }

    @Override
    public ResponseEntity<List<EstudanteEntity>> listarEstudantesDoCurso(long idCurso) {
        Optional<CursoEntity> cursoEntityDados = this.cursoRepository.findById(idCurso);
        return cursoEntityDados.map(cursoEntity -> ResponseEntity.ok(cursoEntity.getEstudanteEntities())).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
