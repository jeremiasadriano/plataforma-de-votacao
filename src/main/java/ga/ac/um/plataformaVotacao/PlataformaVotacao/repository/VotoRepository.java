package ga.ac.um.plataformaVotacao.PlataformaVotacao.repository;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.VotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<VotoEntity, Long> {
}
