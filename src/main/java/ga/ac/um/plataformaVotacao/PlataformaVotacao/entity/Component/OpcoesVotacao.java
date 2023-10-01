package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OpcoesVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "opcoes_voto")
    private String opcoesVoto;

    @Column(name = "vote_count")
    private Long voteCount;


}
