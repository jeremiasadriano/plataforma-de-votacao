package ga.ac.um.plataformaVotacao.PlataformaVotacao.model.Component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Anuncios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "anuncio")
    private String anuncio;
}
