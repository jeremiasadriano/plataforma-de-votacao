package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "OpcoesVotacao_TABLE")
public class OpcoesVotos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "voto_id")
    private Long voto_id;

    @Column(name = "opcoes")
    private String opcoes;
}
