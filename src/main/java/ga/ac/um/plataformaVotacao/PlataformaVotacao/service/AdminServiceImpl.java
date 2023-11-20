package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.AdminEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> criarConta(AdminEntity dadosAdmin) {
        AdminEntity adminEntity = this.adminRepository.findByEmail(dadosAdmin.getEmail());
        if (adminEntity != null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email já existente!");
        }
        dadosAdmin.setSenha(passwordEncoder.encode(dadosAdmin.getSenha()));
        return ResponseEntity.accepted().body(this.adminRepository.save(dadosAdmin));
    }

    @Override
    public ResponseEntity<AdminEntity> fazerLogin(String emailEntity, String senhaEntity) {
        AdminEntity adminEntity = this.adminRepository.findByEmailAndSenha(emailEntity, senhaEntity);
        return ResponseEntity.ok(adminEntity);
    }

    @Override
    public ResponseEntity<AdminEntity> verPerfil(Long id) {
        Optional<AdminEntity> adminEntityOptional = this.adminRepository.findById(id);
        return adminEntityOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<AdminEntity> editarPerfil(AdminEntity dadosAdmin) {
        Optional<AdminEntity> adminEntityOptional = this.adminRepository.findById(dadosAdmin.getId());
        if (adminEntityOptional.isEmpty()) return ResponseEntity.notFound().build();
        if (dadosAdmin.getId() == null) return ResponseEntity.notFound().build();
        adminEntityOptional.get().setNome(dadosAdmin.getNome());
        adminEntityOptional.get().setSexo(dadosAdmin.getSexo());
        adminEntityOptional.get().setEmail(dadosAdmin.getEmail());
        if (!dadosAdmin.getSenha().isEmpty()) {
            adminEntityOptional.get().setSenha(dadosAdmin.getSenha());
        }
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
