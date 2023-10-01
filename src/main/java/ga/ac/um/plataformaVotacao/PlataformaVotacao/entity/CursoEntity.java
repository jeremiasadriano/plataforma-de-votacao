package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "curso_estudante")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "nome_curso")
    private String nomeCurso;

    @NotBlank
    @Column(name = "turno_estudante")
    private String turnoEstudante;

    @NotBlank
    @Column(name = "ano_estudante")
    private Integer anoEstudante;

    @Column(name = "ano_atual")
    private String anoAtual;

    @Column(name = "final_ano")
    private String finalAno;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "curso_estudante_fk", referencedColumnName = "id")
    private List<EstudanteEntity> estudanteEntities;
}
