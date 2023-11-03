package ga.ac.um.plataformaVotacao.PlataformaVotacao.repository;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.Component.OpcoesVotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpcoesVotosRepository extends JpaRepository<OpcoesVotos,Long> {
    List<OpcoesVotos> findByListaDosVotantesIsNotEmpty();
}
