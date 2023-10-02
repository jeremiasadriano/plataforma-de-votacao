package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "voto_entity")
public class VotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estudante_id", referencedColumnName = "id")
    private EstudanteEntity estudante;

    @Column(name = "titulo_votacao", unique = true)
    private String tituloVotacao;

    @Column(name = "estadoVotacao")
    private Boolean estadoVotacao;

    private String opcoes;
}