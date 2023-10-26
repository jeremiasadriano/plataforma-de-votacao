package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.EstudanteEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mensagem_table")
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "mensagem")
    private String mensagem;

    @NotNull
    @Column(name = "estudante_id")
    private Long estudanteId;
}
