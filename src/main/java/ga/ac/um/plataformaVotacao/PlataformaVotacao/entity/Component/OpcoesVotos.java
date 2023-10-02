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

    @Column(name = "opcoes")
    private String opcoes;

    @Column(name = "voto_contador")
    private Long votoContador;

    @Column(name = "estudante_id")
    private Long estudante_id;

    @PrePersist
    private void prePresist() {
        this.votoContador = Long.parseLong("0");
    }
}
