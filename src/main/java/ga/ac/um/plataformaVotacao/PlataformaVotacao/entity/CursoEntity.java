package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @Column(name = "data_insercao", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate anoAtual;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<EstudanteEntity> estudanteEntities;

}
