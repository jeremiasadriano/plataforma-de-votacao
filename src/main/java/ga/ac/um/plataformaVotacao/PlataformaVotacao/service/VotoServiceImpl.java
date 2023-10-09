package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.ListaDosVotantes;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.OpcoesVotos;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.VotoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.ListaDosVotatantesRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.EstudanteRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.OpcoesVotosRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final OpcoesVotosRepository opcoesVotosRepository;
    private final EstudanteRepository estudanteRepository;
    private final ListaDosVotatantesRepository listaDosVotatantesRepository;

    public VotoServiceImpl(VotoRepository votoRepository, OpcoesVotosRepository opcoesVotosRepository, EstudanteRepository estudanteRepository, ListaDosVotatantesRepository listaDosVotatantesRepository) {
        this.votoRepository = votoRepository;
        this.opcoesVotosRepository = opcoesVotosRepository;
        this.estudanteRepository = estudanteRepository;
        this.listaDosVotatantesRepository = listaDosVotatantesRepository;
    }

    @Override
    public ResponseEntity<?> criarVotacao(VotoEntity dadosVotoEntity) {
        if (dadosVotoEntity == null)
            return ResponseEntity.badRequest().body("Erro: Não foi possível iniciar a votação,dados inválidos!");
        List<VotoEntity> listaVotosEntity = this.votoRepository.findAll();

//        Verificar se o título já existe ou não
        String tituloVotacao = dadosVotoEntity.getTituloVotacao();
        for (VotoEntity recberListaVotosEntity : listaVotosEntity) {
            if (recberListaVotosEntity.getTituloVotacao().equals(tituloVotacao)) {
                VotoEntity resultadoPeloTituloVoto = this.votoRepository.findByTituloVotacao(tituloVotacao);
                Long idEstudante = resultadoPeloTituloVoto.getEstudanteId();

                Optional<EstudanteEntity> buscarEstudantePeloId = this.estudanteRepository.findById(idEstudante);
                if (buscarEstudantePeloId.isEmpty())
                    return ResponseEntity.badRequest().body("Não estudantes não podem iniciar uma votação");
                String nomeEstudante = buscarEstudantePeloId.get().getNome();

                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Não é permitido criar-se uma votação com um título já existente, o estudante " + "'" + nomeEstudante + "'" + " já abriu uma votação com o mesmo título.");
            }
        }
//        Verificar se o estudante por criar a votação existe ou não
        Long idEstudante = dadosVotoEntity.getEstudanteId();
        Optional<EstudanteEntity> buscarEstudantePeloId = this.estudanteRepository.findById(idEstudante);
        if (buscarEstudantePeloId.isEmpty())
            return ResponseEntity.badRequest().body("Não estudantes não podem iniciar uma votação!");
        return ResponseEntity.ok(this.votoRepository.save(dadosVotoEntity));
    }

    @Override
    public ResponseEntity<?> votar(Long opcoesId, Long estudanteId) {
        Optional<OpcoesVotos> opcoesVotosOptional = this.opcoesVotosRepository.findById(opcoesId);
        Optional<EstudanteEntity> estudanteEntityOptional = this.estudanteRepository.findById(estudanteId);
        if (opcoesVotosOptional.isEmpty() && estudanteEntityOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Erro! Opções inseridas são inválidas.");
        } else if (opcoesVotosOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Opção de voto inválida");
        } else if (estudanteEntityOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Estudante inválido");
        }
//        Receber o id do voto a partir das suas opções
        long votoId = opcoesVotosOptional.get().getVotoId();
        Optional<VotoEntity> dadosVotosOptional = this.votoRepository.findById(votoId);
        if (dadosVotosOptional.isEmpty()) return ResponseEntity.badRequest().build();

//        Validar se o estudante já votou ou não
        List<OpcoesVotos> listaOpcoesVotos = dadosVotosOptional.get().getOpcoesVotos();
        for (OpcoesVotos receberListaOpcoesVotos : listaOpcoesVotos) {
            List<ListaDosVotantes> listarTodosContadorVotos = receberListaOpcoesVotos.todosVotantes();
            for (ListaDosVotantes receberListaTodosContadorVotos : listarTodosContadorVotos) {
                Long idVotante = receberListaTodosContadorVotos.getEstudanteId();
                if (Objects.equals(idVotante, estudanteId))
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Um estudante não pode votar duas vezes");
            }
        }
//        Adicionar os dados do voto ao contador
        ListaDosVotantes contadorVotos = new ListaDosVotantes();
        contadorVotos.setOpcoesId(opcoesVotosOptional.get().getId());
        contadorVotos.setEstudanteId(estudanteEntityOptional.get().getId());
        return ResponseEntity.ok(this.listaDosVotatantesRepository.save(contadorVotos));
    }

    @Override
    public ResponseEntity<?> removerVoto(Long opcoesId, Long estudanteId) {
        Optional<OpcoesVotos> opcoesVotosOptional = this.opcoesVotosRepository.findById(opcoesId);
        if (opcoesVotosOptional.isEmpty()) return ResponseEntity.badRequest().build();

        List<ListaDosVotantes> listaDosVotantes = opcoesVotosOptional.get().todosVotantes();
        for (ListaDosVotantes receberlistaDosVotantes : listaDosVotantes) {
            Long idVotante = receberlistaDosVotantes.getEstudanteId();
            Long idVoto = receberlistaDosVotantes.getId();

            if (Objects.equals(idVotante, estudanteId)) {
                this.listaDosVotatantesRepository.deleteById(idVoto);
                return ResponseEntity.ok("Voto removido com sucesso!");
            }
        }
        return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<?> listarOpcoesVoto() {
        List<OpcoesVotos> listaOpcoesVotos = this.opcoesVotosRepository.findAll();
        if (listaOpcoesVotos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listaOpcoesVotos);
    }

    @Override
    public ResponseEntity<?> listarNomesVotantes(Long idOpcao) {
        Optional<OpcoesVotos> opcoesVotosOptional = this.opcoesVotosRepository.findById(idOpcao);
        if (opcoesVotosOptional.isEmpty()) return ResponseEntity.badRequest().build();

        List<ListaDosVotantes> listaDosVotantes = opcoesVotosOptional.get().todosVotantes();
        for (ListaDosVotantes receberListaDosVotantes : listaDosVotantes) {
            Long idEstudante = receberListaDosVotantes.getEstudanteId();

            Optional<EstudanteEntity> estudantesVotantes = this.estudanteRepository.findById(idEstudante);
            if (estudantesVotantes.isEmpty()) return ResponseEntity.notFound().build();

            String nomeEstudante = estudantesVotantes.get().getNome();
            return ResponseEntity.ok(nomeEstudante);
        }
        return ResponseEntity.notFound().build();
    }
}
