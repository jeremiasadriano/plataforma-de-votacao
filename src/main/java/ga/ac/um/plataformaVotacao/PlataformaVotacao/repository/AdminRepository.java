package ga.ac.um.plataformaVotacao.PlataformaVotacao.repository;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findByEmailAndSenha(String emailEntity, String senhaEntity);

    AdminEntity findByEmail(String email);
}
