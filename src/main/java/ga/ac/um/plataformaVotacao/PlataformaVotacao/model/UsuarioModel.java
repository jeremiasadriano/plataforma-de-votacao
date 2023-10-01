package ga.ac.um.plataformaVotacao.PlataformaVotacao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class UsuarioModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome_entity")
    private String nomeEntity;

    @Column(name = "senha_entity")
    private String senhaEntity;

    @Column(name = "sexo_entity")
    private String sexoEntity;

    @Column(name = "email_entity")
    private String emailEntity;

    @Column(name = "data_entity")
    private String dataEntity;

    @Column(name = "estado_entity")
    private boolean estadoEntity;
}