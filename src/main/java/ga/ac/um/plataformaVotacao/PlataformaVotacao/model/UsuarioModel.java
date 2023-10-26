package ga.ac.um.plataformaVotacao.PlataformaVotacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class UsuarioModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Size(min = 8)
    @Column(name = "senha")
    private String senha;

    @Column(name = "sexo")
    private String sexo;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "data_registro")
    private String dataRegistro;

    @Column(name = "estado_conta")
    private boolean estadoConta;

    @PrePersist
    public void dataRegistro() {
        this.dataRegistro = LocalDate.now().toString();
    }
}