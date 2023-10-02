package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.UsuarioModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "estudante_TABLE")
public class EstudanteEntity extends UsuarioModel {

    @NotNull
    @Column(name = "ano_estudante")
    private Integer anoEstudante;

    @NotBlank
    @Column(name = "turno_estudante")
    private String turnoEstudante;

    @Column(name = "curso_id")
    private Long cursoId;

    @Column(name = "role")
    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "estudante_id", referencedColumnName = "id")
    private List<VotoEntity> votoEntities = new ArrayList<>();
}