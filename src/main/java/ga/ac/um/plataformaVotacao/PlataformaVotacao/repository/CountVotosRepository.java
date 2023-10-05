package ga.ac.um.plataformaVotacao.PlataformaVotacao.repository;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.CountVotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountVotosRepository extends JpaRepository<CountVotos, Long> {
    CountVotos countByIdOpcao(Long idOpcao);
}
