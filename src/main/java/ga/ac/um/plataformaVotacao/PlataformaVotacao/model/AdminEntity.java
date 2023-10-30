package ga.ac.um.plataformaVotacao.PlataformaVotacao.model;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.UsuarioModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_TABLE")
public class AdminEntity extends UsuarioModel {

    @NotBlank
    @Column(name = "username_admin")
    private String usernameAdmin;

    @NotBlank
    @Column(name = "cargo_admin")
    private String cargoAdmin;

}