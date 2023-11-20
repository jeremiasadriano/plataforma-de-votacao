package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.AdminEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    ResponseEntity<?> criarConta(AdminEntity dadosAdmin);

    ResponseEntity<AdminEntity> fazerLogin(String emailEntity, String senhaEntity);

    ResponseEntity<AdminEntity> verPerfil(Long id);

    ResponseEntity<AdminEntity> editarPerfil(AdminEntity dadosAdmin);

    ResponseEntity<?> apagarConta(long id);

    ResponseEntity<List<AdminEntity>> listarAdmin();

}
