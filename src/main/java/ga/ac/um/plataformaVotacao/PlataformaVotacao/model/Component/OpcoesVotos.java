package ga.ac.um.plataformaVotacao.PlataformaVotacao.model.Component;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "OpcoesVotacao_TABLE")
public class OpcoesVotos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "opcoes")
    private String opcoes;

    @Column(name = "voto_id")
    private Long votoId;

    @NotNull
    @Column(name = "dashboard_voto")
    private Boolean dashboardVoto;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_opcao", referencedColumnName = "id")
    private List<ListaDosVotantes> listaDosVotantes = new ArrayList<>();

    public int getListaDosVotantes() {
        return listaDosVotantes.size();
    }

    public List<ListaDosVotantes> todosVotos() {
        return listaDosVotantes;
    }

    @PrePersist
    private void dashboard() {
        this.dashboardVoto = false;
    }
}