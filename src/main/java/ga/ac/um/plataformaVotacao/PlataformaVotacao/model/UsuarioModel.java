package ga.ac.um.plataformaVotacao.PlataformaVotacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Column(name = "nome_entity")
    private String nomeEntity;

    @NotBlank
    @Size(min = 8)
    @Column(name = "senha_entity")
    private String senhaEntity;

    @Column(name = "sexo_entity")
    private String sexoEntity;

@NotBlank
    @Email
    @Column(name = "email_entity")
    private String emailEntity;

    @Column(name = "data_entity")
    private String dataEntity;

    @Column(name = "estado_entity")
    private boolean estadoEntity;
}