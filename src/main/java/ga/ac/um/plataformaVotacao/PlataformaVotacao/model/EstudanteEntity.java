package ga.ac.um.plataformaVotacao.PlataformaVotacao.model;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.Component.Anuncios;
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

    @NotBlank
    @Column(name = "animal")
    private String animal;

    @NotNull
    @Column(name = "curso_id")
    private Long cursoId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "anuncios_id", referencedColumnName = "id")
    private List<Anuncios> anuncios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "estudante_id", referencedColumnName = "id")
    private List<VotoEntity> votoEntities = new ArrayList<>();
}