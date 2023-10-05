package ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private List<CountVotos> countVotos = new ArrayList<>();

    public int getCountVotos() {
        return countVotos.size();
    }

    public List<CountVotos> CountList() {
        return countVotos;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        OpcoesVotos that = (OpcoesVotos) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}