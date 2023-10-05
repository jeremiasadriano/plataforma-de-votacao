package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.AdminEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;


    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public ResponseEntity<AdminEntity> criarConta(AdminEntity dadosAdmin) {
        return ResponseEntity.accepted().body(this.adminRepository.save(dadosAdmin));
    }

    @Override
    public ResponseEntity<String> fazerLogin(String emailEntity, String senhaEntity) {
        AdminEntity adminEntity = this.adminRepository.findByEmailAndSenha(emailEntity, senhaEntity);

        if (adminEntity == null) return ResponseEntity.badRequest().body("Usuario não encontrado");
        else return ResponseEntity.ok().body("Hello Usuario");
    }

    @Override
    public ResponseEntity<AdminEntity> verPerfil(Long id) throws Exception {
        Optional<AdminEntity> adminEntityOptional = this.adminRepository.findById(id);
        if (adminEntityOptional.isEmpty()) throw new Exception("Dados nao encontrados");
        return ResponseEntity.ok(adminEntityOptional.get());
    }

    @Override
    public ResponseEntity<AdminEntity> editarPerfil(AdminEntity dadosAdmin, long id) throws Exception {
        Optional<AdminEntity> adminEntityOptional = this.adminRepository.findById(id);

        if (adminEntityOptional.isEmpty()) throw new Exception("Usuario Vazio");

        adminEntityOptional.get().setNome(dadosAdmin.getNome());
        adminEntityOptional.get().setSenha(dadosAdmin.getSenha());
        adminEntityOptional.get().setSexo(dadosAdmin.getSexo());
        adminEntityOptional.get().setDataRegistro(dadosAdmin.getDataRegistro());
        adminEntityOptional.get().setEmail(dadosAdmin.getEmail());
        adminEntityOptional.get().setEstadoConta(dadosAdmin.isEstadoConta());
        adminEntityOptional.get().setUsernameAdmin(dadosAdmin.getUsernameAdmin());
        adminEntityOptional.get().setCargoAdmin(dadosAdmin.getCargoAdmin());

        return ResponseEntity.ok().body(this.adminRepository.save(adminEntityOptional.get()));
    }

    @Override
    public ResponseEntity<?> apagarConta(long id) {
        this.adminRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<AdminEntity>> listarAdmin() {
        return ResponseEntity.ok().body(this.adminRepository.findAll());
    }
}
