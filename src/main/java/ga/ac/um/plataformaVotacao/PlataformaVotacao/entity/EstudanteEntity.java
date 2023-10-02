package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.UsuarioModel;
import jakarta.persistence.*;
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
    @Column(name = "role")
    private String role;

    @Column(name = "curso_id")
    private Long cursoId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "estudante_id", referencedColumnName = "id")
    private List<VotoEntity> votoEntities = new ArrayList<>();
}