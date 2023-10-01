package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.OpcoesVotacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "voto_entity")
public class VotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo_votacao", unique = true)
    private String tituloVotacao;

    @Column(name = "criador_voto_fk")
    private Long criadorVotoEntity;

    @Column(name = "estadoVotacao")
    private Boolean estadoVotacao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "voto_opcoes_fk", referencedColumnName = "id")
    private List<OpcoesVotacao> opcoesVotacao;
}