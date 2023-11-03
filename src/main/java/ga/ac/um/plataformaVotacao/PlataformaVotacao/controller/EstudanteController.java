package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.Component.Anuncios;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.Component.OpcoesVotos;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.OpcoesVotosRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.VotoRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.EstudanteService;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.VotoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class EstudanteController {
    private final EstudanteService estudanteService;
    private final VotoService votoService;
    private final OpcoesVotosRepository opcoesVotosRepository;
    private final VotoRepository votoRepository;
    private static Long idVotacaoParaTitulo;
    private static Long studentId;


    @PostMapping("/registar/estudante")
    public Object criarConta(@Valid EstudanteEntity dadosEstudante) {
        var response = this.estudanteService.criarConta(dadosEstudante);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/";
        } else {
            ModelAndView view = new ModelAndView("pages/registro");
            view.addObject("DadosRegistro", "Os dados inseridos são inválidos!");
            return view;
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String emailEstudante, @RequestParam("senha") String senhaEstudante, HttpSession session) {
        var response = this.estudanteService.login(emailEstudante, senhaEstudante);
        if (response.getStatusCode().is2xxSuccessful()) {
            session.setAttribute("Username", Objects.requireNonNull(response.getBody()));
            session.setMaxInactiveInterval(300);
            return "redirect:/dashboard";
        } else {
            return "redirect:/login/DadosIncorrectos";
        }
    }

    @PostMapping("/perfil/editar")
    public ResponseEntity<String> editarPerfil(EstudanteEntity dadosEstudante) throws Exception {
        return this.estudanteService.editarPerfil(dadosEstudante);
    }

//    @DeleteMapping("/perfil/id={idEstudante}")
//    public ResponseEntity<?> apagarConta(@PathVariable("idEstudante") long idEstudante) {
//        return this.estudanteService.apagarConta(idEstudante);
//    }

    @ModelAttribute("listarEstudantesVotes")
    public List<EstudanteEntity> listarEstudantsVotes() {
        return this.estudanteService.listarEstudantsVotes();
    }

    @ModelAttribute("listaAnuncios")
    public List<Anuncios> anuncio() {
        ResponseEntity<List<EstudanteEntity>> response = this.estudanteService.anuncios();
        if (response.getStatusCode().is2xxSuccessful()) {
            for (EstudanteEntity estudante : Objects.requireNonNull(response.getBody())) {
                return estudante.getAnuncios();
            }
        }
        return Collections.emptyList();

    }

    @ModelAttribute("listarVotantes")
    public List<OpcoesVotos> opcoesVotos() {
        ResponseEntity<List<OpcoesVotos>> response = this.votoService.listarOpcoesVoto();
        if (response.getStatusCode().is2xxSuccessful()) {
            for (OpcoesVotos votos : Objects.requireNonNull(response.getBody())) {
                idVotacaoParaTitulo = votos.getVotoId();
            }
            return response.getBody();
        } else {
            return Collections.emptyList();
        }
    }


    @ModelAttribute("tituloVotacao")
    public String titulo() {
        var votosOptional = this.votoRepository.findById(idVotacaoParaTitulo);
        if (votosOptional.isEmpty()) return "Tema";
        return votosOptional.get().getTituloVotacao();
    }


    @GetMapping("/login/DadosIncorrectos")
    public ModelAndView loginError() {
        ModelAndView view = new ModelAndView("index");
        view.addObject("DataError", "Dados inseridos são inválidos!");
        return view;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("Username") == null) {
            return "index";
        }
        List<OpcoesVotos> opcoesComVotos = opcoesVotosRepository.findByListaDosVotantesIsNotEmpty();
        model.addAttribute("opcoesComVotos", opcoesComVotos);
        return "pages/dashboard";
    }
}