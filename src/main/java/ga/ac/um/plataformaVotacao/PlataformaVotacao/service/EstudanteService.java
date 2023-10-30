package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.EstudanteEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EstudanteService {
    ResponseEntity<?> criarConta(EstudanteEntity dadosEstudante);

    ResponseEntity<EstudanteEntity> login(String emailEstudante, String senhaEstudante);

    ResponseEntity<EstudanteEntity> verPerfil(long idEstudante) throws Exception;

    ResponseEntity<String> editarPerfil(long idEstudante, EstudanteEntity dadosEstudante) throws Exception;

    ResponseEntity<?> apagarConta(long idEstudante);

    ResponseEntity<List<EstudanteEntity>> verEstudantes();

}
