package ga.ac.um.plataformaVotacao.PlataformaVotacao.repository;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.EstudanteEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudanteRepository extends JpaRepository<EstudanteEntity, Long> {
    EstudanteEntity findByEmailAndSenha(String emailEstudante, String senhaEstudante);

    EstudanteEntity findByEmail(@NotBlank @Email String email);
}

