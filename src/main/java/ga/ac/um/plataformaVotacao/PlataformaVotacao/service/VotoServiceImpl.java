package ga.ac.um.plataformaVotacao.PlataformaVotacao.service;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.ListaDosVotantes;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.Component.OpcoesVotos;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.VotoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.EstudanteRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.ListaDosVotatantesRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.OpcoesVotosRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
        for (VotoEntity listaVotos : listaVotosEntity) {
            if (listaVotos.getTituloVotacao().equals(tituloVotacao)) {
                VotoEntity resultadoPeloTituloVoto = this.votoRepository.findByTituloVotacao(tituloVotacao);
                Long idEstudante = resultadoPeloTituloVoto.getEstudanteId();

                Optional<EstudanteEntity> buscarEstudantePeloId = this.estudanteRepository.findById(idEstudante);
                if (buscarEstudantePeloId.isEmpty()) {
                    return ResponseEntity.badRequest().body("Não estudantes não podem iniciar uma votação");
                }

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
        Set<OpcoesVotos> listaOpcoesVotos = dadosVotosOptional.get().getOpcoesVotos();
        for (OpcoesVotos receberListaOpcoesVotos : listaOpcoesVotos) {
            List<ListaDosVotantes> listarTodosContadorVotos = receberListaOpcoesVotos.todosVotos();
            for (ListaDosVotantes receberListaTodosContadorVotos : listarTodosContadorVotos) {
                Long idVotante = receberListaTodosContadorVotos.getEstudanteId();
                if (Objects.equals(idVotante, estudanteId))
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Um estudante não pode votar duas vezes");
            }
        }
//        Adicionar os dados do voto ao contador
        ListaDosVotantes listaDosVotantes = new ListaDosVotantes();
        listaDosVotantes.setOpcoesId(opcoesVotosOptional.get().getId());
        listaDosVotantes.setEstudanteId(estudanteEntityOptional.get().getId());
        return ResponseEntity.ok(this.listaDosVotatantesRepository.save(listaDosVotantes));
    }

    @Override
    public ResponseEntity<?> removerVoto(Long opcoesId, Long estudanteId) {
        Optional<OpcoesVotos> opcoesVotosOptional = this.opcoesVotosRepository.findById(opcoesId);
        Optional<EstudanteEntity> estudanteEntityOptional = this.estudanteRepository.findById(estudanteId);
        if (opcoesVotosOptional.isEmpty() || estudanteEntityOptional.isEmpty())
            return ResponseEntity.badRequest().build();

        List<ListaDosVotantes> listaDosVotantes = opcoesVotosOptional.get().todosVotos();
        for (ListaDosVotantes votantes : listaDosVotantes) {
            if (Objects.equals(votantes.getEstudanteId(), estudanteId)) {
                this.listaDosVotatantesRepository.deleteById(votantes.getId());
                return ResponseEntity.ok("Removido com sucesso!");
            } else if (Objects.isNull(votantes.getEstudanteId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Voto não encontrado!");
            }
        }
        return ResponseEntity.badRequest().body("O servidor não conseguiu carregar a requisição!");
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

        List<ListaDosVotantes> listaDosVotantes = opcoesVotosOptional.get().todosVotos();
        ArrayList<String> nomesVotantes = new ArrayList<>();
        for (ListaDosVotantes votantes : listaDosVotantes) {
            Long ids = votantes.getEstudanteId();
            Optional<EstudanteEntity> estudanteEntityOptional = this.estudanteRepository.findById(ids);
            if (estudanteEntityOptional.isEmpty()) return ResponseEntity.badRequest().build();
            nomesVotantes.add(estudanteEntityOptional.get().getNome());
        }
        return ResponseEntity.ok(nomesVotantes);
    }

    @Override
    public ResponseEntity<?> removerVotacao(Long estudanteId, Long votoId) {
        Optional<EstudanteEntity> estudanteEntityOptional = this.estudanteRepository.findById(estudanteId);
        Optional<VotoEntity> votoEntityOptional = this.votoRepository.findById(votoId);
        if (estudanteEntityOptional.isEmpty() || votoEntityOptional.isEmpty())
            return ResponseEntity.badRequest().build();

        List<VotoEntity> listaVotos = estudanteEntityOptional.get().getVotoEntities();
        for (VotoEntity votos : listaVotos) {
            if (Objects.equals(votos.getId(), votoId)) {
                this.votoRepository.deleteById(votoId);
                return ResponseEntity.ok("Voto Removido com sucesso!");
            }
        }
        return ResponseEntity.badRequest().body("O servidor não conseguiu carregar a sua requisição!");
    }
}

