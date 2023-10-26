package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "curso_TABLE")
public class CursoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "nome_curso", unique = true)
    private String nomeCurso;

    @Column(name = "data_insercao")
    private Integer anoAtual;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "curso_id", referencedColumnName = "id")
    private List<EstudanteEntity> estudanteEntities = new ArrayList<>();

    @PrePersist
    private void anoRegistro() {
        this.anoAtual = LocalDate.now().getYear();
    }
}
