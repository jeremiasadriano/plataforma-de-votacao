package ga.ac.um.plataformaVotacao.PlataformaVotacao.repository;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.EstudanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudanteRepository extends JpaRepository<EstudanteEntity, Long> {
    EstudanteEntity findByEmailEntityAndSenhaEntity(String emailEstudante, String senhaEstudante);
}

