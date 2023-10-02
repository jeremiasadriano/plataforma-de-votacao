package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.OpcoesVotos;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.VotoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.OpcoesVotosRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.VotoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository objVotoRepository;
    private final OpcoesVotosRepository objOpcoesVotosRepository;

    public VotoServiceImpl(VotoRepository objVotoRepository, OpcoesVotosRepository objOpcoesVotosRepository) {
        this.objVotoRepository = objVotoRepository;
        this.objOpcoesVotosRepository = objOpcoesVotosRepository;
    }

    @Override
    public ResponseEntity<?> criarVotacao(VotoEntity dadosVotoEntity) {
        if (dadosVotoEntity == null)
            return ResponseEntity.badRequest().body("Erro: Não foi possível iniciar a votação,dados inválidos!");
        return ResponseEntity.ok(this.objVotoRepository.save(dadosVotoEntity));
    }

    @Override
    public ResponseEntity<?> votar(Long idOpcoes, Long idEstudante) {
        Optional<OpcoesVotos> optionalOpcoesVotos = this.objOpcoesVotosRepository.findById(idOpcoes);
        if (optionalOpcoesVotos.isEmpty()) return ResponseEntity.notFound().build();
        OpcoesVotos objOpcoesVotos = new OpcoesVotos();

        objOpcoesVotos.setOpcoes(optionalOpcoesVotos.get().getOpcoes());
        objOpcoesVotos.setEstudante_id(idEstudante);
        objOpcoesVotos.setVoto_id(optionalOpcoesVotos.get().getVoto_id());

        Long valorVotosIncremento = optionalOpcoesVotos.get().getVotoContador();
        valorVotosIncremento++;
        objOpcoesVotos.setVotoContador(valorVotosIncremento);

        return ResponseEntity.ok(this.objOpcoesVotosRepository.save(objOpcoesVotos));
    }

}
