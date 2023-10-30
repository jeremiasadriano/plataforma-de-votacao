package ga.ac.um.plataformaVotacao.PlataformaVotacao.repository;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.Component.ListaDosVotantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaDosVotatantesRepository extends JpaRepository<ListaDosVotantes, Long> {
}
