package ga.ac.um.plataformaVotacao.PlataformaVotacao.model;

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
    @Column(name = "cargo_admin")
    private String cargoAdmin;
}