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
    public ResponseEntity<String> fazerLogin(String emailEntity, String senhaEntity) {
        if (emailEntity.isEmpty() || senhaEntity.isEmpty()) return ResponseEntity.badRequest().build();
        AdminEntity adminEntity = this.adminRepository.findByEmail(emailEntity);
        if (adminEntity == null) {
            return ResponseEntity.notFound().build();
        }
        String senhaArmazenada = adminEntity.getSenha();
        if (passwordEncoder.matches(senhaEntity, senhaArmazenada)) {
            return ResponseEntity.ok().body("Ele existe");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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
