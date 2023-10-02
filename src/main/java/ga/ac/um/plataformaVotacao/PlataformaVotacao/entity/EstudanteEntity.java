package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.UsuarioModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "estudante_entity")
public class EstudanteEntity extends UsuarioModel {

    @Column(name = "curso_estudante_fk")
    private Long curso_fk;

    @Column(name = "role")
    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "estudante_criador_votoFk", referencedColumnName = "id")
    private List<VotoEntity> votoEntity;
}