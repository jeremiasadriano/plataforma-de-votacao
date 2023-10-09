package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Votos_Feitos_TABLE")
public class ContadorVotos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "id_opcao")
    private Long opcoesId;

    @NotNull
    @Column(name = "id_Estudante")
    private Long estudanteId;
}
