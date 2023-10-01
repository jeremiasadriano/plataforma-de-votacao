package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OpcoesVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "opcoes_voto")
    private String opcoesVoto;

    @Column(name = "vote_count", columnDefinition = "bigint default 0")
    private Long voteCount;

    @Column(name = "idEstudanteVotoFk")
    private Long idEstudanteVoto;
}
