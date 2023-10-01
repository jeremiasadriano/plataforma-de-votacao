package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.UsuarioModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_entity")
public class AdminEntity extends UsuarioModel {
    @Column(name = "username_admin")
    private String usernameAdmin;

    @Column(name = "cargo_admin")
    private String cargoAdmin;

}