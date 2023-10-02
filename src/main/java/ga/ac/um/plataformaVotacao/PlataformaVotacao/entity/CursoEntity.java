package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
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

    @NotNull
    @Column(name = "ano_estudante")
    private Integer anoEstudante;

    @Column(name = "data_insercao")
    private Integer anoAtual;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<EstudanteEntity> estudanteEntities;

    @PrePersist
    private void prePresist() {
        this.anoAtual = Calendar.getInstance().get(Calendar.YEAR);
    }

}
