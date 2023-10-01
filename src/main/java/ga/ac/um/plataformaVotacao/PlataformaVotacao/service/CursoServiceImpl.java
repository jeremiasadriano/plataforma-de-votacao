package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.CursoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository objCursoRepository;

    public CursoServiceImpl(CursoRepository objCursoRepository) {
        this.objCursoRepository = objCursoRepository;
    }

    @Override
    public ResponseEntity<CursoEntity> criarCurso_E_Estudante(CursoEntity dadosEstudante) {
        if (dadosEstudante == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(this.objCursoRepository.save(dadosEstudante));
    }

    @Override
    public ResponseEntity<List<CursoEntity>> listarCurso_E_Estudante() {
        return ResponseEntity.ok(this.objCursoRepository.findAll());
    }

    @Override
    public ResponseEntity<List<EstudanteEntity>> listarEstudantesDoCurso(long idCurso) {
        Optional<CursoEntity> cursoEntityDados = this.objCursoRepository.findById(idCurso);
        return cursoEntityDados.map(cursoEntity -> ResponseEntity.ok(cursoEntity.getEstudanteEntities())).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
