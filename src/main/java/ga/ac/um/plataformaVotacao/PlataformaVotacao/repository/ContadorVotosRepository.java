package ga.ac.um.plataformaVotacao.PlataformaVotacao.repository;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.ContadorVotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContadorVotosRepository extends JpaRepository<ContadorVotos, Long> {
    void deleteContadorVotosByOpcoesIdAndEstudanteId(Long opcoesId, Long estudanteId);
}
