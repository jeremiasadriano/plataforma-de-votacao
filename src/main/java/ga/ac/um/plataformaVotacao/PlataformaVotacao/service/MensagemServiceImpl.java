package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.Component.Mensagem;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.EstudanteRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.MensagemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class MensagemServiceImpl implements MensagemService {
    private final MensagemRepository mensagemRepository;
    private final EstudanteRepository estudanteRepository;

    public MensagemServiceImpl(MensagemRepository mensagemRepository, EstudanteRepository estudanteRepository) {
        this.mensagemRepository = mensagemRepository;
        this.estudanteRepository = estudanteRepository;
    }

    @Override
    public ResponseEntity<?> comentar(Mensagem mensagem) {
        if (mensagem == null || mensagem.getEstudanteId() == null)
            return ResponseEntity.badRequest().body("Erro: mensagem contêm informações inválidas!");
        return ResponseEntity.ok(this.mensagemRepository.save(mensagem));
    }

    @Override
    public ResponseEntity<?> listarMensagens() {
        return ResponseEntity.ok(this.mensagemRepository.findAll());
    }

    @Override
    public ResponseEntity<?> verMensagem(Long idMensagem) {
        if (idMensagem == null) return ResponseEntity.badRequest().body("Erro: mensagem não encontrada!");
        Optional<Mensagem> mensagemOptional = this.mensagemRepository.findById(idMensagem);
        if (mensagemOptional.isEmpty())
            return ResponseEntity.badRequest().body("Erro: mensagem contêm informações inválidas!");

        Long idEstudante = mensagemOptional.get().getEstudanteId();
        Optional<EstudanteEntity> estudanteEntityOptional = this.estudanteRepository.findById(idEstudante);

        return estudanteEntityOptional.map(estudanteEntity -> ResponseEntity.ok(
                mensagemOptional.get().getMensagem().concat("\t" + estudanteEntity.getNome())
        )).orElse(ResponseEntity.badRequest().body("Estudante inválido!"));
    }

    @Override
    public ResponseEntity<?> editarMensagem(Long idMensagem, Long idEstudante, Mensagem mensagem) {
        if (idMensagem == null || idEstudante == null || mensagem == null)
            return ResponseEntity.badRequest().body("O servidor não conseguiu carregar a sua requisição");

        Optional<Mensagem> mensagemOptional = this.mensagemRepository.findById(idMensagem);
        if (mensagemOptional.isEmpty()) return ResponseEntity.notFound().build();

        Mensagem mensagemGet = mensagemOptional.get();
        if (Objects.equals(idEstudante, mensagemGet.getEstudanteId())) {
            mensagemGet.setMensagem(mensagem.getMensagem());
            mensagemGet.setEstudanteId(idEstudante);
            return ResponseEntity.ok(this.mensagemRepository.save(mensagemGet));
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Um estudante não pode editar uma mensagem que não o pertence!");
    }

    @Override
    public ResponseEntity<?> apagarMensagem(Long idMensagem, Long idEstudante) {
        if (idMensagem == null || idEstudante == null)
            return ResponseEntity.badRequest().body("O servidor não conseguiu carregar a sua requisição");

        Optional<Mensagem> mensagemOptional = this.mensagemRepository.findById(idMensagem);
        if (mensagemOptional.isPresent()) {
            Mensagem mensagemGet = mensagemOptional.get();
            if (Objects.equals(idEstudante, mensagemGet.getEstudanteId())) {
                this.mensagemRepository.deleteById(idMensagem);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Mensagem removida com sucesso!");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
