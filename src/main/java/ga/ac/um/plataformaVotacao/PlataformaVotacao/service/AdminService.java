package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.AdminEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    ResponseEntity<?> criarConta(AdminEntity dadosAdmin);

    ResponseEntity<String> fazerLogin(String emailEntity, String senhaEntity);

    ResponseEntity<AdminEntity> verPerfil(Long id) throws Exception;

    ResponseEntity<AdminEntity> editarPerfil(AdminEntity dadosAdmin, long id) throws Exception;

    ResponseEntity<?> apagarConta(long id);

    ResponseEntity<List<AdminEntity>> listarAdmin();

}
