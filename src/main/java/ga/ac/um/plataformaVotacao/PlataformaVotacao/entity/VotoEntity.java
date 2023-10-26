package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.OpcoesVotos;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "voto_TABLE")
public class VotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo_votacao", unique = true)
    private String tituloVotacao;

    @Column(name = "estado_votacao")
    private Boolean estadoVotacao;

    @Column(name = "estudante_id")
    private Long estudanteId;

    @NotNull
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "voto_id", referencedColumnName = "id")
    private Set<OpcoesVotos> opcoesVotos = new HashSet<>();
}