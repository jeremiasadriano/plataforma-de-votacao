package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.CursoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.EstudanteEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EstudanteService {
    ResponseEntity<?> criarConta(EstudanteEntity dadosEstudante);

    ResponseEntity<EstudanteEntity> login(String emailEstudante, String senhaEstudante);

    ResponseEntity<EstudanteEntity> verPerfil(long idEstudante) throws Exception;

    ResponseEntity<List<EstudanteEntity>> anuncios();

    List<EstudanteEntity> listarEstudantsVotes();

    List<EstudanteEntity> listarTodosEstudantes();

    ResponseEntity<? extends EstudanteEntity> editarPerfil(EstudanteEntity estudante);

    ResponseEntity<?> atualizarPerfil(EstudanteEntity dadosEstudante);

    ResponseEntity<EstudanteEntity> recuperarSenha(String emailEstudante, String animal);

    CursoEntity cursoEstudante(Long cursoId);

    ResponseEntity<EstudanteEntity> detalhesEstudantes(Long id);
}
