package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.CursoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.EstudanteEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CursoService {

    ResponseEntity<CursoEntity> criarCurso_E_Estudante(CursoEntity dadosEstudante);

    ResponseEntity<List<CursoEntity>> listarCurso_E_Estudante();

    ResponseEntity<List<EstudanteEntity>> listarEstudantesDoCurso(long idCurso);

}
