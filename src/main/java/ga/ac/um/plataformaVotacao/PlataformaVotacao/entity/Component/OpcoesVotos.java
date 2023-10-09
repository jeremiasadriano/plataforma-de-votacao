package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component;


import jakarta.persistence.*;
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

    @Column(name = "opcoes")
    private String opcoes;

    @Column(name = "voto_id")
    private Long votoId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_opcao", referencedColumnName = "id")
    private List<ListaDosVotantes> listaDosVotantes = new ArrayList<>();

    public int getListaDosVotantes() {
        return listaDosVotantes.size();
    }

    public List<ListaDosVotantes> todosVotantes() {
        return listaDosVotantes;
    }
}